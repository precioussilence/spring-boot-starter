package com.zmya.tools.data.core.model;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
public class SysResource {
    private Long id;

    private String resourceName;

    private String resourceCode;

    private Integer resourceType;

    private Long parentId;

    private String path;

    private String icon;

    private Integer sort;

    private Integer visible;

    private Integer status;

    private Long createdBy;

    private Instant createdTime;

    private Long updatedBy;

    private Instant updatedTime;

    private Set<SysResourceApi> sysResourceApis = new LinkedHashSet<>();

    private Set<SysRoleResource> sysRoleResources = new LinkedHashSet<>();

}