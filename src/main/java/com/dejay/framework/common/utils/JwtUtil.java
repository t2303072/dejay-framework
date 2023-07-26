package com.dejay.framework.common.utils;

import com.dejay.framework.common.enums.ExceptionCodeMsgEnum;
import com.dejay.framework.common.enums.MapKeyStringEnum;
import com.dejay.framework.domain.common.TokenObject;
import com.dejay.framework.vo.common.TokenVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtUtil {

    private final UserDetailsService userDetailsService;

    @Value("${jwt.secret}")
    private String secretKey;
    @Value("${jwt.issuer}")
    private String issuer;

    @PostConstruct
    protected void encodeKey() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    /**
     * 토큰 객체 생성
     * @param userName {@link String}
     * @param accessExpiresAt {@link Long}
     * @param refreshExpiresAt {@link Long}
     * @param roles {@link Arrays}
     * @return
     */
    public TokenObject createTokenObject(String userName, Long accessExpiresAt, Long refreshExpiresAt, Set<?> roles) {
        String accessToken = this.generateJwt(userName, accessExpiresAt, roles);
        String refreshToken = this.generateJwt(userName, refreshExpiresAt, roles);
        return TokenObject.builder().accessToken(accessToken).refreshToken(refreshToken).key(userName).build();
    }

    /**
     * JWT 생성
     * @param userName {@link String}
     * @param expiredMs {@link Long}
     * @return
     */
    public String generateJwt(String userName, Long expiredMs, Set<?> roles) {
        Claims claims = Jwts.claims().setSubject(userName);
        claims.put(MapKeyStringEnum.JWT_USERNAME.getKeyString(), userName);
        claims.put(MapKeyStringEnum.ROLES.getKeyString(), roles);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiredMs))
                .setIssuer(issuer)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    /**
     * JWT 만료 여부
     * @param token {@link String}
     * @return
     */
    public boolean isExpired(String token) {
        return Jwts.parserBuilder().setSigningKey(secretKey).build()
                .parseClaimsJws(token).getBody().getExpiration().before(new Date());
    }

    /**
     * JWT 유저 명 조회
     *
     * @param token {@link String}
     * @return
     */
    public String getUserName(String token) {
        return Jwts.parserBuilder().setSigningKey(secretKey).build()
                .parseClaimsJws(token).getBody().get(MapKeyStringEnum.JWT_USERNAME.getKeyString(), String.class);
    }

    /**
     * JWT 유저 권한 정보 조회
     * @param token {@link String}
     * @return
     * @throws JsonProcessingException
     */
    public Set<?> getUserRoles(String token) throws JsonProcessingException {
        TokenVO tokenVO = this.decode(token);
        return tokenVO.getRoles();
    }

    /**
     * JWT decoder
     * @param token {@link String}
     * @return
     * @throws JsonProcessingException
     */
    public TokenVO decode(String token) throws JsonProcessingException {
        if(!StringUtils.hasText(token)) throw new NullPointerException(ExceptionCodeMsgEnum.NO_TOKEN.getMsg());

        String[] chunk = token.split("\\.");
        Base64.Decoder decoder = Base64.getDecoder();
        String payload = new String(decoder.decode(chunk[1]));
        log.info("Token payload: {}", payload);

        ObjectMapper mapper = new ObjectMapper();
        TokenVO tokenVO = mapper.readValue(payload, TokenVO.class);

        return tokenVO;
    }
}
