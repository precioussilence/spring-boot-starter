package com.zmya.tools.auth.rbac.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ModifyResourceRequest {

    @NotNull(message = "resourceId cannot be null")
    private Long resourceId;
    private String resourceName;
    private String resourceCode;
    private Integer resourceType;
    private Long parentId;
    private String path;
    private String icon;
    private Integer sort;
    private Integer visible;

}
