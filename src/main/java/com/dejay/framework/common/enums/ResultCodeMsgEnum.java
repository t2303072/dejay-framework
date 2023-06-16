package com.dejay.framework.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultCodeMsgEnum {

      SUCCESS(200, "success")
    , FAIL(400, "fail")
    , NO_DATA(1, "조회된 데이터가 없습니다.")
    ;

    private int code;
    private String msg;
}
