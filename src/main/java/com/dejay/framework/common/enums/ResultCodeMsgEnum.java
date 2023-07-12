package com.dejay.framework.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultCodeMsgEnum {

      SUCCESS(200, "success")
    , FAIL(400, "fail")
    , NO_DATA(1, "조회된 데이터가 없습니다.")
    , NOT_LOGGED_IN(2, "로그인 정보가 없습니다.")
    , NO_COOKIE(3, "해당 쿠키 정보가 없습니다.")
    , NO_HEADER_AUTHORIZATION(4, "NO AUTHORIZATION HEADER")
    , INVALID_HEADER_AUTHORIZATION(5, "잘못된 권한 헤더 값 입니다.")
    , TOKEN_EXPIRED(6, "만료된 토큰 입니다.")
    ;

    private int code;
    private String msg;
}
