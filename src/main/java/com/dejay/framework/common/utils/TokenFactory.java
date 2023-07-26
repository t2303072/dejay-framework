package com.dejay.framework.common.utils;

import com.dejay.framework.domain.common.TokenObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * TODO: IJ
 * 1. 만료 전 엑세스 토큰이 있는데 재발급 요청하면 엑세스 & refresh 토큰 둘 다 만료 시키기 => refresh 토큰은 DB에 저장
 * 2. 중복 로그인 처리
 * 3. OAuth 구현
 * 4. http-only 속성이 있는 쿠키에 저장 처리
 */

@Slf4j
@RequiredArgsConstructor
@Component
public class TokenFactory {

    private final JwtUtil jwtUtil;
    private Long accessExpiresAt = 1000L * 60 * 60; // 1 hour
    private Long refreshExpiresAt = 1000L * 60 * 60 * 24 * 14; // 14 days
    /**
     * JWT 생성
     * @param userName
     * @param password
     * @return
     */
    public TokenObject createJWT(String userName, String password, Set<?> roles) {
        TokenObject tokenObject = jwtUtil.createTokenObject(userName, accessExpiresAt, refreshExpiresAt, roles);
        return tokenObject;
    }
}
