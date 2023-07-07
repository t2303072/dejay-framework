package com.dejay.framework.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MapKeyStringEnum {
    // Common
      RESULT_STATUS("resultStatus")
    , TEST("test")
    , DATA("data")
    , CODE("code")
    , PAGING("paging")
    , ROLES("roles")

    // Object
    , MEMBER("member")
    , MEMBER_VO("memberVO")
    , LOGIN_VO("loginVO")
    , TOKEN_VO("tokenVO")

    // Collection
    , MEMBER_LIST("memberList")
    , CODE_LIST("codeList")

    // Token
    , JWT_USERNAME("userName")
    ;

    private String keyString;
}
