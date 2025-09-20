package com.zmya.tools.data.core.model;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
public class SysUser {
    private Long id;

    private String username;

    private String password;

    private String nickname;

    private String email;

    private String phone;

    private Integer status;

    private String avatar;

    private Instant lastLoginTime;

    private Long createdBy;

    private Instant createdTime;

    private Long updatedBy;

    private Instant updatedTime;

    private Set<SysUserRole> sysUserRoles = new LinkedHashSet<>();

}