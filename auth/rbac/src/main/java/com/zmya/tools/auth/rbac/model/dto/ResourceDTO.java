package com.zmya.tools.auth.rbac.model.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class ResourceDTO {

    private String resourceName;
    private String resourceCode;
    private Integer resourceType;
    private Long parentId;
    private String path;
    private String icon;
    private Integer sort;
    private Integer visible;
    private Integer status;
    private Instant createdTime;
    private Instant updatedTime;

}
