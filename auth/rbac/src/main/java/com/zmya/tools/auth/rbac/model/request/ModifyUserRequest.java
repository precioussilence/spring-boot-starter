package com.zmya.tools.auth.rbac.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ModifyUserRequest {

    @NotNull(message = "userId cannot be null")
    private Long userId;
    private String nickname;
    private String email;
    private String phone;
    private String avatar;
    private Integer status;

}
