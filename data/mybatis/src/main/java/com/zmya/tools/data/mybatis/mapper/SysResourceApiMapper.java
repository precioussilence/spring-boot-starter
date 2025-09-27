package com.zmya.tools.data.mybatis.mapper;

import static com.zmya.tools.data.mybatis.mapper.SysResourceApiEntityDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

import com.zmya.tools.data.mybatis.entity.SysResourceApiEntity;
import jakarta.annotation.Generated;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.BasicColumn;
import org.mybatis.dynamic.sql.delete.DeleteDSLCompleter;
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider;
import org.mybatis.dynamic.sql.select.CountDSLCompleter;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.UpdateDSL;
import org.mybatis.dynamic.sql.update.UpdateDSLCompleter;
import org.mybatis.dynamic.sql.update.UpdateModel;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;
import org.mybatis.dynamic.sql.util.mybatis3.CommonCountMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonDeleteMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonUpdateMapper;
import org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utils;

@Mapper
public interface SysResourceApiMapper extends CommonCountMapper, CommonDeleteMapper, CommonUpdateMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_resource_api")
    BasicColumn[] selectList = BasicColumn.columnList(id, resourceId, apiId, createdTime);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_resource_api")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="row.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<SysResourceApiEntity> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_resource_api")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="SysResourceApiEntityResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="resource_id", property="resourceId", jdbcType=JdbcType.BIGINT),
        @Result(column="api_id", property="apiId", jdbcType=JdbcType.BIGINT),
        @Result(column="created_time", property="createdTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SysResourceApiEntity> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_resource_api")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("SysResourceApiEntityResult")
    Optional<SysResourceApiEntity> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_resource_api")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, sysResourceApiEntity, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_resource_api")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, sysResourceApiEntity, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_resource_api")
    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_resource_api")
    default int insert(SysResourceApiEntity row) {
        return MyBatis3Utils.insert(this::insert, row, sysResourceApiEntity, c ->
            c.map(resourceId).toProperty("resourceId")
            .map(apiId).toProperty("apiId")
            .map(createdTime).toProperty("createdTime")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_resource_api")
    default int insertSelective(SysResourceApiEntity row) {
        return MyBatis3Utils.insert(this::insert, row, sysResourceApiEntity, c ->
            c.map(resourceId).toPropertyWhenPresent("resourceId", row::getResourceId)
            .map(apiId).toPropertyWhenPresent("apiId", row::getApiId)
            .map(createdTime).toPropertyWhenPresent("createdTime", row::getCreatedTime)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_resource_api")
    default Optional<SysResourceApiEntity> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, sysResourceApiEntity, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_resource_api")
    default List<SysResourceApiEntity> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, sysResourceApiEntity, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_resource_api")
    default List<SysResourceApiEntity> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, sysResourceApiEntity, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_resource_api")
    default Optional<SysResourceApiEntity> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_resource_api")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, sysResourceApiEntity, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_resource_api")
    static UpdateDSL<UpdateModel> updateAllColumns(SysResourceApiEntity row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(resourceId).equalTo(row::getResourceId)
                .set(apiId).equalTo(row::getApiId)
                .set(createdTime).equalTo(row::getCreatedTime);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_resource_api")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(SysResourceApiEntity row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(resourceId).equalToWhenPresent(row::getResourceId)
                .set(apiId).equalToWhenPresent(row::getApiId)
                .set(createdTime).equalToWhenPresent(row::getCreatedTime);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_resource_api")
    default int updateByPrimaryKey(SysResourceApiEntity row) {
        return update(c ->
            c.set(resourceId).equalTo(row::getResourceId)
            .set(apiId).equalTo(row::getApiId)
            .set(createdTime).equalTo(row::getCreatedTime)
            .where(id, isEqualTo(row::getId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_resource_api")
    default int updateByPrimaryKeySelective(SysResourceApiEntity row) {
        return update(c ->
            c.set(resourceId).equalToWhenPresent(row::getResourceId)
            .set(apiId).equalToWhenPresent(row::getApiId)
            .set(createdTime).equalToWhenPresent(row::getCreatedTime)
            .where(id, isEqualTo(row::getId))
        );
    }
}