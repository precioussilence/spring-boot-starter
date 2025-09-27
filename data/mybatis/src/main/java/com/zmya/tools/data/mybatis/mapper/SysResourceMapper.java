package com.zmya.tools.data.mybatis.mapper;

import static com.zmya.tools.data.mybatis.mapper.SysResourceEntityDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

import com.zmya.tools.data.mybatis.entity.SysResourceEntity;
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
public interface SysResourceMapper extends CommonCountMapper, CommonDeleteMapper, CommonUpdateMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_resource")
    BasicColumn[] selectList = BasicColumn.columnList(id, resourceName, resourceCode, resourceType, parentId, path, icon, sort, visible, status, createdBy, createdTime, updatedBy, updatedTime);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_resource")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="row.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<SysResourceEntity> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_resource")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="SysResourceEntityResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="resource_name", property="resourceName", jdbcType=JdbcType.VARCHAR),
        @Result(column="resource_code", property="resourceCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="resource_type", property="resourceType", jdbcType=JdbcType.INTEGER),
        @Result(column="parent_id", property="parentId", jdbcType=JdbcType.BIGINT),
        @Result(column="path", property="path", jdbcType=JdbcType.VARCHAR),
        @Result(column="icon", property="icon", jdbcType=JdbcType.VARCHAR),
        @Result(column="sort", property="sort", jdbcType=JdbcType.INTEGER),
        @Result(column="visible", property="visible", jdbcType=JdbcType.INTEGER),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="created_by", property="createdBy", jdbcType=JdbcType.BIGINT),
        @Result(column="created_time", property="createdTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="updated_by", property="updatedBy", jdbcType=JdbcType.BIGINT),
        @Result(column="updated_time", property="updatedTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SysResourceEntity> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_resource")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("SysResourceEntityResult")
    Optional<SysResourceEntity> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_resource")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, sysResourceEntity, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_resource")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, sysResourceEntity, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_resource")
    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_resource")
    default int insert(SysResourceEntity row) {
        return MyBatis3Utils.insert(this::insert, row, sysResourceEntity, c ->
            c.map(resourceName).toProperty("resourceName")
            .map(resourceCode).toProperty("resourceCode")
            .map(resourceType).toProperty("resourceType")
            .map(parentId).toProperty("parentId")
            .map(path).toProperty("path")
            .map(icon).toProperty("icon")
            .map(sort).toProperty("sort")
            .map(visible).toProperty("visible")
            .map(status).toProperty("status")
            .map(createdBy).toProperty("createdBy")
            .map(createdTime).toProperty("createdTime")
            .map(updatedBy).toProperty("updatedBy")
            .map(updatedTime).toProperty("updatedTime")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_resource")
    default int insertSelective(SysResourceEntity row) {
        return MyBatis3Utils.insert(this::insert, row, sysResourceEntity, c ->
            c.map(resourceName).toPropertyWhenPresent("resourceName", row::getResourceName)
            .map(resourceCode).toPropertyWhenPresent("resourceCode", row::getResourceCode)
            .map(resourceType).toPropertyWhenPresent("resourceType", row::getResourceType)
            .map(parentId).toPropertyWhenPresent("parentId", row::getParentId)
            .map(path).toPropertyWhenPresent("path", row::getPath)
            .map(icon).toPropertyWhenPresent("icon", row::getIcon)
            .map(sort).toPropertyWhenPresent("sort", row::getSort)
            .map(visible).toPropertyWhenPresent("visible", row::getVisible)
            .map(status).toPropertyWhenPresent("status", row::getStatus)
            .map(createdBy).toPropertyWhenPresent("createdBy", row::getCreatedBy)
            .map(createdTime).toPropertyWhenPresent("createdTime", row::getCreatedTime)
            .map(updatedBy).toPropertyWhenPresent("updatedBy", row::getUpdatedBy)
            .map(updatedTime).toPropertyWhenPresent("updatedTime", row::getUpdatedTime)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_resource")
    default Optional<SysResourceEntity> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, sysResourceEntity, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_resource")
    default List<SysResourceEntity> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, sysResourceEntity, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_resource")
    default List<SysResourceEntity> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, sysResourceEntity, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_resource")
    default Optional<SysResourceEntity> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_resource")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, sysResourceEntity, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_resource")
    static UpdateDSL<UpdateModel> updateAllColumns(SysResourceEntity row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(resourceName).equalTo(row::getResourceName)
                .set(resourceCode).equalTo(row::getResourceCode)
                .set(resourceType).equalTo(row::getResourceType)
                .set(parentId).equalTo(row::getParentId)
                .set(path).equalTo(row::getPath)
                .set(icon).equalTo(row::getIcon)
                .set(sort).equalTo(row::getSort)
                .set(visible).equalTo(row::getVisible)
                .set(status).equalTo(row::getStatus)
                .set(createdBy).equalTo(row::getCreatedBy)
                .set(createdTime).equalTo(row::getCreatedTime)
                .set(updatedBy).equalTo(row::getUpdatedBy)
                .set(updatedTime).equalTo(row::getUpdatedTime);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_resource")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(SysResourceEntity row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(resourceName).equalToWhenPresent(row::getResourceName)
                .set(resourceCode).equalToWhenPresent(row::getResourceCode)
                .set(resourceType).equalToWhenPresent(row::getResourceType)
                .set(parentId).equalToWhenPresent(row::getParentId)
                .set(path).equalToWhenPresent(row::getPath)
                .set(icon).equalToWhenPresent(row::getIcon)
                .set(sort).equalToWhenPresent(row::getSort)
                .set(visible).equalToWhenPresent(row::getVisible)
                .set(status).equalToWhenPresent(row::getStatus)
                .set(createdBy).equalToWhenPresent(row::getCreatedBy)
                .set(createdTime).equalToWhenPresent(row::getCreatedTime)
                .set(updatedBy).equalToWhenPresent(row::getUpdatedBy)
                .set(updatedTime).equalToWhenPresent(row::getUpdatedTime);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_resource")
    default int updateByPrimaryKey(SysResourceEntity row) {
        return update(c ->
            c.set(resourceName).equalTo(row::getResourceName)
            .set(resourceCode).equalTo(row::getResourceCode)
            .set(resourceType).equalTo(row::getResourceType)
            .set(parentId).equalTo(row::getParentId)
            .set(path).equalTo(row::getPath)
            .set(icon).equalTo(row::getIcon)
            .set(sort).equalTo(row::getSort)
            .set(visible).equalTo(row::getVisible)
            .set(status).equalTo(row::getStatus)
            .set(createdBy).equalTo(row::getCreatedBy)
            .set(createdTime).equalTo(row::getCreatedTime)
            .set(updatedBy).equalTo(row::getUpdatedBy)
            .set(updatedTime).equalTo(row::getUpdatedTime)
            .where(id, isEqualTo(row::getId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_resource")
    default int updateByPrimaryKeySelective(SysResourceEntity row) {
        return update(c ->
            c.set(resourceName).equalToWhenPresent(row::getResourceName)
            .set(resourceCode).equalToWhenPresent(row::getResourceCode)
            .set(resourceType).equalToWhenPresent(row::getResourceType)
            .set(parentId).equalToWhenPresent(row::getParentId)
            .set(path).equalToWhenPresent(row::getPath)
            .set(icon).equalToWhenPresent(row::getIcon)
            .set(sort).equalToWhenPresent(row::getSort)
            .set(visible).equalToWhenPresent(row::getVisible)
            .set(status).equalToWhenPresent(row::getStatus)
            .set(createdBy).equalToWhenPresent(row::getCreatedBy)
            .set(createdTime).equalToWhenPresent(row::getCreatedTime)
            .set(updatedBy).equalToWhenPresent(row::getUpdatedBy)
            .set(updatedTime).equalToWhenPresent(row::getUpdatedTime)
            .where(id, isEqualTo(row::getId))
        );
    }
}