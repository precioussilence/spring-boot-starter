package com.zmya.tools.auth.rbac.model.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class ApiDTO {

    private String apiName;
    private String url;
    private String method;
    private String description;
    private Integer status;
    private Instant createdTime;

}
