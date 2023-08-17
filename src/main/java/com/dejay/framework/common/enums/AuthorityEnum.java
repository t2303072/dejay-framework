package com.dejay.framework.common.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum AuthorityEnum {
    NO_AUTHORITY("00000000"), DEVELOPMENT("00020001"), BUSINESS_SUPPORT("00020002");

    private String deptCode;

    AuthorityEnum(String deptCode) {
        this.deptCode = deptCode;
    }

    @JsonCreator
    public static AuthorityEnum from(String value) {
        for(AuthorityEnum auth : AuthorityEnum.values()) {
            if(auth.getDeptCode().equals(value)) {
                return auth;
            }
        }
        return null;
    }

    @JsonValue
    public String getValue() {
        return deptCode;
    }
}
