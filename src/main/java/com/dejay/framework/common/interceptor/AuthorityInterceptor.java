package com.dejay.framework.common.interceptor;

import com.dejay.framework.common.enums.ExceptionCodeMsgEnum;
import com.dejay.framework.common.enums.MapKeyStringEnum;
import com.dejay.framework.common.utils.JwtUtil;
import com.dejay.framework.common.utils.ObjectHandlingUtil;
import com.dejay.framework.service.member.MemberService;
import com.dejay.framework.vo.common.TokenVO;
import com.dejay.framework.vo.member.MemberVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.security.auth.login.LoginException;

@Slf4j
@RequiredArgsConstructor
@Component
public class AuthorityInterceptor implements HandlerInterceptor {

    private final JwtUtil jwtUtil;
    private final MemberService memberService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("[preHandle]: {}", request.getRequestURI());

        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(!StringUtils.hasText(authorizationHeader)) {
            throw new AuthenticationException(ExceptionCodeMsgEnum.AUTH_ERROR.getMsg()) {
                @Override
                public String toString() {
                    return super.toString();
                }
            };
        }

        TokenVO tokenVO = jwtUtil.decode(authorizationHeader.split("Bearer ")[1]);
        if(tokenVO == null) throw new InvalidBearerTokenException(ExceptionCodeMsgEnum.AUTH_ERROR.getMsg());
        request.setAttribute(MapKeyStringEnum.TOKEN_VO.getKeyString(), tokenVO);

        MemberVO memberVO = memberService.findMemberByUserName(tokenVO.getUserName());
        if(memberVO == null) throw new LoginException(ExceptionCodeMsgEnum.LOGIN_REQUIRED.getMsg());
        request.setAttribute(MapKeyStringEnum.MEMBER_VO.getKeyString(), memberVO);

        MemberVO memberByUserName = memberService.findMemberByUserName(memberVO.getMemberId());

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {}

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {}
}
