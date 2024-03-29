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
    , SQL_ERROR(993, "SQL 에러")
    , LOGIN_REQUIRED(992, "로그인이 필요한 API 입니다.")
    , PASSWORD_VALUE_NOT_PROVIDED(991, "비밀번호 값이 없습니다.")
    , NULL_POINTER(990, "NULL POINTER")
    , EXPIRED_TOKEN(989, "만료된 토큰 값 입니다.")
    , NO_TOKEN(988, "토큰 값이 없습니다.")
    , AUTH_ERROR(987, "유효하지 않은 인증 정보 입니다.")
    , UNSUPPORTED_ENCODING(986, "지원하지 않는 인코딩 방식 입니다.")
    , JSON_ERROR(985, "JSON Error")
    , RUNTIME_ERROR(984, "RUNTIME 에러")
    ;

    private int code;
    private String msg;
}
