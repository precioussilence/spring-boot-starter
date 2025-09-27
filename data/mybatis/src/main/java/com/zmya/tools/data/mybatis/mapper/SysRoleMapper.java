package com.zmya.tools.data.mybatis.mapper;

import static com.zmya.tools.data.mybatis.mapper.SysRoleEntityDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

import com.zmya.tools.data.mybatis.entity.SysRoleEntity;
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
public interface SysRoleMapper extends CommonCountMapper, CommonDeleteMapper, CommonUpdateMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_role")
    BasicColumn[] selectList = BasicColumn.columnList(id, roleCode, roleName, description, status, createdBy, createdTime, updatedBy, updatedTime);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_role")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="row.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<SysRoleEntity> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_role")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="SysRoleEntityResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="role_code", property="roleCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="role_name", property="roleName", jdbcType=JdbcType.VARCHAR),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="created_by", property="createdBy", jdbcType=JdbcType.BIGINT),
        @Result(column="created_time", property="createdTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="updated_by", property="updatedBy", jdbcType=JdbcType.BIGINT),
        @Result(column="updated_time", property="updatedTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SysRoleEntity> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_role")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("SysRoleEntityResult")
    Optional<SysRoleEntity> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_role")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, sysRoleEntity, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_role")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, sysRoleEntity, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_role")
    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_role")
    default int insert(SysRoleEntity row) {
        return MyBatis3Utils.insert(this::insert, row, sysRoleEntity, c ->
            c.map(roleCode).toProperty("roleCode")
            .map(roleName).toProperty("roleName")
            .map(description).toProperty("description")
            .map(status).toProperty("status")
            .map(createdBy).toProperty("createdBy")
            .map(createdTime).toProperty("createdTime")
            .map(updatedBy).toProperty("updatedBy")
            .map(updatedTime).toProperty("updatedTime")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_role")
    default int insertSelective(SysRoleEntity row) {
        return MyBatis3Utils.insert(this::insert, row, sysRoleEntity, c ->
            c.map(roleCode).toPropertyWhenPresent("roleCode", row::getRoleCode)
            .map(roleName).toPropertyWhenPresent("roleName", row::getRoleName)
            .map(description).toPropertyWhenPresent("description", row::getDescription)
            .map(status).toPropertyWhenPresent("status", row::getStatus)
            .map(createdBy).toPropertyWhenPresent("createdBy", row::getCreatedBy)
            .map(createdTime).toPropertyWhenPresent("createdTime", row::getCreatedTime)
            .map(updatedBy).toPropertyWhenPresent("updatedBy", row::getUpdatedBy)
            .map(updatedTime).toPropertyWhenPresent("updatedTime", row::getUpdatedTime)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_role")
    default Optional<SysRoleEntity> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, sysRoleEntity, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_role")
    default List<SysRoleEntity> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, sysRoleEntity, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_role")
    default List<SysRoleEntity> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, sysRoleEntity, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_role")
    default Optional<SysRoleEntity> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_role")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, sysRoleEntity, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_role")
    static UpdateDSL<UpdateModel> updateAllColumns(SysRoleEntity row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(roleCode).equalTo(row::getRoleCode)
                .set(roleName).equalTo(row::getRoleName)
                .set(description).equalTo(row::getDescription)
                .set(status).equalTo(row::getStatus)
                .set(createdBy).equalTo(row::getCreatedBy)
                .set(createdTime).equalTo(row::getCreatedTime)
                .set(updatedBy).equalTo(row::getUpdatedBy)
                .set(updatedTime).equalTo(row::getUpdatedTime);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_role")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(SysRoleEntity row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(roleCode).equalToWhenPresent(row::getRoleCode)
                .set(roleName).equalToWhenPresent(row::getRoleName)
                .set(description).equalToWhenPresent(row::getDescription)
                .set(status).equalToWhenPresent(row::getStatus)
                .set(createdBy).equalToWhenPresent(row::getCreatedBy)
                .set(createdTime).equalToWhenPresent(row::getCreatedTime)
                .set(updatedBy).equalToWhenPresent(row::getUpdatedBy)
                .set(updatedTime).equalToWhenPresent(row::getUpdatedTime);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_role")
    default int updateByPrimaryKey(SysRoleEntity row) {
        return update(c ->
            c.set(roleCode).equalTo(row::getRoleCode)
            .set(roleName).equalTo(row::getRoleName)
            .set(description).equalTo(row::getDescription)
            .set(status).equalTo(row::getStatus)
            .set(createdBy).equalTo(row::getCreatedBy)
            .set(createdTime).equalTo(row::getCreatedTime)
            .set(updatedBy).equalTo(row::getUpdatedBy)
            .set(updatedTime).equalTo(row::getUpdatedTime)
            .where(id, isEqualTo(row::getId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_role")
    default int updateByPrimaryKeySelective(SysRoleEntity row) {
        return update(c ->
            c.set(roleCode).equalToWhenPresent(row::getRoleCode)
            .set(roleName).equalToWhenPresent(row::getRoleName)
            .set(description).equalToWhenPresent(row::getDescription)
            .set(status).equalToWhenPresent(row::getStatus)
            .set(createdBy).equalToWhenPresent(row::getCreatedBy)
            .set(createdTime).equalToWhenPresent(row::getCreatedTime)
            .set(updatedBy).equalToWhenPresent(row::getUpdatedBy)
            .set(updatedTime).equalToWhenPresent(row::getUpdatedTime)
            .where(id, isEqualTo(row::getId))
        );
    }
}