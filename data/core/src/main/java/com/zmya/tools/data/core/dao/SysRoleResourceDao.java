package com.zmya.tools.data.core.dao;

import com.zmya.tools.data.core.model.SysRoleResource;

import java.util.Collection;
import java.util.List;

public interface SysRoleResourceDao {
    List<SysRoleResource> findByResourceIn(Collection<Long> resourceIds);

    List<SysRoleResource> findByRoleAndResourceIn(Long roleId, Collection<Long> resourceIds);

    List<SysRoleResource> findByRoleId(Long roleId);

    List<SysRoleResource> saveAll(List<SysRoleResource> sysRoleResourceList);
}