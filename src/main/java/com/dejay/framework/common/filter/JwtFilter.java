package com.dejay.framework.common.filter;

import com.dejay.framework.common.enums.ResultCodeMsgEnum;
import com.dejay.framework.common.utils.JwtUtil;
import com.dejay.framework.service.member.MemberService;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final MemberService memberService;
    private final String secretKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        log.info("authorization: {}", authorization);

        // token 전송 여부 확인
        if(authorization == null || !authorization.startsWith("Bearer ")) {
            log.error(ResultCodeMsgEnum.INVALID_HEADER_AUTHORIZATION.getMsg());
            filterChain.doFilter(request, response);
            return;
        }

        // Get token
        String token = authorization.split(" ")[1];

        // Token expiration status
        boolean expired = JwtUtil.isExpired(token, secretKey);
        if(JwtUtil.isExpired(token, secretKey)) {
            log.error(ResultCodeMsgEnum.TOKEN_EXPIRED.getMsg());
            filterChain.doFilter(request, response);
            return;
        }

        // Get userName out of token
        String userName = JwtUtil.getUserName(token, secretKey);

        // Grant Authentication
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userName, null, List.of(new SimpleGrantedAuthority("USER")));

        // Set details
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }
}
