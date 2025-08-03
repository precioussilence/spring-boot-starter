package com.zmya.tools.auth.rbac.service;

import com.zmya.tools.auth.rbac.entity.*;
import com.zmya.tools.auth.rbac.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AuthorityService {

    private final SysApiRepository sysApiRepository;
    private final SysResourceApiRepository sysResourceApiRepository;
    private final SysResourceRepository sysResourceRepository;
    private final SysRoleResourceRepository sysRoleResourceRepository;
    private final SysRoleRepository sysRoleRepository;

    public List<String> getAuthorities(String path, String method) {
        List<SysApi> apis = sysApiRepository.findByUrlAndMethod(path, method);
        if (CollectionUtils.isEmpty(apis)) {
            return new ArrayList<>();
        }
        List<SysResourceApi> resourceApis = sysResourceApiRepository.findByApiIn(apis);
        if (CollectionUtils.isEmpty(resourceApis)) {
            return new ArrayList<>();
        }
        List<Long> resourceIds = resourceApis.stream().map(SysResourceApi::getId).toList();
        List<SysResource> resources = sysResourceRepository.findByIdIn(resourceIds);
        List<SysRoleResource> roleResources = sysRoleResourceRepository.findByResourceIn(resources);
        if (CollectionUtils.isEmpty(roleResources)) {
            return new ArrayList<>();
        }
        List<Long> roleIds = roleResources.stream().map(SysRoleResource::getId).toList();
        List<SysRole> roles = sysRoleRepository.findByIdIn(roleIds);
        return roles.stream().map(SysRole::getRoleName).toList();
    }

}
