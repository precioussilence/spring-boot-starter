package com.zmya.tools.data.mybatis.entity;

import jakarta.annotation.Generated;
import java.util.Date;

public class SysUserEntity {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_user.id")
    private Long id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_user.username")
    private String username;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_user.password")
    private String password;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_user.nickname")
    private String nickname;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_user.email")
    private String email;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_user.phone")
    private String phone;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_user.status")
    private Integer status;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_user.avatar")
    private String avatar;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_user.last_login_time")
    private Date lastLoginTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_user.created_by")
    private Long createdBy;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_user.created_time")
    private Date createdTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_user.updated_by")
    private Long updatedBy;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_user.updated_time")
    private Date updatedTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_user.id")
    public Long getId() {
        return id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_user.id")
    public void setId(Long id) {
        this.id = id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_user.username")
    public String getUsername() {
        return username;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_user.username")
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_user.password")
    public String getPassword() {
        return password;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_user.password")
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_user.nickname")
    public String getNickname() {
        return nickname;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_user.nickname")
    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_user.email")
    public String getEmail() {
        return email;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_user.email")
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_user.phone")
    public String getPhone() {
        return phone;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_user.phone")
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_user.status")
    public Integer getStatus() {
        return status;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_user.status")
    public void setStatus(Integer status) {
        this.status = status;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_user.avatar")
    public String getAvatar() {
        return avatar;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_user.avatar")
    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_user.last_login_time")
    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_user.last_login_time")
    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_user.created_by")
    public Long getCreatedBy() {
        return createdBy;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_user.created_by")
    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_user.created_time")
    public Date getCreatedTime() {
        return createdTime;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_user.created_time")
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_user.updated_by")
    public Long getUpdatedBy() {
        return updatedBy;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_user.updated_by")
    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_user.updated_time")
    public Date getUpdatedTime() {
        return updatedTime;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: sys_user.updated_time")
    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }
}