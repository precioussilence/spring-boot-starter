package com.zmya.tools.data.core.model;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
public class SysRole {
    private Long id;

    private String roleCode;

    private String roleName;

    private String description;

    private Integer status;

    private Long createdBy;

    private Instant createdTime;

    private Long updatedBy;

    private Instant updatedTime;

    private Set<SysRoleResource> sysRoleResources = new LinkedHashSet<>();

    private Set<SysUserRole> sysUserRoles = new LinkedHashSet<>();

}