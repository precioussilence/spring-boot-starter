package com.zmya.tools.data.core.dao;

import com.zmya.tools.data.core.common.query.PageQuery;
import com.zmya.tools.data.core.common.result.PageResult;
import com.zmya.tools.data.core.model.SysResource;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface SysResourceDao {
    List<SysResource> findByIdIn(Collection<Long> ids);

    void removeByIdIn(Collection<Long> ids);

    Optional<SysResource> findById(Long id);

    PageResult<SysResource> findAll(PageQuery query, String resourceName);

    List<SysResource> findAll();

    SysResource save(SysResource sysResource);
}