package com.dejay.framework.common.config;

import com.dejay.framework.vo.common.ResultStatusVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
public class JwtExceptionFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("JwtExceptionFilter");
        try {
            filterChain.doFilter(request, response);
        } catch (JwtException ex) {
            jwtExceptionResponse(response, HttpStatus.FORBIDDEN, ex);
        }
    }

    public void jwtExceptionResponse(HttpServletResponse response, HttpStatus status, Throwable ex) throws IOException {
        response.setStatus(status.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        String json = new ObjectMapper().writeValueAsString(new ResultStatusVO(status.value(), ex.getMessage()));
        response.getWriter().write(json);
    }
}
