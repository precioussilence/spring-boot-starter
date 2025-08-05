package com.zmya.tools.auth.rbac.enums;

import lombok.Getter;

import java.util.Objects;

@Getter
public enum ResourceStatusEnum {
    DISABLE(0, "禁用"),
    NORMAL(1, "正常");

    private final Integer status;
    private final String message;

    ResourceStatusEnum(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public static ResourceStatusEnum getByStatus(Integer status) {
        for (ResourceStatusEnum statusEnum : ResourceStatusEnum.values()) {
            if (Objects.equals(statusEnum.getStatus(), status)) {
                return statusEnum;
            }
        }
        return null;
    }
}
