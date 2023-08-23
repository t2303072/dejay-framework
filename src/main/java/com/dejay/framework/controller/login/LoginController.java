package com.dejay.framework.controller.login;

import com.dejay.framework.common.enums.MapKeyStringEnum;
import com.dejay.framework.common.enums.ResultCodeMsgEnum;
import com.dejay.framework.common.utils.ObjectHandlingUtil;
import com.dejay.framework.controller.common.ParentController;
import com.dejay.framework.domain.common.TokenObjectVO;
import com.dejay.framework.domain.member.LoginRequest;
import com.dejay.framework.vo.common.ResultStatusVO;
import com.dejay.framework.vo.member.MemberVO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/login")
public class LoginController extends ParentController {

    @PostMapping({"", "/"})
    public ResponseEntity login(@RequestBody @Valid LoginRequest loginRequest) {

        MemberVO loginInfo = getCommonService().getMemberService().getLoginInfo(loginRequest);
        Map<String, Object> resultMap;
        if(loginInfo != null) {
            TokenObjectVO tokenObjectVO = getCommonUtil().getTokenFactory().createJWT(loginInfo.getMemberId(), null, loginInfo.getAuthority());
            ResultStatusVO resultStatusVO = ObjectHandlingUtil.setSingleObjResultStatusVO(tokenObjectVO, ResultCodeMsgEnum.NO_DATA);
            var mapKeyList = Arrays.asList(MapKeyStringEnum.TOKEN_OBJECT.getKeyString());
            resultMap = getMapUtil().responseEntityBodyWrapper(resultStatusVO, mapKeyList, tokenObjectVO);
        }else  {
            resultMap = getMapUtil().responseEntityBodyWrapper(new ResultStatusVO(ResultCodeMsgEnum.NOT_LOGGED_IN.getCode(), ResultCodeMsgEnum.NOT_LOGGED_IN.getMsg()));
        }

        return ResponseEntity.ok(resultMap);
    }

    // TODO: IJ OAuth 로직 보류
    @PostMapping("/oauth2/google")
    public ResponseEntity oauth2Google() {

        return null;
    }

}
