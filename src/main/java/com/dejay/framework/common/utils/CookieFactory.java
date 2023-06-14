package com.dejay.framework.common.utils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
public class CookieFactory {

    public static final String SESSION_COOKIE_NAME = "JSESSIONID";

    public void setCookie(HttpServletResponse response,String key, String value, boolean httpOnly, boolean secure, String path, int maxAge, String domain) {
        Cookie cookie = new Cookie(key, value);
        cookie.setHttpOnly(httpOnly);
        cookie.setSecure(secure);
        cookie.setPath(path);
        cookie.setMaxAge(1 * 24 * 60 * 60);
        cookie.setDomain(domain);

        response.addCookie(cookie);
    }

    public Cookie findCookie(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        if(cookies == null) {
            return null;
        }

        return Arrays.stream(cookies)
                .filter(c -> c.getName().equals(cookieName))
                .findAny()
                .orElse(null);
    }
    public void removeCookie(HttpServletRequest request, HttpServletResponse response, String key) {
        Cookie cookie = this.findCookie(request, key);
        if(cookie != null) {
            cookie.setMaxAge(0);
            cookie.setDomain("localhost");  // TODO: 유효 도메인으로 추후 변경 필요
            cookie.setPath(request.getRequestURI());
            response.addCookie(cookie);
        }
    }

    public List<Cookie> getCookieList(HttpServletRequest request) {
        List<Cookie> list = new ArrayList<Cookie>();
        if(request.getCookies() != null && request.getCookies().length > 0) {
            list = Arrays.asList(request.getCookies());
            list.forEach(c -> log.info("Cookie name: {}, value: {}", String.valueOf(c.getName()), String.valueOf(c.getValue())));
        }

        return list;
    }
}
