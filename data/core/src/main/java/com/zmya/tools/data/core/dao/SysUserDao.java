package com.zmya.tools.data.core.dao;

import com.zmya.tools.data.core.common.query.PageQuery;
import com.zmya.tools.data.core.common.result.PageResult;
import com.zmya.tools.data.core.model.SysUser;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface SysUserDao {

    List<String> findGrantedRoleCodes(String username);

    void removeByIdIn(Collection<Long> ids);

    List<SysUser> findByUsername(String username);

    Optional<SysUser> findById(Long id);

    PageResult<SysUser> findAll(PageQuery query,
                                String username,
                                String nickname,
                                String phone,
                                String email);

    SysUser save(SysUser sysUser);
}