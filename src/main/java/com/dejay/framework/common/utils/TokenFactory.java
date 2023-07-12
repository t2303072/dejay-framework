package com.dejay.framework.common.utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class TokenFactory {

    private final JwtUtil jwtUtil;
    private Long expiredMs = 1000 * 60 * 60l;
    /**
     * JWT 생성
     * @param userName
     * @param password
     * @return
     */
    public String createJWT(String userName, String password, String[] list) {
        return jwtUtil.createJwt(userName, expiredMs, list);
    }
}
