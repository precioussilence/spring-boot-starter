package com.zmya.tools.auth.rbac.model.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class SaveResourceRequest {

    @NotEmpty(message = "resourceName cannot be empty")
    private String resourceName;
    @NotEmpty(message = "resourceCode cannot be empty")
    private String resourceCode;
    @NotEmpty(message = "resourceType cannot be null")
    private Integer resourceType;
    private Long parentId;
    private String path;
    private String icon;
    private Integer sort;
    private Integer visible;

}
