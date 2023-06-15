package com.dejay.framework.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ExceptionCodeMsgEnum {

      ERR_INVALID_PARAM_EXISTS(999, "유효하지 않은 매개변수 값이 존재합니다.")
    , ERR_INVALID_NUMBER_FORMAT(998, "잘못된 숫자 형식 입니다.")
    , ERR_CLASS_NOT_FOUND(997, "클래스를 찾을 수 없습니다.")
    ;

    private int code;
    private String msg;
}
