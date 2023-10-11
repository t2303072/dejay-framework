package com.dejay.framework.controller.board;

import com.dejay.framework.controller.common.ParentController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController extends ParentController {

    @GetMapping({"", "/"})
    public ModelAndView board(ModelAndView mv) {
        mv.setViewName("board/list");
        mv.addObject("hello", "드제이");

        return mv;
    }
}
