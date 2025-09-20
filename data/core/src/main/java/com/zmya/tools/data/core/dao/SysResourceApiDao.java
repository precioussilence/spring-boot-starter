package com.zmya.tools.data.core.dao;

import com.zmya.tools.data.core.model.SysApi;
import com.zmya.tools.data.core.model.SysResource;
import com.zmya.tools.data.core.model.SysResourceApi;

import java.util.Collection;
import java.util.List;

public interface SysResourceApiDao  {
    List<SysResourceApi> findByApiIn(Collection<SysApi> apis);

    List<SysResourceApi> findByResourceAndApiIn(SysResource resource, Collection<SysApi> apis);
}