package com.dejay.framework.common.utils;

import com.dejay.framework.domain.Member;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class SessionFactory {

    private static final String SESSION_ID = "SESSION_ID";
    private static final String LOGIN_MEMBER = "loginMember";

    public <T> void createSession(HttpServletRequest request, T obj) {
        HttpSession httpSession = request.getSession(true);
        httpSession.setAttribute(LOGIN_MEMBER, obj);
    }

    public void removeSession(HttpServletRequest request) {
        request.getSession().invalidate();
    }

    public Member getLoginUserInfo(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session == null) return null;
        String loginId = (String) session.getAttribute(SESSION_ID);
        Member member = Member.builder().memberId(loginId).build();
        log.info(member.toString());

        return member;
    }
}
