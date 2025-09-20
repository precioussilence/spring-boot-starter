package com.zmya.tools.data.core.dao;

import com.zmya.tools.data.core.common.query.PageQuery;
import com.zmya.tools.data.core.common.result.PageResult;
import com.zmya.tools.data.core.model.SysRole;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface SysRoleDao {
    Optional<SysRole> findById(Long id);

    List<SysRole> findByIdIn(Collection<Long> ids);

    void removeByIdIn(Collection<Long> ids);

    SysRole save(SysRole sysRole);

    PageResult<SysRole> findAll(PageQuery query, String roleName);

    List<SysRole> findAll();
}