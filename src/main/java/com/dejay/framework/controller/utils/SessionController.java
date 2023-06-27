package com.dejay.framework.controller.utils;

import com.dejay.framework.common.utils.CookieFactory;
import com.dejay.framework.common.utils.SessionFactory;
import com.dejay.framework.domain.Member;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/session")
public class SessionController {

    private final SessionFactory sessionFactory;

    @GetMapping({"", "/"})
    public ResponseEntity createSession(HttpServletRequest request, HttpServletResponse response) {
        Member loginInfo = Member.builder()
                .memberId("ijzone")
                .memberName("이익주")
                .build();
        // TODO: IJ 사용자 로그인 정보 조회 로직 추가
        sessionFactory.createSession(request, loginInfo);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/expire")
    public ResponseEntity expireSession(HttpServletRequest request, HttpServletResponse response) {
        sessionFactory.removeSession(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/login-test")
    public ResponseEntity sessionLogin(HttpServletRequest request) {
        sessionFactory.getLoginUserInfo(request);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/index"));
        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }

}
