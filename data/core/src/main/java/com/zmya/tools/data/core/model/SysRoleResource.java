package com.zmya.tools.data.core.model;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class SysRoleResource {
    private Long id;

    private SysRole role;

    private SysResource resource;

    private Instant createdTime;

}