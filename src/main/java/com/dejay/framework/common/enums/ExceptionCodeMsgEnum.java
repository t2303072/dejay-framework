package com.dejay.framework.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ExceptionCodeMsgEnum {

      INVALID_ARGUMENT_EXISTS(999, "유효하지 않은 인자 값이 존재합니다.")
    , INVALID_NUMBER_FORMAT(998, "잘못된 숫자 형식 입니다.")
    , CLASS_NOT_FOUND(997, "클래스를 찾을 수 없습니다.")
    , SERVER_DATA_ERROR(996, "서버 데이터 에러")
    , NOT_EQUAL_OBJECT_SIZE(995, "비교 객체의 사이즈가 불일치 합니다.")
    , INVALID_PARAMETER_BINDING(994, "유효하지 않은 매개변수 값 입니다.")
    ;

    private int code;
    private String msg;
}
