package com.zmya.tools.auth.rbac.enums;

import lombok.Getter;

import java.util.Objects;

@Getter
public enum UserStatusEnum {
    DISABLE(0, "禁用"),
    NORMAL(1, "正常");

    private final Integer status;
    private final String message;

    UserStatusEnum(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public static UserStatusEnum getByStatus(Integer status) {
        for (UserStatusEnum userStatusEnum : UserStatusEnum.values()) {
            if (Objects.equals(userStatusEnum.getStatus(), status)) {
                return userStatusEnum;
            }
        }
        return null;
    }
}
