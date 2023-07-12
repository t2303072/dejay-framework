package com.dejay.framework.common.utils;

import com.dejay.framework.domain.common.TokenObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class TokenFactory {

    private final JwtUtil jwtUtil;
    private Long accessExpiresAt = 1000l * 60 * 60; // 1 hour
    private Long refreshExpiresAt = 1000l * 60 * 60 * 24 * 14; // 14 days
    /**
     * JWT 생성
     * @param userName
     * @param password
     * @return
     */
    public TokenObject createJWT(String userName, String password, String[] list) {
        TokenObject tokenObject = jwtUtil.createTokenObject(userName, accessExpiresAt, refreshExpiresAt, list);
        return tokenObject;
    }
}
