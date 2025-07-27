package com.zmya.tools.auth.rbac.response;

import lombok.Data;

import java.util.List;

@Data
public class SigninResponse {

    private String token;
    private List<String> authorities;

}
