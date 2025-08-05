package com.zmya.tools.auth.rbac.model.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class RoleDTO {

    private Long id;
    private String roleCode;
    private String roleName;
    private String description;
    private Integer status;
    private Instant createdTime;
    private Instant updatedTime;

    private Boolean owned;

}
