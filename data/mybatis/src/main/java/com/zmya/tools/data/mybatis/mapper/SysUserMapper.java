package com.zmya.tools.data.mybatis.mapper;

import static com.zmya.tools.data.mybatis.mapper.SysUserEntityDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

import com.zmya.tools.data.mybatis.entity.SysUserEntity;
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
public interface SysUserMapper extends CommonCountMapper, CommonDeleteMapper, CommonUpdateMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_user")
    BasicColumn[] selectList = BasicColumn.columnList(id, username, password, nickname, email, phone, status, avatar, lastLoginTime, createdBy, createdTime, updatedBy, updatedTime);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_user")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="row.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<SysUserEntity> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_user")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="SysUserEntityResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="nickname", property="nickname", jdbcType=JdbcType.VARCHAR),
        @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
        @Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="avatar", property="avatar", jdbcType=JdbcType.VARCHAR),
        @Result(column="last_login_time", property="lastLoginTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="created_by", property="createdBy", jdbcType=JdbcType.BIGINT),
        @Result(column="created_time", property="createdTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="updated_by", property="updatedBy", jdbcType=JdbcType.BIGINT),
        @Result(column="updated_time", property="updatedTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SysUserEntity> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_user")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("SysUserEntityResult")
    Optional<SysUserEntity> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_user")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, sysUserEntity, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_user")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, sysUserEntity, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_user")
    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_user")
    default int insert(SysUserEntity row) {
        return MyBatis3Utils.insert(this::insert, row, sysUserEntity, c ->
            c.map(username).toProperty("username")
            .map(password).toProperty("password")
            .map(nickname).toProperty("nickname")
            .map(email).toProperty("email")
            .map(phone).toProperty("phone")
            .map(status).toProperty("status")
            .map(avatar).toProperty("avatar")
            .map(lastLoginTime).toProperty("lastLoginTime")
            .map(createdBy).toProperty("createdBy")
            .map(createdTime).toProperty("createdTime")
            .map(updatedBy).toProperty("updatedBy")
            .map(updatedTime).toProperty("updatedTime")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_user")
    default int insertSelective(SysUserEntity row) {
        return MyBatis3Utils.insert(this::insert, row, sysUserEntity, c ->
            c.map(username).toPropertyWhenPresent("username", row::getUsername)
            .map(password).toPropertyWhenPresent("password", row::getPassword)
            .map(nickname).toPropertyWhenPresent("nickname", row::getNickname)
            .map(email).toPropertyWhenPresent("email", row::getEmail)
            .map(phone).toPropertyWhenPresent("phone", row::getPhone)
            .map(status).toPropertyWhenPresent("status", row::getStatus)
            .map(avatar).toPropertyWhenPresent("avatar", row::getAvatar)
            .map(lastLoginTime).toPropertyWhenPresent("lastLoginTime", row::getLastLoginTime)
            .map(createdBy).toPropertyWhenPresent("createdBy", row::getCreatedBy)
            .map(createdTime).toPropertyWhenPresent("createdTime", row::getCreatedTime)
            .map(updatedBy).toPropertyWhenPresent("updatedBy", row::getUpdatedBy)
            .map(updatedTime).toPropertyWhenPresent("updatedTime", row::getUpdatedTime)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_user")
    default Optional<SysUserEntity> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, sysUserEntity, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_user")
    default List<SysUserEntity> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, sysUserEntity, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_user")
    default List<SysUserEntity> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, sysUserEntity, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_user")
    default Optional<SysUserEntity> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_user")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, sysUserEntity, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_user")
    static UpdateDSL<UpdateModel> updateAllColumns(SysUserEntity row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(username).equalTo(row::getUsername)
                .set(password).equalTo(row::getPassword)
                .set(nickname).equalTo(row::getNickname)
                .set(email).equalTo(row::getEmail)
                .set(phone).equalTo(row::getPhone)
                .set(status).equalTo(row::getStatus)
                .set(avatar).equalTo(row::getAvatar)
                .set(lastLoginTime).equalTo(row::getLastLoginTime)
                .set(createdBy).equalTo(row::getCreatedBy)
                .set(createdTime).equalTo(row::getCreatedTime)
                .set(updatedBy).equalTo(row::getUpdatedBy)
                .set(updatedTime).equalTo(row::getUpdatedTime);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_user")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(SysUserEntity row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(username).equalToWhenPresent(row::getUsername)
                .set(password).equalToWhenPresent(row::getPassword)
                .set(nickname).equalToWhenPresent(row::getNickname)
                .set(email).equalToWhenPresent(row::getEmail)
                .set(phone).equalToWhenPresent(row::getPhone)
                .set(status).equalToWhenPresent(row::getStatus)
                .set(avatar).equalToWhenPresent(row::getAvatar)
                .set(lastLoginTime).equalToWhenPresent(row::getLastLoginTime)
                .set(createdBy).equalToWhenPresent(row::getCreatedBy)
                .set(createdTime).equalToWhenPresent(row::getCreatedTime)
                .set(updatedBy).equalToWhenPresent(row::getUpdatedBy)
                .set(updatedTime).equalToWhenPresent(row::getUpdatedTime);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_user")
    default int updateByPrimaryKey(SysUserEntity row) {
        return update(c ->
            c.set(username).equalTo(row::getUsername)
            .set(password).equalTo(row::getPassword)
            .set(nickname).equalTo(row::getNickname)
            .set(email).equalTo(row::getEmail)
            .set(phone).equalTo(row::getPhone)
            .set(status).equalTo(row::getStatus)
            .set(avatar).equalTo(row::getAvatar)
            .set(lastLoginTime).equalTo(row::getLastLoginTime)
            .set(createdBy).equalTo(row::getCreatedBy)
            .set(createdTime).equalTo(row::getCreatedTime)
            .set(updatedBy).equalTo(row::getUpdatedBy)
            .set(updatedTime).equalTo(row::getUpdatedTime)
            .where(id, isEqualTo(row::getId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_user")
    default int updateByPrimaryKeySelective(SysUserEntity row) {
        return update(c ->
            c.set(username).equalToWhenPresent(row::getUsername)
            .set(password).equalToWhenPresent(row::getPassword)
            .set(nickname).equalToWhenPresent(row::getNickname)
            .set(email).equalToWhenPresent(row::getEmail)
            .set(phone).equalToWhenPresent(row::getPhone)
            .set(status).equalToWhenPresent(row::getStatus)
            .set(avatar).equalToWhenPresent(row::getAvatar)
            .set(lastLoginTime).equalToWhenPresent(row::getLastLoginTime)
            .set(createdBy).equalToWhenPresent(row::getCreatedBy)
            .set(createdTime).equalToWhenPresent(row::getCreatedTime)
            .set(updatedBy).equalToWhenPresent(row::getUpdatedBy)
            .set(updatedTime).equalToWhenPresent(row::getUpdatedTime)
            .where(id, isEqualTo(row::getId))
        );
    }
}