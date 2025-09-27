package com.zmya.tools.data.mybatis.mapper;

import jakarta.annotation.Generated;
import java.sql.JDBCType;
import java.util.Date;
import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

public final class SysApiEntityDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_api")
    public static final SysApiEntity sysApiEntity = new SysApiEntity();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_api.id")
    public static final SqlColumn<Long> id = sysApiEntity.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_api.api_name")
    public static final SqlColumn<String> apiName = sysApiEntity.apiName;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_api.url")
    public static final SqlColumn<String> url = sysApiEntity.url;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_api.method")
    public static final SqlColumn<String> method = sysApiEntity.method;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_api.description")
    public static final SqlColumn<String> description = sysApiEntity.description;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_api.status")
    public static final SqlColumn<Integer> status = sysApiEntity.status;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_api.created_time")
    public static final SqlColumn<Date> createdTime = sysApiEntity.createdTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_api.updated_time")
    public static final SqlColumn<Date> updatedTime = sysApiEntity.updatedTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_api")
    public static final class SysApiEntity extends AliasableSqlTable<SysApiEntity> {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> apiName = column("api_name", JDBCType.VARCHAR);

        public final SqlColumn<String> url = column("url", JDBCType.VARCHAR);

        public final SqlColumn<String> method = column("method", JDBCType.VARCHAR);

        public final SqlColumn<String> description = column("description", JDBCType.VARCHAR);

        public final SqlColumn<Integer> status = column("status", JDBCType.INTEGER);

        public final SqlColumn<Date> createdTime = column("created_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> updatedTime = column("updated_time", JDBCType.TIMESTAMP);

        public SysApiEntity() {
            super("sys_api", SysApiEntity::new);
        }
    }
}