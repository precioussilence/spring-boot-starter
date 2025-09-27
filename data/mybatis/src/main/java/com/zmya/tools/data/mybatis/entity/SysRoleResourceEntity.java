package com.zmya.tools.data.mybatis.entity;

import jakarta.annotation.Generated;
import java.util.Date;

public class SysRoleResourceEntity {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_role_resource.id")
    private Long id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_role_resource.role_id")
    private Long roleId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_role_resource.resource_id")
    private Long resourceId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_role_resource.created_time")
    private Date createdTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_role_resource.id")
    public Long getId() {
        return id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_role_resource.id")
    public void setId(Long id) {
        this.id = id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_role_resource.role_id")
    public Long getRoleId() {
        return roleId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_role_resource.role_id")
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_role_resource.resource_id")
    public Long getResourceId() {
        return resourceId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_role_resource.resource_id")
    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_role_resource.created_time")
    public Date getCreatedTime() {
        return createdTime;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_role_resource.created_time")
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }
}