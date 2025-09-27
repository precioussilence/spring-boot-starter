package com.zmya.tools.data.mybatis.mapper;

import jakarta.annotation.Generated;
import java.sql.JDBCType;
import java.util.Date;
import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

public final class SysUserEntityDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_user")
    public static final SysUserEntity sysUserEntity = new SysUserEntity();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_user.id")
    public static final SqlColumn<Long> id = sysUserEntity.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_user.username")
    public static final SqlColumn<String> username = sysUserEntity.username;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_user.password")
    public static final SqlColumn<String> password = sysUserEntity.password;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_user.nickname")
    public static final SqlColumn<String> nickname = sysUserEntity.nickname;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_user.email")
    public static final SqlColumn<String> email = sysUserEntity.email;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_user.phone")
    public static final SqlColumn<String> phone = sysUserEntity.phone;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_user.status")
    public static final SqlColumn<Integer> status = sysUserEntity.status;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_user.avatar")
    public static final SqlColumn<String> avatar = sysUserEntity.avatar;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_user.last_login_time")
    public static final SqlColumn<Date> lastLoginTime = sysUserEntity.lastLoginTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_user.created_by")
    public static final SqlColumn<Long> createdBy = sysUserEntity.createdBy;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_user.created_time")
    public static final SqlColumn<Date> createdTime = sysUserEntity.createdTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_user.updated_by")
    public static final SqlColumn<Long> updatedBy = sysUserEntity.updatedBy;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_user.updated_time")
    public static final SqlColumn<Date> updatedTime = sysUserEntity.updatedTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_user")
    public static final class SysUserEntity extends AliasableSqlTable<SysUserEntity> {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> username = column("username", JDBCType.VARCHAR);

        public final SqlColumn<String> password = column("password", JDBCType.VARCHAR);

        public final SqlColumn<String> nickname = column("nickname", JDBCType.VARCHAR);

        public final SqlColumn<String> email = column("email", JDBCType.VARCHAR);

        public final SqlColumn<String> phone = column("phone", JDBCType.VARCHAR);

        public final SqlColumn<Integer> status = column("status", JDBCType.INTEGER);

        public final SqlColumn<String> avatar = column("avatar", JDBCType.VARCHAR);

        public final SqlColumn<Date> lastLoginTime = column("last_login_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Long> createdBy = column("created_by", JDBCType.BIGINT);

        public final SqlColumn<Date> createdTime = column("created_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Long> updatedBy = column("updated_by", JDBCType.BIGINT);

        public final SqlColumn<Date> updatedTime = column("updated_time", JDBCType.TIMESTAMP);

        public SysUserEntity() {
            super("sys_user", SysUserEntity::new);
        }
    }
}