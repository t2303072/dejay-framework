package com.dejay.framework.controller.login;

import com.dejay.framework.controller.common.ParentController;
import com.dejay.framework.vo.member.MemberVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.Map;

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
    public ResponseEntity login(@RequestBody MemberVO member, RedirectView redirectView) {
        // 유저 이름에 해당하는 유저 정보를 가져온다.
        MemberVO loginInfo = getCommonService().getFileService().getCommonMapper().getMemberMapper().getLoginInfo(member.getMemberName());

        return new ResponseEntity<>(loginInfo, HttpStatus.OK);
    }
}
