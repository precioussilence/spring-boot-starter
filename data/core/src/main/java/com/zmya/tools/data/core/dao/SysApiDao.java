package com.zmya.tools.data.core.dao;

import com.zmya.tools.data.core.model.SysApi;

import java.util.Collection;
import java.util.List;

public interface SysApiDao  {

    List<String> findRequiredRoleCodes(String url,  String method);

    List<SysApi> findByIdIn(Collection<Long> ids);

    List<SysApi> findByUrlAndMethod(String url, String method);
}