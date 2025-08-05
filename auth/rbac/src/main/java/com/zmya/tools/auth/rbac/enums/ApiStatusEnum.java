package com.zmya.tools.auth.rbac.enums;

import lombok.Getter;

import java.util.Objects;

@Getter
public enum ApiStatusEnum {
    DISABLE(0, "禁用"),
    NORMAL(1, "正常");

    private final Integer status;
    private final String message;

    ApiStatusEnum(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public static ApiStatusEnum getByStatus(Integer status) {
        for (ApiStatusEnum statusEnum : ApiStatusEnum.values()) {
            if (Objects.equals(statusEnum.getStatus(), status)) {
                return statusEnum;
            }
        }
        return null;
    }
}
