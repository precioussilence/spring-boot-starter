package com.zmya.tools.data.core.dao;

import com.zmya.tools.data.core.model.SysResourceApi;

import java.util.Collection;
import java.util.List;

public interface SysResourceApiDao {
    List<SysResourceApi> findByResourceAndApiIn(Long resourceId, Collection<Long> apiIds);

    List<SysResourceApi> saveAll(List<SysResourceApi> sysResourceApis);
}