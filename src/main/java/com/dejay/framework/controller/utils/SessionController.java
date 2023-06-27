package com.dejay.framework.controller.utils;

import com.dejay.framework.common.enums.MapKeyStringEnum;
import com.dejay.framework.common.enums.ResultCodeMsgEnum;
import com.dejay.framework.common.utils.CookieFactory;
import com.dejay.framework.common.utils.MapUtil;
import com.dejay.framework.common.utils.SessionFactory;
import com.dejay.framework.domain.Member;
import com.dejay.framework.vo.ResultStatusVO;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/session")
public class SessionController {

    private final SessionFactory sessionFactory;
    private final CookieFactory cookieFactory;
    private final MapUtil mapUtil;

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
        ResultStatusVO resultStatusVO = new ResultStatusVO();
        List<String> mapKeyList = new ArrayList<>();
        List<Object> dataList = new ArrayList<>();
        Map<String, Object> resultMap;

        Cookie cookie = cookieFactory.findCookie(request, SessionFactory.SessionEnum.SESSION_ID.getSessionKey());
        if(cookie == null) {
            resultStatusVO = new ResultStatusVO(ResultCodeMsgEnum.NO_COOKIE.getCode(), ResultCodeMsgEnum.NO_COOKIE.getMsg());
        }else {
            mapKeyList.add(MapKeyStringEnum.MEMBER.getKeyString());
            Member loginUserInfo = sessionFactory.getLoginUserInfo(request);
            dataList.add(loginUserInfo);
            if(loginUserInfo == null) resultStatusVO = new ResultStatusVO(ResultCodeMsgEnum.NOT_LOGGED_IN.getCode(), ResultCodeMsgEnum.NOT_LOGGED_IN.getMsg());
        }

        resultMap = mapUtil.responseEntityBodyWrapper(resultStatusVO, mapKeyList, dataList);
        return ResponseEntity.ok(resultMap);
    }

}
