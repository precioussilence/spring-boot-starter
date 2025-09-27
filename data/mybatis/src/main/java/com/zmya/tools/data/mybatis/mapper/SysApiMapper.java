package com.zmya.tools.data.mybatis.mapper;

import static com.zmya.tools.data.mybatis.mapper.SysApiEntityDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

import com.zmya.tools.data.mybatis.entity.SysApiEntity;
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
public interface SysApiMapper extends CommonCountMapper, CommonDeleteMapper, CommonUpdateMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_api")
    BasicColumn[] selectList = BasicColumn.columnList(id, apiName, url, method, description, status, createdTime, updatedTime);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_api")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="row.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<SysApiEntity> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_api")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="SysApiEntityResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="api_name", property="apiName", jdbcType=JdbcType.VARCHAR),
        @Result(column="url", property="url", jdbcType=JdbcType.VARCHAR),
        @Result(column="method", property="method", jdbcType=JdbcType.VARCHAR),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="created_time", property="createdTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="updated_time", property="updatedTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SysApiEntity> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_api")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("SysApiEntityResult")
    Optional<SysApiEntity> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_api")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, sysApiEntity, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_api")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, sysApiEntity, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_api")
    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_api")
    default int insert(SysApiEntity row) {
        return MyBatis3Utils.insert(this::insert, row, sysApiEntity, c ->
            c.map(apiName).toProperty("apiName")
            .map(url).toProperty("url")
            .map(method).toProperty("method")
            .map(description).toProperty("description")
            .map(status).toProperty("status")
            .map(createdTime).toProperty("createdTime")
            .map(updatedTime).toProperty("updatedTime")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_api")
    default int insertSelective(SysApiEntity row) {
        return MyBatis3Utils.insert(this::insert, row, sysApiEntity, c ->
            c.map(apiName).toPropertyWhenPresent("apiName", row::getApiName)
            .map(url).toPropertyWhenPresent("url", row::getUrl)
            .map(method).toPropertyWhenPresent("method", row::getMethod)
            .map(description).toPropertyWhenPresent("description", row::getDescription)
            .map(status).toPropertyWhenPresent("status", row::getStatus)
            .map(createdTime).toPropertyWhenPresent("createdTime", row::getCreatedTime)
            .map(updatedTime).toPropertyWhenPresent("updatedTime", row::getUpdatedTime)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_api")
    default Optional<SysApiEntity> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, sysApiEntity, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_api")
    default List<SysApiEntity> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, sysApiEntity, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_api")
    default List<SysApiEntity> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, sysApiEntity, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_api")
    default Optional<SysApiEntity> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_api")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, sysApiEntity, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_api")
    static UpdateDSL<UpdateModel> updateAllColumns(SysApiEntity row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(apiName).equalTo(row::getApiName)
                .set(url).equalTo(row::getUrl)
                .set(method).equalTo(row::getMethod)
                .set(description).equalTo(row::getDescription)
                .set(status).equalTo(row::getStatus)
                .set(createdTime).equalTo(row::getCreatedTime)
                .set(updatedTime).equalTo(row::getUpdatedTime);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_api")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(SysApiEntity row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(apiName).equalToWhenPresent(row::getApiName)
                .set(url).equalToWhenPresent(row::getUrl)
                .set(method).equalToWhenPresent(row::getMethod)
                .set(description).equalToWhenPresent(row::getDescription)
                .set(status).equalToWhenPresent(row::getStatus)
                .set(createdTime).equalToWhenPresent(row::getCreatedTime)
                .set(updatedTime).equalToWhenPresent(row::getUpdatedTime);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_api")
    default int updateByPrimaryKey(SysApiEntity row) {
        return update(c ->
            c.set(apiName).equalTo(row::getApiName)
            .set(url).equalTo(row::getUrl)
            .set(method).equalTo(row::getMethod)
            .set(description).equalTo(row::getDescription)
            .set(status).equalTo(row::getStatus)
            .set(createdTime).equalTo(row::getCreatedTime)
            .set(updatedTime).equalTo(row::getUpdatedTime)
            .where(id, isEqualTo(row::getId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_api")
    default int updateByPrimaryKeySelective(SysApiEntity row) {
        return update(c ->
            c.set(apiName).equalToWhenPresent(row::getApiName)
            .set(url).equalToWhenPresent(row::getUrl)
            .set(method).equalToWhenPresent(row::getMethod)
            .set(description).equalToWhenPresent(row::getDescription)
            .set(status).equalToWhenPresent(row::getStatus)
            .set(createdTime).equalToWhenPresent(row::getCreatedTime)
            .set(updatedTime).equalToWhenPresent(row::getUpdatedTime)
            .where(id, isEqualTo(row::getId))
        );
    }
}