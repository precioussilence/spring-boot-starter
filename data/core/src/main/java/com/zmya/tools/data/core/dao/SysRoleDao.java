package com.zmya.tools.data.core.dao;

import com.zmya.tools.data.core.model.SysRole;

import java.util.Collection;
import java.util.List;

public interface SysRoleDao {
    List<SysRole> findByIdIn(Collection<Long> ids);

    void removeByIdIn(Collection<Long> ids);
}