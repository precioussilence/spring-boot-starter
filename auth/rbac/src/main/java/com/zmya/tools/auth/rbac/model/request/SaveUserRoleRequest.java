package com.zmya.tools.auth.rbac.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class SaveUserRoleRequest {

    @NotNull(message = "userId cannot be null")
    private Long userId;
    @NotEmpty(message = "roleIds cannot be empty")
    private List<Long> roleIds;

}
