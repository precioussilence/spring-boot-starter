package com.zmya.tools.auth.rbac.model.request;

import lombok.Data;

@Data
public class SignupRequest {

    private String username;
    private String password;

}
