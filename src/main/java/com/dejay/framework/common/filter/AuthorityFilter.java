package com.dejay.framework.common.filter;

import com.dejay.framework.common.enums.ResultCodeMsgEnum;
import com.dejay.framework.common.utils.JwtUtil;
import com.dejay.framework.service.member.MemberService;
import com.dejay.framework.vo.member.MemberVO;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class AuthorityFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private static final String TOKEN_PREFIX = "Bearer ";
    private final MemberService memberService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);

        // token 전송 여부 확인
        if(authorization == null || !authorization.startsWith(TOKEN_PREFIX)) {
            log.error(ResultCodeMsgEnum.NO_HEADER_AUTHORIZATION.getMsg());
            filterChain.doFilter(request, response);
            return;
        }

        // Get token
        String token = authorization.split(" ")[1];

        // Token expiration status
        if(jwtUtil.isExpired(token)) {
            log.error(ResultCodeMsgEnum.TOKEN_EXPIRED.getMsg());
            filterChain.doFilter(request, response);
            return;
        }

        // Get userName out of token
        String userName = jwtUtil.getUserName(token);

        // TODO: IJ 유효 로그인 정보 여부 조회, MyBatis Interceptor에서 걸리므로 필터에서 조회하는 쿼리는 제외하는 로직 필요
//        MemberVO memberVO = memberService.findMemberByUserName(userName);

        // TODO: IJ 권한 부여 로직
        // Grant Authentication
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userName, null, List.of(new SimpleGrantedAuthority("USER")));

        // Set details
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }
}
