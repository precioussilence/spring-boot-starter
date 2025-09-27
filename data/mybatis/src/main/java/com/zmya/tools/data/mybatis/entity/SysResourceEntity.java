package com.zmya.tools.data.mybatis.entity;

import jakarta.annotation.Generated;
import java.util.Date;

public class SysResourceEntity {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_resource.id")
    private Long id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_resource.resource_name")
    private String resourceName;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_resource.resource_code")
    private String resourceCode;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_resource.resource_type")
    private Integer resourceType;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_resource.parent_id")
    private Long parentId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_resource.path")
    private String path;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_resource.icon")
    private String icon;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_resource.sort")
    private Integer sort;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_resource.visible")
    private Integer visible;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_resource.status")
    private Integer status;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_resource.created_by")
    private Long createdBy;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_resource.created_time")
    private Date createdTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_resource.updated_by")
    private Long updatedBy;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_resource.updated_time")
    private Date updatedTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_resource.id")
    public Long getId() {
        return id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_resource.id")
    public void setId(Long id) {
        this.id = id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_resource.resource_name")
    public String getResourceName() {
        return resourceName;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_resource.resource_name")
    public void setResourceName(String resourceName) {
        this.resourceName = resourceName == null ? null : resourceName.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_resource.resource_code")
    public String getResourceCode() {
        return resourceCode;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_resource.resource_code")
    public void setResourceCode(String resourceCode) {
        this.resourceCode = resourceCode == null ? null : resourceCode.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_resource.resource_type")
    public Integer getResourceType() {
        return resourceType;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_resource.resource_type")
    public void setResourceType(Integer resourceType) {
        this.resourceType = resourceType;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_resource.parent_id")
    public Long getParentId() {
        return parentId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_resource.parent_id")
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_resource.path")
    public String getPath() {
        return path;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_resource.path")
    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_resource.icon")
    public String getIcon() {
        return icon;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_resource.icon")
    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_resource.sort")
    public Integer getSort() {
        return sort;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_resource.sort")
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_resource.visible")
    public Integer getVisible() {
        return visible;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_resource.visible")
    public void setVisible(Integer visible) {
        this.visible = visible;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_resource.status")
    public Integer getStatus() {
        return status;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_resource.status")
    public void setStatus(Integer status) {
        this.status = status;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_resource.created_by")
    public Long getCreatedBy() {
        return createdBy;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_resource.created_by")
    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_resource.created_time")
    public Date getCreatedTime() {
        return createdTime;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_resource.created_time")
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_resource.updated_by")
    public Long getUpdatedBy() {
        return updatedBy;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_resource.updated_by")
    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_resource.updated_time")
    public Date getUpdatedTime() {
        return updatedTime;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_resource.updated_time")
    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }
}