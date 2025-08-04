package com.zmya.tools.auth.rbac.enums;

import lombok.Getter;

import java.util.Objects;

@Getter
public enum RoleStatusEnum {
    DISABLE(0, "禁用"),
    NORMAL(1, "正常");

    private final Integer status;
    private final String message;

    RoleStatusEnum(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public static RoleStatusEnum getByStatus(Integer status) {
        for (RoleStatusEnum roleStatusEnum : RoleStatusEnum.values()) {
            if (Objects.equals(roleStatusEnum.getStatus(), status)) {
                return roleStatusEnum;
            }
        }
        return null;
    }

}
