package com.zmya.tools.data.core.dao;

import com.zmya.tools.data.core.model.SysUser;

import java.util.Collection;
import java.util.List;

public interface SysUserDao {

    List<String> findGrantedRoleCodes(String username);

    void removeByIdIn(Collection<Long> ids);

    List<SysUser> findByUsername(String username);
}