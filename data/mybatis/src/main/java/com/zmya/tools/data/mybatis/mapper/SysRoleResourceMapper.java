package com.zmya.tools.data.mybatis.mapper;

import static com.zmya.tools.data.mybatis.mapper.SysRoleResourceEntityDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

import com.zmya.tools.data.mybatis.entity.SysRoleResourceEntity;
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
public interface SysRoleResourceMapper extends CommonCountMapper, CommonDeleteMapper, CommonUpdateMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_role_resource")
    BasicColumn[] selectList = BasicColumn.columnList(id, roleId, resourceId, createdTime);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_role_resource")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="row.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<SysRoleResourceEntity> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_role_resource")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="SysRoleResourceEntityResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="role_id", property="roleId", jdbcType=JdbcType.BIGINT),
        @Result(column="resource_id", property="resourceId", jdbcType=JdbcType.BIGINT),
        @Result(column="created_time", property="createdTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SysRoleResourceEntity> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_role_resource")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("SysRoleResourceEntityResult")
    Optional<SysRoleResourceEntity> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_role_resource")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, sysRoleResourceEntity, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_role_resource")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, sysRoleResourceEntity, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_role_resource")
    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_role_resource")
    default int insert(SysRoleResourceEntity row) {
        return MyBatis3Utils.insert(this::insert, row, sysRoleResourceEntity, c ->
            c.map(roleId).toProperty("roleId")
            .map(resourceId).toProperty("resourceId")
            .map(createdTime).toProperty("createdTime")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_role_resource")
    default int insertSelective(SysRoleResourceEntity row) {
        return MyBatis3Utils.insert(this::insert, row, sysRoleResourceEntity, c ->
            c.map(roleId).toPropertyWhenPresent("roleId", row::getRoleId)
            .map(resourceId).toPropertyWhenPresent("resourceId", row::getResourceId)
            .map(createdTime).toPropertyWhenPresent("createdTime", row::getCreatedTime)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_role_resource")
    default Optional<SysRoleResourceEntity> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, sysRoleResourceEntity, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_role_resource")
    default List<SysRoleResourceEntity> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, sysRoleResourceEntity, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_role_resource")
    default List<SysRoleResourceEntity> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, sysRoleResourceEntity, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_role_resource")
    default Optional<SysRoleResourceEntity> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_role_resource")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, sysRoleResourceEntity, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_role_resource")
    static UpdateDSL<UpdateModel> updateAllColumns(SysRoleResourceEntity row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(roleId).equalTo(row::getRoleId)
                .set(resourceId).equalTo(row::getResourceId)
                .set(createdTime).equalTo(row::getCreatedTime);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_role_resource")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(SysRoleResourceEntity row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(roleId).equalToWhenPresent(row::getRoleId)
                .set(resourceId).equalToWhenPresent(row::getResourceId)
                .set(createdTime).equalToWhenPresent(row::getCreatedTime);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_role_resource")
    default int updateByPrimaryKey(SysRoleResourceEntity row) {
        return update(c ->
            c.set(roleId).equalTo(row::getRoleId)
            .set(resourceId).equalTo(row::getResourceId)
            .set(createdTime).equalTo(row::getCreatedTime)
            .where(id, isEqualTo(row::getId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_role_resource")
    default int updateByPrimaryKeySelective(SysRoleResourceEntity row) {
        return update(c ->
            c.set(roleId).equalToWhenPresent(row::getRoleId)
            .set(resourceId).equalToWhenPresent(row::getResourceId)
            .set(createdTime).equalToWhenPresent(row::getCreatedTime)
            .where(id, isEqualTo(row::getId))
        );
    }
}