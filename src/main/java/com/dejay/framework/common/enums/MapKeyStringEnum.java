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
    , AUTH("authority")

    // Object
    , MEMBER("member")
    , MEMBER_VO("memberVO")
    , LOGIN_VO("loginVO")
    , TOKEN_VO("tokenVO")
    , TOKEN_OBJECT("tokenObject")
    , BOARD("board")
    , USER("user")

    // Collection
    , MEMBER_LIST("memberList")
    , CODE_LIST("codeList")
    , BOARD_LIST("boardList")

    // Token
    , JWT_USERNAME("userName")
    , JWT_ROLES("roles")
    , TOKEN_REISSUE("reissue")
    ;

    private String keyString;
}
