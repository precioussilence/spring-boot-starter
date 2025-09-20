package com.zmya.tools.data.core.dao;

import com.zmya.tools.data.core.model.SysUserRole;

import java.util.Collection;
import java.util.List;

public interface SysUserRoleDao {
    List<SysUserRole> findByUserAndRoleIn(Long userId, Collection<Long> roleIds);

    List<SysUserRole> findByUserId(Long userId);
}