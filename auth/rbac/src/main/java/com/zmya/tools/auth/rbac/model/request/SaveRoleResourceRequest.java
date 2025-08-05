package com.zmya.tools.auth.rbac.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class SaveRoleResourceRequest {

    @NotNull(message = "roleId cannot be null")
    private Long roleId;
    @NotEmpty(message = "resourceIds cannot be empty")
    private List<Long> resourceIds;

}
