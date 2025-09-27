package com.zmya.tools.data.mybatis.entity;

import jakarta.annotation.Generated;
import java.util.Date;

public class SysResourceApiEntity {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_resource_api.id")
    private Long id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_resource_api.resource_id")
    private Long resourceId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_resource_api.api_id")
    private Long apiId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_resource_api.created_time")
    private Date createdTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_resource_api.id")
    public Long getId() {
        return id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_resource_api.id")
    public void setId(Long id) {
        this.id = id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_resource_api.resource_id")
    public Long getResourceId() {
        return resourceId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_resource_api.resource_id")
    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_resource_api.api_id")
    public Long getApiId() {
        return apiId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_resource_api.api_id")
    public void setApiId(Long apiId) {
        this.apiId = apiId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_resource_api.created_time")
    public Date getCreatedTime() {
        return createdTime;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_resource_api.created_time")
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }
}