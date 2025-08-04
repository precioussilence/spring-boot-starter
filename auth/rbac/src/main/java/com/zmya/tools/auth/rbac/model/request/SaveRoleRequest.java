package com.zmya.tools.auth.rbac.model.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class SaveRoleRequest {

    @NotEmpty(message = "roleCode cannot be empty")
    private String roleCode;
    @NotEmpty(message = "roleName cannot be empty")
    private String roleName;
    private String description;

}
