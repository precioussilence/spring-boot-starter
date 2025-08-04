package com.zmya.tools.auth.rbac.error;

import lombok.Getter;

@Getter
public enum ErrorCodeEnum {
    USER_NOT_FOUND(400001, "User Not Found"),
    ;

    private final int code;
    private final String message;

    ErrorCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
