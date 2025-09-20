package com.zmya.tools.auth.rbac.service;

import com.zmya.tools.auth.rbac.error.BusinessException;
import com.zmya.tools.auth.rbac.error.ErrorCodeEnum;
import com.zmya.tools.auth.rbac.model.dto.ResourceDTO;
import com.zmya.tools.auth.rbac.model.request.SaveRoleResourceRequest;
import com.zmya.tools.auth.rbac.utils.ModelConvertUtils;
import com.zmya.tools.data.core.dao.SysResourceDao;
import com.zmya.tools.data.core.dao.SysRoleDao;
import com.zmya.tools.data.core.dao.SysRoleResourceDao;
import com.zmya.tools.data.core.model.SysResource;
import com.zmya.tools.data.core.model.SysRole;
import com.zmya.tools.data.core.model.SysRoleResource;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RoleResourceService {

    private final SysRoleDao sysRoleDao;
    private final SysResourceDao sysResourceDao;
    private final SysRoleResourceDao sysRoleResourceDao;

    public boolean save(SaveRoleResourceRequest request) {
        Optional<SysRole> roleOptional = sysRoleDao.findById(request.getRoleId());
        if (roleOptional.isEmpty()) {
            throw new BusinessException(ErrorCodeEnum.ROLE_NOT_FOUND);
        }
        List<SysResource> resources = sysResourceDao.findByIdIn(request.getResourceIds());
        if (CollectionUtils.isEmpty(resources)) {
            throw new BusinessException(ErrorCodeEnum.RESOURCE_NOT_FOUND);
        }
        Set<Long> existResourceIds = resources.stream().map(SysResource::getId).collect(Collectors.toSet());
        if (!existResourceIds.containsAll(request.getResourceIds())) {
            throw new BusinessException(ErrorCodeEnum.RESOURCE_NOT_FOUND);
        }
        List<Long> needSaveResourceIds = new ArrayList<>();
        List<Long> resourceIds = resources.stream().map(SysResource::getId).toList();
        List<SysRoleResource> savedRoleResources = sysRoleResourceDao.findByRoleAndResourceIn(roleOptional.get().getId(), resourceIds);
        if (CollectionUtils.isEmpty(savedRoleResources)) {
            needSaveResourceIds.addAll(request.getResourceIds());
        } else {
            List<Long> savedResourceIds = savedRoleResources.stream().map(SysRoleResource::getResourceId).toList();
            needSaveResourceIds.addAll(request.getResourceIds().stream().filter(resourceId -> !savedResourceIds.contains(resourceId)).toList());
        }
        if (needSaveResourceIds.isEmpty()) {
            return true;
        }
        List<SysRoleResource> needSaveRoleResources = new ArrayList<>();
        needSaveResourceIds.forEach(resourceId -> {
            SysRoleResource sysRoleResource = new SysRoleResource();
            sysRoleResource.setRoleId(request.getRoleId());
            sysRoleResource.setResourceId(resourceId);
            needSaveRoleResources.add(sysRoleResource);
        });
        List<SysRoleResource> sysRoleResources = sysRoleResourceDao.saveAll(needSaveRoleResources);
        return !CollectionUtils.isEmpty(sysRoleResources);
    }

    public List<ResourceDTO> list(Long roleId) {
        List<SysResource> allResources = sysResourceDao.findAll();
        if (CollectionUtils.isEmpty(allResources)) {
            return new ArrayList<>();
        }
        List<ResourceDTO> resourceDTOS = ModelConvertUtils.fromResources(allResources);
        List<SysRoleResource> roleResources = sysRoleResourceDao.findByRoleId(roleId);
        if (CollectionUtils.isEmpty(roleResources)) {
            return resourceDTOS;
        }
        List<Long> ownedResourceIds = roleResources.stream().map(SysRoleResource::getResourceId).toList();
        resourceDTOS.stream().filter(resource -> ownedResourceIds.contains(resource.getId())).forEach(roleDTO -> roleDTO.setOwned(true));
        return resourceDTOS;
    }

}
