package com.dejay.framework.controller.auth;

import com.dejay.framework.controller.common.ParentController;
import com.dejay.framework.vo.code.CommonCodeVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthorityController extends ParentController {

    /**
     * 개인 권한 관리 화면
     * @param mv
     * @return
     */
    @GetMapping("/individual")
    public ModelAndView findAll(ModelAndView mv) {
        mv.setViewName("auth/individual");

        // 메뉴 목록 조회
        List<CommonCodeVO> list = getCommonService().getAuthorityService().findMenuList();
        mv.addObject("list", list);
        mv.addObject("code", new CommonCodeVO());

        return mv;
    }

}
