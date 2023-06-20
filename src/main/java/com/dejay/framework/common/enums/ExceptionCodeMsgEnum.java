package com.dejay.framework.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ExceptionCodeMsgEnum {

      INVALID_PARAM_EXISTS_ERROR(999, "유효하지 않은 매개변수 값이 존재합니다.")
    , INVALID_NUMBER_FORMAT_ERROR(998, "잘못된 숫자 형식 입니다.")
    , CLASS_NOT_FOUND_ERROR(997, "클래스를 찾을 수 없습니다.")
    , SERVER_DATA_ERROR(996, "서버 데이터 에러")
    ;

    private int code;
    private String msg;
}
