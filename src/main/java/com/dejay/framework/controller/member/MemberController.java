package com.dejay.framework.controller.member;

import com.dejay.framework.controller.common.ParentController;
import com.dejay.framework.vo.member.MemberVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

}
