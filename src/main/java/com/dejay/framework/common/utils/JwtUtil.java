package com.dejay.framework.common.utils;

import com.dejay.framework.common.enums.ExceptionCodeMsgEnum;
import com.dejay.framework.common.enums.MapKeyStringEnum;
import com.dejay.framework.vo.common.TokenVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
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

    /**
     * JWT 생성
     * @param userName String
     * @param expiredMs Long
     * @return
     */
    public String createJwt(String userName, Long expiredMs, String[] roles) {
        Claims claims = Jwts.claims();
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
     * @param token
     * @return
     */
    public boolean isExpired(String token) {
        return Jwts.parserBuilder().setSigningKey(secretKey).build()
                    .parseClaimsJws(token).getBody().getExpiration().before(new Date());
    }

    /**
     * JWT 유저명 조회
     *
     * @param token
     * @return
     */
    public String getUserName(String token) {
        return Jwts.parserBuilder().setSigningKey(secretKey).build()
                .parseClaimsJws(token).getBody().get(MapKeyStringEnum.JWT_USERNAME.getKeyString(), String.class);
    }

    /**
     * JWT decoder
     * @param token String
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

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserPk(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUserPk(String token) {
        return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody().getSubject();
    }

    public String graspToken(HttpServletRequest request) {
        return request.getHeader("X-AUTH-TOKEN");
    }


}
