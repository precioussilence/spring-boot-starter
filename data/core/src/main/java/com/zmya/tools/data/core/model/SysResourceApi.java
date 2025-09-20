package com.zmya.tools.data.core.model;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class SysResourceApi {
    private Long id;

    private SysResource resource;

    private SysApi api;

    private Instant createdTime;

}