package com.zmya.tools.auth.rbac.service;

import com.zmya.tools.auth.rbac.enums.ApiStatusEnum;
import com.zmya.tools.auth.rbac.error.BusinessException;
import com.zmya.tools.auth.rbac.error.ErrorCodeEnum;
import com.zmya.tools.auth.rbac.model.dto.ApiDTO;
import com.zmya.tools.auth.rbac.model.request.ListApiRequest;
import com.zmya.tools.auth.rbac.model.request.SaveResourceApiRequest;
import com.zmya.tools.data.core.dao.SysApiDao;
import com.zmya.tools.data.core.dao.SysResourceApiDao;
import com.zmya.tools.data.core.dao.SysResourceDao;
import com.zmya.tools.data.core.model.SysApi;
import com.zmya.tools.data.core.model.SysResource;
import com.zmya.tools.data.core.model.SysResourceApi;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.util.pattern.PathPattern;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class ApiService implements CommandLineRunner {

    private final SysApiDao sysApiDao;
    private final SysResourceDao sysResourceDao;
    private final SysResourceApiDao sysResourceApiDao;

    private final RequestMappingHandlerMapping handlerMapping;

    public List<ApiDTO> list(ListApiRequest request) {
        List<SysApi> all = sysApiDao.findByApiNameAndUrl(request.getApiName(), request.getUrl());
        return from(all);
    }

    public boolean save(SaveResourceApiRequest request) {
        Optional<SysResource> resourceOptional = sysResourceDao.findById(request.getResourceId());
        if (resourceOptional.isEmpty()) {
            throw new BusinessException(ErrorCodeEnum.RESOURCE_NOT_FOUND);
        }
        List<SysApi> apis = sysApiDao.findByIdIn(request.getApiIds());
        if (CollectionUtils.isEmpty(apis)) {
            throw new BusinessException(ErrorCodeEnum.API_NOT_FOUND);
        }
        Set<Long> existApiIds = apis.stream().map(SysApi::getId).collect(Collectors.toSet());
        if (!existApiIds.containsAll(request.getApiIds())) {
            throw new BusinessException(ErrorCodeEnum.API_NOT_FOUND);
        }
        List<Long> needSaveApiIds = new ArrayList<>();
        List<Long> apiIds = apis.stream().map(SysApi::getId).toList();
        List<SysResourceApi> savedResourceApis = sysResourceApiDao.findByResourceAndApiIn(resourceOptional.get().getId(), apiIds);
        if (CollectionUtils.isEmpty(savedResourceApis)) {
            needSaveApiIds.addAll(request.getApiIds());
        } else {
            List<Long> savedApiIds = savedResourceApis.stream().map(SysResourceApi::getApiId).toList();
            needSaveApiIds.addAll(request.getApiIds().stream().filter(apiId -> !savedApiIds.contains(apiId)).toList());
        }
        if (needSaveApiIds.isEmpty()) {
            return true;
        }
        List<SysResourceApi> needSaveResourceApis = new ArrayList<>();
        needSaveApiIds.forEach(apiId -> {
            SysResourceApi sysResourceApi = new SysResourceApi();
            sysResourceApi.setResourceId(request.getResourceId());
            sysResourceApi.setApiId(apiId);
            needSaveResourceApis.add(sysResourceApi);
        });
        List<SysResourceApi> sysResourceApis = sysResourceApiDao.saveAll(needSaveResourceApis);
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

    @Override
    public void run(String... args) {
        List<SysApi> entities = new ArrayList<>();
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = handlerMapping.getHandlerMethods();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : handlerMethods.entrySet()) {
            RequestMappingInfo info = entry.getKey();
            HandlerMethod method = entry.getValue();
            if (Objects.isNull(info.getPathPatternsCondition())) {
                continue;
            }
            Set<PathPattern> paths = info.getPathPatternsCondition().getPatterns();
            Set<RequestMethod> methods = info.getMethodsCondition().getMethods();
            if (CollectionUtils.isEmpty(paths) || CollectionUtils.isEmpty(methods)) {
                continue;
            }
            for (PathPattern path : paths) {
                for (RequestMethod requestMethod : methods) {
                    SysApi entity = new SysApi();
                    entity.setUrl(path.getPatternString());
                    entity.setMethod(requestMethod.name());
                    entity.setApiName(method.getBeanType().getSimpleName() + "." + method.getMethod().getName());
                    entity.setStatus(ApiStatusEnum.NORMAL.getStatus());
                    entities.add(entity);
                }
            }
        }
        log.info("ApiScanner|there's total {} apis found", entities.size());
        for (SysApi entity : entities) {
            List<SysApi> apis = sysApiDao.findByUrlAndMethod(entity.getUrl(), entity.getMethod());
            if (!CollectionUtils.isEmpty(apis)) {
                SysApi existApi = apis.getFirst();
                entity.setId(existApi.getId());
                entity.setStatus(existApi.getStatus());
                entity.setDescription(existApi.getDescription());
            }
        }
        List<SysApi> saved = sysApiDao.saveAll(entities);
        log.info("ApiScanner|there's total {} apis saved", saved.size());
    }
}
