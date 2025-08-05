package com.zmya.tools.auth.rbac.service;

import com.zmya.tools.auth.rbac.entity.SysApi;
import com.zmya.tools.auth.rbac.model.dto.ApiDTO;
import com.zmya.tools.auth.rbac.model.request.ListApiRequest;
import com.zmya.tools.auth.rbac.repository.SysApiRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ApiService {

    private final SysApiRepository sysApiRepository;

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
