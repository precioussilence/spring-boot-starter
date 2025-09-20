package com.zmya.tools.data.core.model;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
public class SysApi {
    private Long id;

    private String apiName;

    private String url;

    private String method;

    private String description;

    private Integer status;

    private Instant createdTime;

    private Instant updatedTime;

    private Set<SysResourceApi> sysResourceApis = new LinkedHashSet<>();

}