package com.dejay.framework.common.interceptor;

import com.dejay.framework.common.enums.ExceptionCodeMsgEnum;
import com.dejay.framework.common.enums.MapKeyStringEnum;
import com.dejay.framework.common.enums.RequestTypeEnum;
import com.dejay.framework.common.utils.JwtUtil;
import com.dejay.framework.mapper.authority.AuthorityMapper;
import com.dejay.framework.service.member.MemberService;
import com.dejay.framework.vo.authority.MenuAuthorityVO;
import com.dejay.framework.vo.common.TokenVO;
import com.dejay.framework.vo.member.MemberVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.security.auth.login.LoginException;
import java.util.Arrays;

@Slf4j
@RequiredArgsConstructor
@Component
public class AuthorityInterceptor implements HandlerInterceptor {

    private final JwtUtil jwtUtil;
    private final MemberService memberService;
    private final AuthorityMapper authorityMapper;

    private String[] readPrefix = {"paging", "list", "row", "get", "select", "one"};
    private String[] createPrefix = {"insert", "save"};
    private String[] updatePrefix = {"update", "modify"};
    private String[] deletePrefix = {"delete", "remove"};
    private String[] downloadPrefix = {"download", "fileDownload"};
    private RequestTypeEnum action = null;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("[preHandle]: {}", request.getRequestURI());

        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(!StringUtils.hasText(authorizationHeader)) {
            authException(ExceptionCodeMsgEnum.INVALID_AUTH.getMsg());
        }

        TokenVO tokenVO = jwtUtil.decode(authorizationHeader.split("Bearer ")[1]);
        if(tokenVO == null) throw new InvalidBearerTokenException(ExceptionCodeMsgEnum.INVALID_AUTH.getMsg());
        request.setAttribute(MapKeyStringEnum.TOKEN_VO.getKeyString(), tokenVO);

        MemberVO memberVO = memberService.findMemberByUserName(tokenVO.getUserName());
        if(memberVO == null) throw new LoginException(ExceptionCodeMsgEnum.LOGIN_REQUIRED.getMsg());
        request.setAttribute(MapKeyStringEnum.MEMBER_VO.getKeyString(), memberVO);

        /**
         * 메뉴 접근 권한 체크
         */
        String menuId = request.getRequestURI().split("/")[1];
        MenuAuthorityVO menuAuthorityVO = authorityMapper.selectMenuAuthority(memberVO.getMemberId(), menuId);
        if(menuAuthorityVO == null) authException(ExceptionCodeMsgEnum.NO_MENU_AUTHORITY.getMsg());
        menuAuthorityVO.authLevelDivider();

        String methodName = ((HandlerMethod) handler).getMethod().getName();
        boolean hasNoAuthority = Arrays.stream(targetPrefix(methodName)).filter(p -> methodName.startsWith(p)).findAny().isEmpty();
        if(hasNoAuthority) authException(ExceptionCodeMsgEnum.NO_AUTHORITY.getMsg());

        switch(action) {
            case READ -> {
                if(menuAuthorityVO.isRead()) return true;
                authException(ExceptionCodeMsgEnum.NO_AUTHORITY.getMsg());
            }
            case CREATE -> {
                if(menuAuthorityVO.isCreate()) return true;
                authException(ExceptionCodeMsgEnum.NO_AUTHORITY.getMsg());
            }
            case UPDATE -> {
                if(menuAuthorityVO.isUpdate()) return true;
                authException(ExceptionCodeMsgEnum.NO_AUTHORITY.getMsg());
            }
            case DELETE -> {
                if(menuAuthorityVO.isDelete()) return true;
                authException(ExceptionCodeMsgEnum.NO_AUTHORITY.getMsg());
            }
            case DOWNLOAD -> {
                if(menuAuthorityVO.isDownload()) return true;
                authException(ExceptionCodeMsgEnum.NO_AUTHORITY.getMsg());
            }
            default -> authException(ExceptionCodeMsgEnum.NO_AUTHORITY.getMsg());
        }

        return true;
    }

    private void authException(String msg) {
        throw new AccessDeniedException(msg);
    }

    private String[] targetPrefix(String obj) {
        boolean read = Arrays.stream(readPrefix).filter(p -> obj.startsWith(p)).findAny().isEmpty();
        boolean create = Arrays.stream(createPrefix).filter(p -> obj.startsWith(p)).findAny().isEmpty();
        boolean update = Arrays.stream(updatePrefix).filter(p -> obj.startsWith(p)).findAny().isEmpty();
        boolean delete = Arrays.stream(deletePrefix).filter(p -> obj.startsWith(p)).findAny().isEmpty();
        boolean download = Arrays.stream(downloadPrefix).filter(p -> obj.startsWith(p)).findAny().isEmpty();

        if(!read && create && update && delete) {
            action = RequestTypeEnum.READ;
            return readPrefix;
        } else if(read && !create && update && delete) {
            action = RequestTypeEnum.CREATE;
            return createPrefix;
        } else if(read && create && !update && delete) {
            action = RequestTypeEnum.UPDATE;
            return updatePrefix;
        } else if (read && create && update && !delete) {
            action = RequestTypeEnum.DELETE;
            return deletePrefix;
        } else if (!download) {
            action = RequestTypeEnum.DOWNLOAD;
            return downloadPrefix;
        } else {
            return null;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {}

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        action = null;
    }
}
