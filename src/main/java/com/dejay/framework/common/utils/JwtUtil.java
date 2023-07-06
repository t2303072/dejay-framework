package com.dejay.framework.common.utils;

import com.dejay.framework.vo.common.TokenVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.Base64;
import java.util.Date;
import java.util.Map;

@Slf4j
public class JwtUtil {

    /**
     * JWT 유저명 조회
     * @param token
     * @param secretKey
     * @return
     */
    public static String getUserName(String token, String secretKey) {
        return Jwts.parserBuilder().setSigningKey(secretKey).build()
                .parseClaimsJws(token).getBody().get("userName", String.class);
    }

    /**
     * JWT 만료 여부
     * @param token
     * @param secretKey
     * @return
     */
    public static boolean isExpired(String token, String secretKey) {
        return Jwts.parserBuilder().setSigningKey(secretKey).build()
                .parseClaimsJws(token).getBody().getExpiration().before(new Date());
    }

    /**
     * JWT 생성
     * @param userName String
     * @param secretKey String
     * @param expiredMs Long
     * @return
     */
    public static String createJwt(String userName, String secretKey, Long expiredMs) {
        Claims claims = Jwts.claims();
        claims.put("userName", userName);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiredMs))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    /**
     * JWT decoder
     * @param token String
     * @return
     * @throws JsonProcessingException
     */
    public static TokenVO decode(String token) throws JsonProcessingException {
        if(!StringUtils.hasText(token)) throw new NullPointerException("토큰 값이 없습니다.");

        String[] chunk = token.split("\\.");
        Base64.Decoder decoder = Base64.getDecoder();
        String payload = new String(decoder.decode(chunk[1]));
        log.info("Decoded Token: {}", payload);

        ObjectMapper mapper = new ObjectMapper();
        TokenVO tokenVO = mapper.readValue(payload, TokenVO.class);

        return tokenVO;
    }
}
