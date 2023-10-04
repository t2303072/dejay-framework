package com.dejay.framework.controller.login;

import com.dejay.framework.common.enums.ExceptionCodeMsgEnum;
import com.dejay.framework.common.exception.CustomLoginException;
import com.dejay.framework.controller.common.ParentController;
import com.dejay.framework.domain.member.LoginRequest;
import com.dejay.framework.vo.member.MemberVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController extends ParentController {
    /**
     * login view를 가져와 준다.
     * @param model
     * @return
     */
    @GetMapping({"", "/"})
    public String login(Model model) {
        model.addAttribute("member", new MemberVO());
        return "login/view-login";
    }

    /**
     * login
     * @param member
     * @return
     */
    @PostMapping({"", "/login"})
    public ResponseEntity login(@RequestBody LoginRequest loginRequest,HttpServletRequest request) throws CustomLoginException{
        // 유저 이름에 해당하는 유저 정보를 가져온다.
        MemberVO loginInfo = getCommonService().getMemberService().getLoginInfo(loginRequest);
        HttpSession session = request.getSession();

        if(loginInfo!=null){
            getCommonUtil().getTokenFactory().createJWT(loginInfo.getUserId(), null, loginInfo.getAuthority()); // token 생성
            session.setAttribute("loginInfo", loginInfo);
            log.info("session userInfo : {}", loginInfo);

            return new ResponseEntity(loginInfo, HttpStatus.OK);
        }else {
            session.setAttribute("loginInfo", null);

            throw new CustomLoginException(ExceptionCodeMsgEnum.ACCOUNT_NOT_EXISTS.getCode(), ExceptionCodeMsgEnum.ACCOUNT_NOT_EXISTS.getMsg());
        }

    }
}
