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
    , TEST("test")
    ;

    private String key;
}
