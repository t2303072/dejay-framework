package com.dejay.framework.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultCodeMsgEnum {

    SUCCESS(200, "success"), FAIL(400, "fail")
    ;

    private int code;
    private String msg;
}
