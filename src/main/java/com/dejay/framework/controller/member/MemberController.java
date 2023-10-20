package com.dejay.framework.controller.member;

import com.dejay.framework.common.enums.ExceptionCodeMsgEnum;
import com.dejay.framework.common.exception.CustomLoginException;
import com.dejay.framework.common.utils.StringUtil;
import com.dejay.framework.controller.common.ParentController;
import com.dejay.framework.domain.user.SignUpRequest;
import com.dejay.framework.vo.member.MemberVO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@RequestMapping("/member")
public class MemberController extends ParentController {

    /**
     * 회원 가입 View
     * @param mv
     * @return
     */
    @GetMapping("/sign-up")
    public ModelAndView signUp(ModelAndView mv){
        mv.addObject("member", new MemberVO());
        mv.setViewName("member/member-sign-up");
        return mv;
    }

    /**
     * 아이디 정보 확인 view
     * @param mv
     * @return
     */
    @GetMapping("/userId-check")
    public ModelAndView userIdCheck(ModelAndView mv, HttpServletRequest request){
        String id = request.getParameter("id");
        log.info("id : {}", id);
        mv.addObject("memberId", id);
        mv.setViewName("member/member-info-check");
        return mv;
    }

    /**
     * 회원 찾기 View
     * @param mv
     * @return
     */
    @GetMapping("/findById")
    public ModelAndView findById(ModelAndView mv){
        mv.addObject("member",new MemberVO());
        mv.setViewName("member/member-find-id");
        return mv;
    }

    /**
     * 비밀번호 찾기 view
     * @param mv
     * @return
     */
    @GetMapping("/findByPwd")
    public ModelAndView findByPassword(ModelAndView mv) {
        mv.setViewName("member/member-find-password");
        mv.addObject("member", new MemberVO());
        return mv;
    }

    /**
     * 비밀번호 변경 view
     * @param mv
     * @return
     */
    @GetMapping("/pwdChange")
    public ModelAndView changePassword(ModelAndView mv){
        mv.setViewName("member/member-change-password");
        return mv;
    }

    @PostMapping("/sign-up")
    public ResponseEntity signUp(@RequestBody SignUpRequest signUpRequest) throws CustomLoginException {
        MemberVO memberVO = getCommonService().getMemberService().findMemberByUserName(signUpRequest.getId());

        if(!StringUtil.isEmpty(memberVO)){
            throw new CustomLoginException(ExceptionCodeMsgEnum.ACCOUNT_DUPLICATE.getCode(), ExceptionCodeMsgEnum.ACCOUNT_DUPLICATE.getMsg());
        } else {
            getCommonService().getMemberService().signUp(signUpRequest);
            return new ResponseEntity(HttpStatus.OK);
        }

    }

}
