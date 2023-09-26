package com.dejay.framework.controller.member;

import com.dejay.framework.common.enums.ExceptionCodeMsgEnum;
import com.dejay.framework.common.exception.CustomLoginException;
import com.dejay.framework.common.utils.StringUtil;
import com.dejay.framework.controller.common.ParentController;
import com.dejay.framework.domain.common.TokenObjectVO;
import com.dejay.framework.domain.user.SignUpRequest;
import com.dejay.framework.vo.member.MemberVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/member")
public class MemberController extends ParentController {

    @GetMapping("/sign-up")
    public String signUp(Model model){
        model.addAttribute("member",new MemberVO());
        return "member/member-sign-up";
    }

    @PostMapping("/sign-up")
    public ResponseEntity signUp(@RequestBody SignUpRequest signUpRequest) throws CustomLoginException {
        MemberVO memberVO = getCommonService().getMemberService().getCommonMapper().getMemberMapper().findMemberByUserName(signUpRequest.getId());

        if(!StringUtil.isEmpty(memberVO)){
            throw new CustomLoginException(ExceptionCodeMsgEnum.ACCOUNT_DUPLICATE.getCode(), ExceptionCodeMsgEnum.ACCOUNT_DUPLICATE.getMsg());
        } else {
            getCommonService().getMemberService().signUp(signUpRequest);
            return new ResponseEntity(HttpStatus.OK);
        }

    }


}
