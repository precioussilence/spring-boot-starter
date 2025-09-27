package com.zmya.tools.data.mybatis.mapper;

import jakarta.annotation.Generated;
import java.sql.JDBCType;
import java.util.Date;
import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

public final class SysRoleEntityDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_role")
    public static final SysRoleEntity sysRoleEntity = new SysRoleEntity();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_role.id")
    public static final SqlColumn<Long> id = sysRoleEntity.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_role.role_code")
    public static final SqlColumn<String> roleCode = sysRoleEntity.roleCode;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_role.role_name")
    public static final SqlColumn<String> roleName = sysRoleEntity.roleName;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_role.description")
    public static final SqlColumn<String> description = sysRoleEntity.description;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_role.status")
    public static final SqlColumn<Integer> status = sysRoleEntity.status;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_role.created_by")
    public static final SqlColumn<Long> createdBy = sysRoleEntity.createdBy;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_role.created_time")
    public static final SqlColumn<Date> createdTime = sysRoleEntity.createdTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_role.updated_by")
    public static final SqlColumn<Long> updatedBy = sysRoleEntity.updatedBy;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_role.updated_time")
    public static final SqlColumn<Date> updatedTime = sysRoleEntity.updatedTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_role")
    public static final class SysRoleEntity extends AliasableSqlTable<SysRoleEntity> {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> roleCode = column("role_code", JDBCType.VARCHAR);

        public final SqlColumn<String> roleName = column("role_name", JDBCType.VARCHAR);

        public final SqlColumn<String> description = column("description", JDBCType.VARCHAR);

        public final SqlColumn<Integer> status = column("status", JDBCType.INTEGER);

        public final SqlColumn<Long> createdBy = column("created_by", JDBCType.BIGINT);

        public final SqlColumn<Date> createdTime = column("created_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Long> updatedBy = column("updated_by", JDBCType.BIGINT);

        public final SqlColumn<Date> updatedTime = column("updated_time", JDBCType.TIMESTAMP);

        public SysRoleEntity() {
            super("sys_role", SysRoleEntity::new);
        }
    }
}