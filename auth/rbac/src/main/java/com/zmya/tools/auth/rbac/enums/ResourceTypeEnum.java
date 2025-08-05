package com.zmya.tools.auth.rbac.enums;

import lombok.Getter;

import java.util.Objects;

@Getter
public enum ResourceTypeEnum {
    MENU(0, "菜单"),
    PAGE(1, "页面"),
    BUTTON(1, "按钮");

    private final Integer type;
    private final String message;

    ResourceTypeEnum(int type, String message) {
        this.type = type;
        this.message = message;
    }

    public static ResourceTypeEnum getByType(Integer type) {
        for (ResourceTypeEnum statusEnum : ResourceTypeEnum.values()) {
            if (Objects.equals(statusEnum.getType(), type)) {
                return statusEnum;
            }
        }
        return null;
    }
}
