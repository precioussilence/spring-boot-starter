package com.zmya.tools.data.mybatis.mapper;

import jakarta.annotation.Generated;
import java.sql.JDBCType;
import java.util.Date;
import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

public final class SysResourceEntityDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_resource")
    public static final SysResourceEntity sysResourceEntity = new SysResourceEntity();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_resource.id")
    public static final SqlColumn<Long> id = sysResourceEntity.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_resource.resource_name")
    public static final SqlColumn<String> resourceName = sysResourceEntity.resourceName;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_resource.resource_code")
    public static final SqlColumn<String> resourceCode = sysResourceEntity.resourceCode;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_resource.resource_type")
    public static final SqlColumn<Integer> resourceType = sysResourceEntity.resourceType;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_resource.parent_id")
    public static final SqlColumn<Long> parentId = sysResourceEntity.parentId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_resource.path")
    public static final SqlColumn<String> path = sysResourceEntity.path;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_resource.icon")
    public static final SqlColumn<String> icon = sysResourceEntity.icon;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_resource.sort")
    public static final SqlColumn<Integer> sort = sysResourceEntity.sort;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_resource.visible")
    public static final SqlColumn<Integer> visible = sysResourceEntity.visible;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_resource.status")
    public static final SqlColumn<Integer> status = sysResourceEntity.status;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_resource.created_by")
    public static final SqlColumn<Long> createdBy = sysResourceEntity.createdBy;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_resource.created_time")
    public static final SqlColumn<Date> createdTime = sysResourceEntity.createdTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_resource.updated_by")
    public static final SqlColumn<Long> updatedBy = sysResourceEntity.updatedBy;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_resource.updated_time")
    public static final SqlColumn<Date> updatedTime = sysResourceEntity.updatedTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: sys_resource")
    public static final class SysResourceEntity extends AliasableSqlTable<SysResourceEntity> {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> resourceName = column("resource_name", JDBCType.VARCHAR);

        public final SqlColumn<String> resourceCode = column("resource_code", JDBCType.VARCHAR);

        public final SqlColumn<Integer> resourceType = column("resource_type", JDBCType.INTEGER);

        public final SqlColumn<Long> parentId = column("parent_id", JDBCType.BIGINT);

        public final SqlColumn<String> path = column("path", JDBCType.VARCHAR);

        public final SqlColumn<String> icon = column("icon", JDBCType.VARCHAR);

        public final SqlColumn<Integer> sort = column("sort", JDBCType.INTEGER);

        public final SqlColumn<Integer> visible = column("visible", JDBCType.INTEGER);

        public final SqlColumn<Integer> status = column("status", JDBCType.INTEGER);

        public final SqlColumn<Long> createdBy = column("created_by", JDBCType.BIGINT);

        public final SqlColumn<Date> createdTime = column("created_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Long> updatedBy = column("updated_by", JDBCType.BIGINT);

        public final SqlColumn<Date> updatedTime = column("updated_time", JDBCType.TIMESTAMP);

        public SysResourceEntity() {
            super("sys_resource", SysResourceEntity::new);
        }
    }
}