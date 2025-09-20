package com.zmya.tools.data.core.model;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class SysUserRole {
    private Long id;

    private Long userId;

    private Long roleId;

    private Instant createdTime;

}