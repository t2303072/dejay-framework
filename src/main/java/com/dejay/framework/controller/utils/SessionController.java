package com.dejay.framework.controller.utils;

import com.dejay.framework.common.enums.MapKeyStringEnum;
import com.dejay.framework.common.enums.ResultCodeMsgEnum;
import com.dejay.framework.common.utils.CookieFactory;
import com.dejay.framework.common.utils.MapUtil;
import com.dejay.framework.common.utils.SessionFactory;
import com.dejay.framework.controller.common.ParentController;
import com.dejay.framework.domain.member.Member;
import com.dejay.framework.vo.common.ResultStatusVO;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/session")
public class SessionController extends ParentController {

    @GetMapping({"", "/"})
    public ResponseEntity createSession(HttpServletRequest request, HttpServletResponse response) {
        Member loginInfo = Member.builder()
                .memberId("ijzone")
                .memberName("이익주")
                .build();
        // TODO: IJ 사용자 로그인 정보 조회 로직 추가
        commonUtil().sessionFactory().createSession(request, loginInfo);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/expire")
    public ResponseEntity expireSession(HttpServletRequest request, HttpServletResponse response) {
        commonUtil().sessionFactory().removeSession(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/login-test")
    public ResponseEntity sessionLogin(HttpServletRequest request, HttpSession session) {
        ResultStatusVO resultStatusVO = new ResultStatusVO();
        List<String> mapKeyList = new ArrayList<>();
        Map<String, Object> resultMap;
        Member loginUserInfo = null;

        Cookie cookie = commonUtil().cookieFactory().findCookie(request, SessionFactory.SessionEnum.SESSION_ID.getSessionKey());
        if(cookie == null) {
            resultStatusVO = new ResultStatusVO(ResultCodeMsgEnum.NO_COOKIE.getCode(), ResultCodeMsgEnum.NO_COOKIE.getMsg());
        }else {
            mapKeyList.add(MapKeyStringEnum.MEMBER.getKeyString());
            loginUserInfo = commonUtil().sessionFactory().getLoginUserInfo(request);

            if(loginUserInfo == null) resultStatusVO = new ResultStatusVO(ResultCodeMsgEnum.NOT_LOGGED_IN.getCode(), ResultCodeMsgEnum.NOT_LOGGED_IN.getMsg());
        }

        resultMap = mapUtil().responseEntityBodyWrapper(resultStatusVO, mapKeyList, loginUserInfo);
        return ResponseEntity.ok(resultMap);
    }

}
