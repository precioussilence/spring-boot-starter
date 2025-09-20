package com.zmya.tools.data.core.dao;

import com.zmya.tools.data.core.model.SysRole;
import com.zmya.tools.data.core.model.SysUser;
import com.zmya.tools.data.core.model.SysUserRole;

import java.util.Collection;
import java.util.List;

public interface SysUserRoleDao {
    List<SysUserRole> findByUserAndRoleIn(SysUser user, Collection<SysRole> roles);

    List<SysUserRole> findByUser_Id(Long userId);
}