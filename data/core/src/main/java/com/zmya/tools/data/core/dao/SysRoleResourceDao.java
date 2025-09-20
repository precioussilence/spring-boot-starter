package com.zmya.tools.data.core.dao;

import com.zmya.tools.data.core.model.SysResource;
import com.zmya.tools.data.core.model.SysRole;
import com.zmya.tools.data.core.model.SysRoleResource;

import java.util.Collection;
import java.util.List;

public interface SysRoleResourceDao {
    List<SysRoleResource> findByResourceIn(Collection<SysResource> resources);

    List<SysRoleResource> findByRoleAndResourceIn(SysRole role, Collection<SysResource> resources);

    List<SysRoleResource> findByRole_Id(Long roleId);
}