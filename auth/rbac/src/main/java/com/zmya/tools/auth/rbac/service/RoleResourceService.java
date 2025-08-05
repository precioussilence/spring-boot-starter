package com.zmya.tools.auth.rbac.service;

import com.zmya.tools.auth.rbac.entity.SysResource;
import com.zmya.tools.auth.rbac.entity.SysRole;
import com.zmya.tools.auth.rbac.entity.SysRoleResource;
import com.zmya.tools.auth.rbac.error.BusinessException;
import com.zmya.tools.auth.rbac.error.ErrorCodeEnum;
import com.zmya.tools.auth.rbac.model.dto.ResourceDTO;
import com.zmya.tools.auth.rbac.model.request.SaveRoleResourceRequest;
import com.zmya.tools.auth.rbac.repository.SysResourceRepository;
import com.zmya.tools.auth.rbac.repository.SysRoleRepository;
import com.zmya.tools.auth.rbac.repository.SysRoleResourceRepository;
import com.zmya.tools.auth.rbac.utils.ModelConvertUtils;
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

    private final SysRoleRepository sysRoleRepository;
    private final SysResourceRepository sysResourceRepository;
    private final SysRoleResourceRepository sysRoleResourceRepository;

    public boolean save(SaveRoleResourceRequest request) {
        Optional<SysRole> roleOptional = sysRoleRepository.findById(request.getRoleId());
        if (roleOptional.isEmpty()) {
            throw new BusinessException(ErrorCodeEnum.ROLE_NOT_FOUND);
        }
        List<SysResource> resources = sysResourceRepository.findByIdIn(request.getResourceIds());
        if (CollectionUtils.isEmpty(resources)) {
            throw new BusinessException(ErrorCodeEnum.RESOURCE_NOT_FOUND);
        }
        Set<Long> existResourceIds = resources.stream().map(SysResource::getId).collect(Collectors.toSet());
        if (!existResourceIds.containsAll(request.getResourceIds())) {
            throw new BusinessException(ErrorCodeEnum.RESOURCE_NOT_FOUND);
        }
        List<Long> needSaveResourceIds = new ArrayList<>();
        List<SysRoleResource> savedRoleResources = sysRoleResourceRepository.findByRoleAndResourceIn(roleOptional.get(), resources);
        if (CollectionUtils.isEmpty(savedRoleResources)) {
            needSaveResourceIds.addAll(request.getResourceIds());
        } else {
            List<Long> savedResourceIds = savedRoleResources.stream().map(SysRoleResource::getResource).map(SysResource::getId).toList();
            needSaveResourceIds.addAll(request.getResourceIds().stream().filter(resourceId -> !savedResourceIds.contains(resourceId)).toList());
        }
        if (needSaveResourceIds.isEmpty()) {
            return true;
        }
        List<SysRoleResource> needSaveRoleResources = new ArrayList<>();
        needSaveResourceIds.forEach(resourceId -> {
            SysRoleResource sysRoleResource = new SysRoleResource();
            sysRoleResource.setRole(sysRoleRepository.getReferenceById(request.getRoleId()));
            sysRoleResource.setResource(sysResourceRepository.getReferenceById(resourceId));
            needSaveRoleResources.add(sysRoleResource);
        });
        List<SysRoleResource> sysRoleResources = sysRoleResourceRepository.saveAll(needSaveRoleResources);
        return !CollectionUtils.isEmpty(sysRoleResources);
    }

    public List<ResourceDTO> list(Long roleId) {
        List<SysResource> allResources = sysResourceRepository.findAll();
        if (CollectionUtils.isEmpty(allResources)) {
            return new ArrayList<>();
        }
        List<ResourceDTO> resourceDTOS = ModelConvertUtils.fromResources(allResources);
        List<SysRoleResource> roleResources = sysRoleResourceRepository.findByRole_Id(roleId);
        if (CollectionUtils.isEmpty(roleResources)) {
            return resourceDTOS;
        }
        List<Long> ownedResourceIds = roleResources.stream().map(SysRoleResource::getResource).map(SysResource::getId).toList();
        resourceDTOS.stream().filter(resource -> ownedResourceIds.contains(resource.getId())).forEach(roleDTO -> roleDTO.setOwned(true));
        return resourceDTOS;
    }

}
