package com.zmya.tools.data.core.dao;

import com.zmya.tools.data.core.model.SysResource;

import java.util.Collection;
import java.util.List;

public interface SysResourceDao {
    List<SysResource> findByIdIn(Collection<Long> ids);

    void removeByIdIn(Collection<Long> ids);
}