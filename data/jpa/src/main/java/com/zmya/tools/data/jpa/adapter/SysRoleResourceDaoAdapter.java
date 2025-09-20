package com.zmya.tools.data.jpa.adapter;

import com.zmya.tools.data.core.dao.SysRoleResourceDao;
import com.zmya.tools.data.core.model.SysRoleResource;
import com.zmya.tools.data.jpa.entity.SysResourceEntity;
import com.zmya.tools.data.jpa.entity.SysRoleEntity;
import com.zmya.tools.data.jpa.entity.SysRoleResourceEntity;
import com.zmya.tools.data.jpa.repository.SysResourceRepository;
import com.zmya.tools.data.jpa.repository.SysRoleRepository;
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
    private final SysRoleRepository sysRoleRepository;
    private final SysResourceRepository sysResourceRepository;

    @Override
    public List<SysRoleResource> findByResourceIn(Collection<Long> resourceIds) {
        List<SysResourceEntity> resourceList = resourceIds.stream().map(id -> {
            SysResourceEntity sysResource = new SysResourceEntity();
            sysResource.setId(id);
            return sysResource;
        }).toList();
        List<SysRoleResourceEntity> list = sysRoleResourceRepository.findByResourceIn(resourceList);
        return list.stream().map(source -> {
            SysRoleResource target = new SysRoleResource();
            BeanUtils.copyProperties(source, target);
            return target;
        }).toList();
    }

    @Override
    public List<SysRoleResource> findByRoleAndResourceIn(Long roleId, Collection<Long> resourceIds) {
        SysRoleEntity role = new SysRoleEntity();
        role.setId(roleId);
        List<SysResourceEntity> resourceList = resourceIds.stream().map(id -> {
            SysResourceEntity resource = new SysResourceEntity();
            resource.setId(id);
            return resource;
        }).toList();
        List<SysRoleResourceEntity> list = sysRoleResourceRepository.findByRoleAndResourceIn(role, resourceList);
        return list.stream().map(source -> {
            SysRoleResource target = new SysRoleResource();
            BeanUtils.copyProperties(source, target);
            return target;
        }).toList();
    }

    @Override
    public List<SysRoleResource> findByRoleId(Long roleId) {
        List<SysRoleResourceEntity> list = sysRoleResourceRepository.findByRole_Id(roleId);
        return list.stream().map(source -> {
            SysRoleResource target = new SysRoleResource();
            BeanUtils.copyProperties(source, target);
            return target;
        }).toList();
    }

    @Override
    public List<SysRoleResource> saveAll(List<SysRoleResource> sysRoleResourceList) {
        List<SysRoleResourceEntity> entities = sysRoleResourceList.stream().map(source -> {
            SysRoleResourceEntity entity = new SysRoleResourceEntity();
            entity.setRole(sysRoleRepository.getReferenceById(source.getRoleId()));
            entity.setResource(sysResourceRepository.getReferenceById(source.getResourceId()));
            return entity;
        }).toList();
        List<SysRoleResourceEntity> saved = sysRoleResourceRepository.saveAll(entities);
        return saved.stream().map(source -> {
            SysRoleResource target = new SysRoleResource();
            BeanUtils.copyProperties(source, target);
            return target;
        }).toList();
    }
}
