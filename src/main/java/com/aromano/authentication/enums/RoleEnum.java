package com.aromano.authentication.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum RoleEnum {

    ROLE_ADMIN("ROLE_ADMIN");

    private String value;

    RoleEnum(String value) {
        this.value = value;
    }

    @Override public String toString() {
        return String.valueOf(this.value);
    }

    @JsonCreator
    public static RoleEnum fromValue(String text) {
        for (RoleEnum b : RoleEnum.values()) {
            if (String.valueOf(b.value).equals(text)) {
                return b;
            }
        }
        return null;
    }

    public String getValue() {
        return this.value;
    }
}
