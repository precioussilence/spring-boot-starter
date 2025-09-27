package com.zmya.tools.data.mybatis.mapper;

import jakarta.annotation.Generated;
import java.sql.JDBCType;
import java.util.Date;
import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

public final class SysRoleResourceEntityDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_role_resource")
    public static final SysRoleResourceEntity sysRoleResourceEntity = new SysRoleResourceEntity();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_role_resource.id")
    public static final SqlColumn<Long> id = sysRoleResourceEntity.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_role_resource.role_id")
    public static final SqlColumn<Long> roleId = sysRoleResourceEntity.roleId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_role_resource.resource_id")
    public static final SqlColumn<Long> resourceId = sysRoleResourceEntity.resourceId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_role_resource.created_time")
    public static final SqlColumn<Date> createdTime = sysRoleResourceEntity.createdTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_role_resource")
    public static final class SysRoleResourceEntity extends AliasableSqlTable<SysRoleResourceEntity> {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> roleId = column("role_id", JDBCType.BIGINT);

        public final SqlColumn<Long> resourceId = column("resource_id", JDBCType.BIGINT);

        public final SqlColumn<Date> createdTime = column("created_time", JDBCType.TIMESTAMP);

        public SysRoleResourceEntity() {
            super("sys_role_resource", SysRoleResourceEntity::new);
        }
    }
}