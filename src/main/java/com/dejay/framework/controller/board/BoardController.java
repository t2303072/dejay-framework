package com.dejay.framework.controller.board;

import com.dejay.framework.controller.common.ParentController;
import com.dejay.framework.vo.board.BoardPublicVO;
import com.dejay.framework.vo.board.BoardVO;
import com.dejay.framework.vo.search.board.BoardSearchVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController extends ParentController {

    @GetMapping("/{seq}")
    public ModelAndView findById(ModelAndView mv, @PathVariable long seq) {
        mv.setViewName("board/detail");

        BoardSearchVO boardSearchVO = new BoardSearchVO();
        boardSearchVO.setBoardSeq(seq);
        BoardPublicVO rowData = getCommonService().getBoardService().findById(boardSearchVO);
        mv.addObject("rowData", rowData);

        return mv;
    }

    @GetMapping({"", "/"})
    public ModelAndView list(ModelAndView mv) {
        mv.setViewName("board/list");

        List<BoardPublicVO> list = getCommonService().getBoardService().getList();
        mv.addObject("list", list);

        return mv;
    }
}
