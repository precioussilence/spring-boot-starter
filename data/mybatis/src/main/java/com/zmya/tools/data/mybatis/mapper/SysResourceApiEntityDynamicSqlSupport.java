package com.zmya.tools.data.mybatis.mapper;

import jakarta.annotation.Generated;
import java.sql.JDBCType;
import java.util.Date;
import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

public final class SysResourceApiEntityDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_resource_api")
    public static final SysResourceApiEntity sysResourceApiEntity = new SysResourceApiEntity();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_resource_api.id")
    public static final SqlColumn<Long> id = sysResourceApiEntity.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_resource_api.resource_id")
    public static final SqlColumn<Long> resourceId = sysResourceApiEntity.resourceId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_resource_api.api_id")
    public static final SqlColumn<Long> apiId = sysResourceApiEntity.apiId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_resource_api.created_time")
    public static final SqlColumn<Date> createdTime = sysResourceApiEntity.createdTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_resource_api")
    public static final class SysResourceApiEntity extends AliasableSqlTable<SysResourceApiEntity> {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> resourceId = column("resource_id", JDBCType.BIGINT);

        public final SqlColumn<Long> apiId = column("api_id", JDBCType.BIGINT);

        public final SqlColumn<Date> createdTime = column("created_time", JDBCType.TIMESTAMP);

        public SysResourceApiEntity() {
            super("sys_resource_api", SysResourceApiEntity::new);
        }
    }
}