package com.zmya.tools.data.jpa.adapter;

import com.zmya.tools.data.core.dao.SysRoleResourceDao;
import com.zmya.tools.data.core.model.SysRoleResource;
import com.zmya.tools.data.jpa.entity.SysResource;
import com.zmya.tools.data.jpa.repository.SysRoleResourceRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
@ConditionalOnProperty(name = "zmya.data.impl", havingValue = "jpa", matchIfMissing = true)
@AllArgsConstructor
public class SysRoleResourceDaoAdapter implements SysRoleResourceDao {

    private final SysRoleResourceRepository sysRoleResourceRepository;

    @Override
    public List<SysRoleResource> findByResourceIn(Collection<Long> resourceIds) {
        List<SysResource> resourceList = resourceIds.stream().map(id -> {
            SysResource sysResource = new SysResource();
            sysResource.setId(id);
            return sysResource;
        }).toList();
        List<com.zmya.tools.data.jpa.entity.SysRoleResource> list = sysRoleResourceRepository.findByResourceIn(resourceList);
        return list.stream().map(source -> {
            SysRoleResource target = new SysRoleResource();
            BeanUtils.copyProperties(source, target);
            return target;
        }).toList();
    }

    @Override
    public List<SysRoleResource> findByRoleAndResourceIn(Long roleId, Collection<Long> resourceIds) {
        com.zmya.tools.data.jpa.entity.SysRole role = new com.zmya.tools.data.jpa.entity.SysRole();
        role.setId(roleId);
        List<SysResource> resourceList = resourceIds.stream().map(id -> {
            SysResource resource = new SysResource();
            resource.setId(id);
            return resource;
        }).toList();
        List<com.zmya.tools.data.jpa.entity.SysRoleResource> list = sysRoleResourceRepository.findByRoleAndResourceIn(role, resourceList);
        return List.of();
    }

    @Override
    public List<SysRoleResource> findByRole_Id(Long roleId) {
        List<com.zmya.tools.data.jpa.entity.SysRoleResource> list = sysRoleResourceRepository.findByRole_Id(roleId);
        return list.stream().map(source -> {
            SysRoleResource target = new SysRoleResource();
            BeanUtils.copyProperties(source, target);
            return target;
        }).toList();
    }
}
