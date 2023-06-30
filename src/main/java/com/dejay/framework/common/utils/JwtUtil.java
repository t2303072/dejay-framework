package com.dejay.framework.common.utils;

import com.dejay.framework.domain.common.LoginRequest;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Slf4j
@Component
public class JwtUtil {

    private final String secretKey = "ijzoneJwtSecretKeyGenerateStringForTokenLongWord"; // JWT secret key
    private long jwtExpiration = 86400000;
    private long refreshExpiration = 604800000;
//    private final long validityInMilliSecondsForOneHour = 3600000; // 1hour

    /**
     * JWT 토큰 생성
     * @param extraClaims
     * @param loginRequest
     * @return
     */
    public String generateToken(Map<String, Object> extraClaims, LoginRequest loginRequest) {
        return buildToken(extraClaims, loginRequest, jwtExpiration);
    }

    /**
     * JWT Refresh 토큰 생성
     * @param extraClaims
     * @param loginRequest
     * @return
     */
    public String generateRefreshToken(Map<String, Object> extraClaims, LoginRequest loginRequest) {
        return buildToken(extraClaims, loginRequest, refreshExpiration);
    }

    /**
     * JWT 토큰 빌더
     * @param extraClaims
     * @param loginRequest
     * @param expiration
     * @return
     */
    public String buildToken(Map<String, Object> extraClaims, LoginRequest loginRequest, long expiration) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(loginRequest.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, LoginRequest loginRequest) {
        final String username = extractUsername(token);
        return (username.equals(loginRequest.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

//    public String getUsernameFromToken(String token) {
//        return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody().getSubject();
//    }

//    public boolean validateToken(String token) {
//        try {
//            Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
//            return true;
//        } catch (JwtException | IllegalArgumentException e) {
//            log.error(e.getMessage());
//            return false;
//        }
//    }
}
