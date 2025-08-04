package com.zmya.tools.auth.rbac.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ModifyRoleRequest {

    @NotNull(message = "roleId cannot be null")
    private Long roleId;
    private String roleCode;
    private String roleName;
    private String description;
    private Integer status;

}
