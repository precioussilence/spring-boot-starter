package com.zmya.tools.auth.rbac.service;

import com.zmya.tools.auth.rbac.entity.SysApi;
import com.zmya.tools.auth.rbac.entity.SysResource;
import com.zmya.tools.auth.rbac.entity.SysResourceApi;
import com.zmya.tools.auth.rbac.error.BusinessException;
import com.zmya.tools.auth.rbac.error.ErrorCodeEnum;
import com.zmya.tools.auth.rbac.model.dto.ApiDTO;
import com.zmya.tools.auth.rbac.model.request.ListApiRequest;
import com.zmya.tools.auth.rbac.model.request.SaveResourceApiRequest;
import com.zmya.tools.auth.rbac.repository.SysApiRepository;
import com.zmya.tools.auth.rbac.repository.SysResourceApiRepository;
import com.zmya.tools.auth.rbac.repository.SysResourceRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ApiService {

    private final SysApiRepository sysApiRepository;
    private final SysResourceRepository sysResourceRepository;
    private final SysResourceApiRepository sysResourceApiRepository;

    public List<ApiDTO> list(ListApiRequest request) {
        Specification<SysApi> spec = Specification.unrestricted();
        if (StringUtils.hasText(request.getApiName())) {
            spec.and((root, query, builder) -> builder.equal(root.get("apiName"), request.getApiName()));
        }
        if (StringUtils.hasText(request.getUrl())) {
            spec.and((root, query, builder) -> builder.equal(root.get("url"), request.getUrl()));
        }
        List<SysApi> all = sysApiRepository.findAll(spec);
        return from(all);
    }

    public boolean save(SaveResourceApiRequest request) {
        Optional<SysResource> resourceOptional = sysResourceRepository.findById(request.getResourceId());
        if (resourceOptional.isEmpty()) {
            throw new BusinessException(ErrorCodeEnum.RESOURCE_NOT_FOUND);
        }
        List<SysApi> apis = sysApiRepository.findByIdIn(request.getApiIds());
        if (CollectionUtils.isEmpty(apis)) {
            throw new BusinessException(ErrorCodeEnum.API_NOT_FOUND);
        }
        Set<Long> existApiIds = apis.stream().map(SysApi::getId).collect(Collectors.toSet());
        if (!existApiIds.containsAll(request.getApiIds())) {
            throw new BusinessException(ErrorCodeEnum.API_NOT_FOUND);
        }
        List<Long> needSaveApiIds = new ArrayList<>();
        List<SysResourceApi> savedResourceApis = sysResourceApiRepository.findByResourceAndApiIn(resourceOptional.get(), apis);
        if (CollectionUtils.isEmpty(savedResourceApis)) {
            needSaveApiIds.addAll(request.getApiIds());
        } else {
            List<Long> savedApiIds = savedResourceApis.stream().map(SysResourceApi::getApi).map(SysApi::getId).toList();
            needSaveApiIds.addAll(request.getApiIds().stream().filter(apiId -> !savedApiIds.contains(apiId)).toList());
        }
        if (needSaveApiIds.isEmpty()) {
            return true;
        }
        List<SysResourceApi> needSaveResourceApis = new ArrayList<>();
        needSaveApiIds.forEach(apiId -> {
            SysResourceApi sysResourceApi = new SysResourceApi();
            sysResourceApi.setResource(sysResourceRepository.getReferenceById(request.getResourceId()));
            sysResourceApi.setApi(sysApiRepository.getReferenceById(apiId));
            needSaveResourceApis.add(sysResourceApi);
        });
        List<SysResourceApi> sysResourceApis = sysResourceApiRepository.saveAll(needSaveResourceApis);
        return !CollectionUtils.isEmpty(sysResourceApis);
    }

    private List<ApiDTO> from(List<SysApi> sysApis) {
        List<ApiDTO> apiDTOList = new ArrayList<>();
        for (SysApi sysApi : sysApis) {
            ApiDTO apiDTO = new ApiDTO();
            BeanUtils.copyProperties(sysApi, apiDTO);
            apiDTOList.add(apiDTO);
        }
        return apiDTOList;
    }

}
