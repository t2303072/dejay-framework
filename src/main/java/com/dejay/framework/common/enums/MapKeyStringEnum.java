package com.dejay.framework.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MapKeyStringEnum {

      RESULT_STATUS("resultStatus")
    , DATA("data")
    , MEMBER("member")
    , MEMBER_LIST("memberList")
    , CODE("code")
    , CODE_LIST("codeList")
    , TEST("test")
    , PAGING("paging")
    , TOKEN_VO("tokenVO")
    ;

    private String keyString;
}
