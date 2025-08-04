package com.zmya.tools.auth.rbac.model.dto;

import lombok.Data;

@Data
public class UserDTO {

    private Long userId;
    private String username;
    private String nickname;
    private String email;
    private String phone;
    private String avatar;
    private Integer status;
    private String createdTime;
    private String updatedTime;

}
