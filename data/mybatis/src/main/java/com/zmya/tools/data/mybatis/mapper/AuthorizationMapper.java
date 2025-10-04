package com.zmya.tools.data.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;

import java.util.List;

@Mapper
public interface AuthorizationMapper {

    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    List<String> findGrantedRoleCodes(SelectStatementProvider provider);

    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    List<String> findRequiredRoleCodes(SelectStatementProvider provider);

}
