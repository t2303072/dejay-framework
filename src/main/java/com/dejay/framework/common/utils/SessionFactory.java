package com.dejay.framework.common.utils;

import com.dejay.framework.domain.Member;
import com.dejay.framework.vo.MemberVO;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@RequiredArgsConstructor
@Component
public class SessionFactory {

    private static final String SESSION_ID = "SESSION_ID";

    public <T> void createSession(HttpServletRequest request, T obj) {
        HttpSession httpSession = request.getSession(true);
        httpSession.setAttribute(SESSION_ID, obj);
    }

    public void removeSession(HttpServletRequest request) {
        request.getSession().invalidate();
    }

    public Member getLoginUserInfo(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session == null) return null;
        String loginId = (String) session.getAttribute(SESSION_ID);
        Member member = Member.builder().userId(loginId).build();
        log.info(member.toString());

        return member;
    }
}