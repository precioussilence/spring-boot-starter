package com.zmya.tools.auth.rbac.request;

import lombok.Data;

@Data
public class SaveUserRequest {

    private String username;
    private String password;
    private String nickname;
    private String email;
    private String phone;
    private String avatar;

}
