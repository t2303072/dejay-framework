package com.dejay.framework.common.interceptor;

import com.dejay.framework.common.enums.MapKeyStringEnum;
import com.dejay.framework.common.utils.JwtUtil;
import com.dejay.framework.common.utils.SessionFactory;
import com.dejay.framework.vo.common.TokenVO;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.security.auth.login.LoginException;
import java.util.Arrays;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        log.info("[preHandle]: {}", requestURI);
        TokenVO tokenVO = JwtUtil.decode(request.getHeader(HttpHeaders.AUTHORIZATION).split("Bearer ")[1]);
        request.setAttribute(MapKeyStringEnum.TOKEN_VO.getKeyString(), tokenVO);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("Callee ==> {}", handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("[afterCompletion]");
    }
}
