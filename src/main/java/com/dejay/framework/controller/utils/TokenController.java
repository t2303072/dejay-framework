package com.dejay.framework.controller.utils;

import com.dejay.framework.common.enums.MapKeyStringEnum;
import com.dejay.framework.common.enums.ResultCodeMsgEnum;
import com.dejay.framework.common.utils.ObjectHandlingUtil;
import com.dejay.framework.controller.common.ParentController;
import com.dejay.framework.domain.common.TokenObjectVO;
import com.dejay.framework.domain.member.LoginRequest;
import com.dejay.framework.vo.common.ResultStatusVO;
import com.dejay.framework.vo.common.TokenVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/token")
public class TokenController extends ParentController {

    /**
     * JWT 생성
     * @param loginRequest
     * @return
     */
    @PostMapping(value = {"", "/"})
    public ResponseEntity createJWT(@RequestBody @Valid LoginRequest loginRequest) {
        TokenObjectVO tokenObjectVO = getCommonUtil().tokenFactory().createJWT(loginRequest.getUserName(), loginRequest.getPassword(), loginRequest.getAuthority());
        ResultStatusVO resultStatusVO = ObjectHandlingUtil.setSingleObjResultStatusVO(tokenObjectVO, ResultCodeMsgEnum.NO_DATA);
        var mapKeyList = Arrays.asList(MapKeyStringEnum.TOKEN_OBJECT.getKeyString());
        Map<String, Object> resultMap = getMapUtil().responseEntityBodyWrapper(resultStatusVO, mapKeyList, tokenObjectVO);

        return ResponseEntity.ok(resultMap);
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

    @PostMapping("/reissue-token")
    public ResponseEntity modifyToken(HttpServletRequest request, Authentication authentication) {
        log.info(authentication.toString());
        String reissueYn = (String) request.getAttribute(MapKeyStringEnum.TOKEN_REISSUE.getKeyString());

        TokenObjectVO tokenObjectVO = null;
        HttpStatus status = HttpStatus.OK;
        if(reissueYn.equalsIgnoreCase("N")) {
            status = HttpStatus.BAD_REQUEST;
        } else {
            tokenObjectVO = getCommonUtil().tokenFactory().createJWT(authentication.getPrincipal().toString(), null, authentication.getAuthorities().stream().collect(Collectors.toSet()));
        }

        ResultStatusVO resultStatusVO = ObjectHandlingUtil.setSingleObjResultStatusVO(tokenObjectVO, ResultCodeMsgEnum.INVALID_HEADER);
        var mapKeyList = Arrays.asList(MapKeyStringEnum.TOKEN_OBJECT.getKeyString());
        Map<String, Object> resultMap = getMapUtil().responseEntityBodyWrapper(resultStatusVO, mapKeyList, tokenObjectVO);

        return ResponseEntity.status(status).body(resultMap);
    }
}
