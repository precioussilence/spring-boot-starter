package com.zmya.tools.auth.rbac.model.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class SaveUserRequest {

    @NotEmpty(message = "username cannot be empty")
    private String username;
    @NotEmpty(message = "password cannot be empty")
    private String password;
    private String nickname;
    private String email;
    private String phone;
    private String avatar;

}
