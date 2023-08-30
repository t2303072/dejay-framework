package com.dejay.framework.controller.board;

import com.dejay.framework.common.enums.MapKeyStringEnum;
import com.dejay.framework.common.enums.RequestTypeEnum;
import com.dejay.framework.common.enums.ResultCodeMsgEnum;
import com.dejay.framework.common.utils.ObjectHandlingUtil;
import com.dejay.framework.controller.common.ParentController;
import com.dejay.framework.domain.board.Board;
import com.dejay.framework.domain.common.DataObject;
import com.dejay.framework.domain.common.SearchObject;
import com.dejay.framework.vo.board.BoardVO;
import com.dejay.framework.vo.common.ResultStatusVO;
import com.dejay.framework.vo.member.MemberVO;
import com.dejay.framework.vo.search.board.BoardSearchVO;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/board")
public class BoardController extends ParentController {


    /**
     * 게시판 페이징 조회
     * @param searchObject
     * @return
     */
    @PostMapping(value="/paging")
    public ResponseEntity pagingBoard(@RequestBody @Valid SearchObject searchObject){
        List<BoardVO> boardList = getCommonService().getBoardService().pagingBoard(searchObject.getSearch().getBoardSearch());

        ResultStatusVO resultStatusVO = ObjectHandlingUtil.setListResultStatusVO(boardList, ResultCodeMsgEnum.NO_DATA);
        List<String> mapKeyList = Arrays.asList(MapKeyStringEnum.PAGING.getKeyString(), MapKeyStringEnum.BOARD_LIST.getKeyString());
        Map<String,Object> resultMap = getMapUtil().responseEntityBodyWrapper(resultStatusVO, mapKeyList,  searchObject.getSearch().getBoardSearch().getPaging() ,boardList);

        return ResponseEntity.ok(resultMap);
    }

    /**
     * 게시판 단 건 조회
     * @param searchObject
     * @return
     */
    @PostMapping(value="/row")
    public ResponseEntity rowBoard(@RequestBody @Valid SearchObject searchObject){
        BoardVO board = getCommonService().getBoardService().rowBoard(searchObject.getSearch().getBoardSearch());
        ResultStatusVO resultStatusVO = ObjectHandlingUtil.setSingleObjResultStatusVO(board, ResultCodeMsgEnum.NO_DATA);
        List<String> mapKeyList = Arrays.asList(MapKeyStringEnum.BOARD.getKeyString());
        Map<String, Object> resultMap = getMapUtil().responseEntityBodyWrapper(resultStatusVO, mapKeyList, board);

        return ResponseEntity.ok(resultMap);
    }

    /**
     * 게시판 저장
     * @param dataObject
     * @return
     */
    @PostMapping(value="/insert")
    public ResponseEntity insertBoard(@RequestBody @Valid DataObject dataObject, HttpServletRequest request) throws Exception {
        MemberVO member = ObjectHandlingUtil.extractLoginInfo(request);

        int inserted = getCommonService().getBoardService().insertBoard(dataObject.getData().getBoard(),member);
        ResultStatusVO resultStatusVO = ObjectHandlingUtil.setDataManipulationResultStatusVO(inserted, RequestTypeEnum.CREATE);
        Map<String, Object> resultMap = getMapUtil().responseEntityBodyWrapper(resultStatusVO);

        return ResponseEntity.status(HttpStatus.CREATED).body(resultMap);
    }

    /**
     * 게시판 수정
     * @param dataObject
     * @return
     */
    @PostMapping(value="/update")
    public ResponseEntity updateBoard(@RequestBody @Valid DataObject dataObject, HttpServletRequest request){
        MemberVO member = ObjectHandlingUtil.extractLoginInfo(request);

        int inserted = getCommonService().getBoardService().updateBoard(dataObject.getData().getBoard(), member);
        ResultStatusVO resultStatusVO = ObjectHandlingUtil.setDataManipulationResultStatusVO(inserted, RequestTypeEnum.UPDATE);
        Map<String, Object> resultMap = getMapUtil().responseEntityBodyWrapper(resultStatusVO);

        return ResponseEntity.ok(resultMap);
    }

    /**
     * 게시판 삭제
     * @param dataObject
     * @return
     */
    @PostMapping(value="/delete")
    public ResponseEntity deleteBoard(@RequestBody @Valid DataObject dataObject,HttpServletRequest request){
        MemberVO member = ObjectHandlingUtil.extractLoginInfo(request);

        int deleted = getCommonService().getBoardService().deleteBoard(dataObject.getData().getBoard(), member);
        ResultStatusVO resultStatusVO = ObjectHandlingUtil.setDataManipulationResultStatusVO(deleted,RequestTypeEnum.DELETE);
        Map<String, Object> resultMap = getMapUtil().responseEntityBodyWrapper(resultStatusVO);

        return ResponseEntity.ok(resultMap);
    }
}
