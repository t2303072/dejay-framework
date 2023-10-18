package com.dejay.framework.controller.board;

import com.dejay.framework.common.utils.ObjectHandlingUtil;
import com.dejay.framework.controller.common.ParentController;
import com.dejay.framework.domain.board.Board;
import com.dejay.framework.domain.common.Paging;
import com.dejay.framework.vo.board.BoardPublicVO;
import com.dejay.framework.vo.search.SearchVO;
import com.dejay.framework.vo.search.board.BoardSearchVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController extends ParentController {

    @GetMapping({"", "/"})
    public ModelAndView findAll(ModelAndView mv) {
        mv.setViewName("board/list");

        // [검색옵션] 날짜
        var searchDateRangeOptionList = getCommonService().getBoardPublicServiceImpl().getSearchDateRangeOptionList();
        mv.addObject("searchDateRangeOptionList", searchDateRangeOptionList);

        // [검색옵션] 키워드
        var searchKeywordTypeOptionList = getCommonService().getBoardPublicServiceImpl().getSearchKeywordTypeList();
        mv.addObject("searchKeywordTypeOptionList", searchKeywordTypeOptionList);

        // TODO: [노출 게시물 갯수 옵션]

        // 전체 게시물 수
        int totalCount = getCommonService().getBoardPublicServiceImpl().totalCount(null);
        mv.addObject("totalCount", totalCount);

        BoardSearchVO boardSearchVO = new BoardSearchVO();
        boardSearchVO.setPaging(Paging.builder()
                .currentPage(1)
                .displayRow(10)
                .build()
        );
        // 목록 조회
        List<BoardPublicVO> list = getCommonService().getBoardPublicServiceImpl().findAll(boardSearchVO);
        mv.addObject("list", list);

        // paging
        SearchVO searchVO = new SearchVO();
        searchVO.setPaging(Paging.builder()
                .currentPage(1)
                .displayRow(10)
                .build()
        );
        Paging paging = ObjectHandlingUtil.pagingOperatorBySearch(searchVO, totalCount);
        mv.addObject("paging", paging);

        return mv;
    }

    @PostMapping("/api/list")
    public String findAll(Model model, @RequestBody Map<String, Object> paramMap) {

        ObjectMapper om = new ObjectMapper();
        BoardSearchVO boardSearchVO = om.convertValue(paramMap.get("boardPublicVO"), BoardSearchVO.class);
        Paging pagingParam = om.convertValue(paramMap.get("paging"), Paging.class);
        boardSearchVO.setPaging(Paging.builder()
                .currentPage(pagingParam.getCurrentPage())
                .displayRow(pagingParam.getDisplayRow())
                .totalCount(pagingParam.getTotalCount())
                .build()
        );

        // 전체 게시물 수
        int totalListCount = getCommonService().getBoardPublicServiceImpl().totalCount(boardSearchVO);
        model.addAttribute("totalCount", totalListCount);
//
//        // 목록 조회
        List<BoardPublicVO> list = getCommonService().getBoardPublicServiceImpl().findAll(boardSearchVO);
        model.addAttribute("list", list);

        // paging
        Paging paging = Paging.builder()
                .currentPage(boardSearchVO.getPaging().getCurrentPage())
                .displayRow(boardSearchVO.getPaging().getDisplayRow())
                .totalCount(totalListCount)
                .build();
        model.addAttribute("paging", paging);

        return "board/list :: #list_wrapper";
    }

    @ResponseBody
    @DeleteMapping("/api/delete")
    public ResponseEntity deleteBoard(Model model, @RequestParam(value = "lastId") String lastId, @RequestParam(value = "checkedList[]") List<Integer> checkedList) {
        Map<String, Object> result = getCommonService().getBoardPublicServiceImpl().deleteBySeq(lastId, checkedList);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/{seq}")
    public ModelAndView findById(ModelAndView mv, @PathVariable long seq) {
        mv.setViewName("board/detail");

        BoardSearchVO boardSearchVO = new BoardSearchVO();
        boardSearchVO.setBoardSeq(seq);
        BoardPublicVO rowData = getCommonService().getBoardService().findById(boardSearchVO);
        mv.addObject("rowData", rowData);

        return mv;
    }

    @GetMapping("/edit/{seq}")
    public ModelAndView editForm(ModelAndView mv, @PathVariable long seq) {
        mv.setViewName("board/edit");

        BoardSearchVO boardSearchVO = new BoardSearchVO();
        boardSearchVO.setBoardSeq(seq);
        BoardPublicVO rowData = getCommonService().getBoardService().findById(boardSearchVO);
        mv.addObject("rowData", rowData);

        return mv;
    }

    @ResponseBody
    @PatchMapping("/api/update/{seq}")
    public ResponseEntity deleteBoard(Model model, @RequestBody Board board) {
        log.info(board.toString());
        Map<String, Object> result = getCommonService().getBoardPublicServiceImpl().updateBoard(board);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/registration")
    public String registration(ModelAndView mv) {
//        mv.setViewName("board/registration");
        return "board/registration";
    }
}
