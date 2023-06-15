package com.dejay.framework.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionCodeMsgEnum {

    ERR_INVALID_PARAM_EXISTS(999, "유효하지 않은 매개변수 값이 존재합니다.")
    ;

    private int code;
    private String msg;
}
