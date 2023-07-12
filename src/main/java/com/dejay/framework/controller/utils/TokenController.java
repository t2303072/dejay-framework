package com.dejay.framework.controller.utils;

import com.dejay.framework.common.utils.ObjectHandlingUtil;
import com.dejay.framework.common.utils.TokenFactory;
import com.dejay.framework.domain.member.LoginRequest;
import com.dejay.framework.vo.common.TokenVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/token")
public class TokenController {

    private final TokenFactory tokenFactory;

    /**
     * JWT 생성
     * @param loginRequest
     * @return
     */
    @PostMapping(value = {"", "/"})
    public ResponseEntity createJWT(@RequestBody @Valid LoginRequest loginRequest) {
        String jwt = tokenFactory.createJWT(loginRequest.getUserName(), loginRequest.getPassword(), loginRequest.getRoles());
        return ResponseEntity.ok(jwt);
    }

    /**
     * 인증 정보 확인
     * @param authentication
     * @return
     */
    @PostMapping("/authentication-info")
    public ResponseEntity authentication(HttpServletRequest request, Authentication authentication) {
        log.info("Authentication: userName => {}", authentication.getName());
        authentication.getAuthorities().forEach(a -> log.info("권한: {}", a.getAuthority()));
        TokenVO tokenVO = ObjectHandlingUtil.extractTokenInfo(request);
        return ResponseEntity.ok(tokenVO.toString());
    }
}
