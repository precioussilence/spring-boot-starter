package com.zmya.tools.data.mybatis.mapper;

import jakarta.annotation.Generated;
import java.sql.JDBCType;
import java.util.Date;
import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

public final class SysUserRoleEntityDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_user_role")
    public static final SysUserRoleEntity sysUserRoleEntity = new SysUserRoleEntity();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_user_role.id")
    public static final SqlColumn<Long> id = sysUserRoleEntity.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_user_role.user_id")
    public static final SqlColumn<Long> userId = sysUserRoleEntity.userId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_user_role.role_id")
    public static final SqlColumn<Long> roleId = sysUserRoleEntity.roleId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_user_role.created_time")
    public static final SqlColumn<Date> createdTime = sysUserRoleEntity.createdTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_user_role")
    public static final class SysUserRoleEntity extends AliasableSqlTable<SysUserRoleEntity> {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> userId = column("user_id", JDBCType.BIGINT);

        public final SqlColumn<Long> roleId = column("role_id", JDBCType.BIGINT);

        public final SqlColumn<Date> createdTime = column("created_time", JDBCType.TIMESTAMP);

        public SysUserRoleEntity() {
            super("sys_user_role", SysUserRoleEntity::new);
        }
    }
}