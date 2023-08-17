package com.dejay.framework.controller.test;

import com.dejay.framework.common.enums.MapKeyStringEnum;
import com.dejay.framework.common.enums.ResultCodeMsgEnum;
import com.dejay.framework.common.utils.JwtUtil;
import com.dejay.framework.common.utils.MapUtil;
import com.dejay.framework.common.utils.ObjectHandlingUtil;
import com.dejay.framework.controller.common.ParentController;
import com.dejay.framework.domain.member.LoginRequest;
import com.dejay.framework.domain.test.Board;
import com.dejay.framework.service.test.TestService;
import com.dejay.framework.vo.common.ResultStatusVO;
import com.dejay.framework.vo.common.TokenVO;
import com.dejay.framework.vo.test.BoardVO;
import com.dejay.framework.vo.test.TestVO;
import com.dejay.framework.vo.common.PagingVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/test")
public class TestController extends ParentController {

    private final TestService testService;

    /**
     * Index test
     * @return
     */
    @GetMapping({"", "/"})
    public ResponseEntity index() {
        return ResponseEntity.ok(testService.getTest());
    }

    /**
     * Paging test
     * @param currentPage
     * @param displayRow
     * @param totalCount
     * @return
     */
    @GetMapping("paging")
    public ResponseEntity paging(@RequestParam int currentPage, @RequestParam int displayRow, @RequestParam(required = false) int totalCount) {
        PagingVO paging = testService.paging(currentPage, displayRow, totalCount);
        var mapKeyList = Arrays.asList(MapKeyStringEnum.PAGING.getKeyString());
        Map<String, Object> resultMap = getMapUtil().responseEntityBodyWrapper(new ResultStatusVO(), mapKeyList, paging);

        return ResponseEntity.ok(resultMap);
    }

    /**
     * 비밀번호 암호화 test
     * @return
     */
    @PostMapping("password-encode")
    public ResponseEntity passwordEncode(@RequestBody @Valid LoginRequest loginRequest) {
        testService.passwordEncode(loginRequest);
        return ResponseEntity.ok().build();
    }

    /**
     * 게시판 리스트 더미 테스트
     * @return
     */
    @GetMapping("board-list")
    public ResponseEntity boardList() {
        List<BoardVO> boardList = testService.getBoardList();
        ResultStatusVO resultStatusVO = ObjectHandlingUtil.setListResultStatusVO(boardList, ResultCodeMsgEnum.NO_DATA);
        var mapKeyList = Arrays.asList(MapKeyStringEnum.BOARD_LIST.getKeyString());
        Map<String, Object> resultMap = getMapUtil().responseEntityBodyWrapper(resultStatusVO, mapKeyList, boardList);

        return ResponseEntity.ok(resultMap);
    }

    /**
     * 게시판 등록 더미 테스트
     * @param board
     * @return
     */
    @PostMapping("board-insert")
    public ResponseEntity insertBoard(@RequestBody @Valid Board board) {
        long inserted = testService.insertBoard(board);
        var mapKeyList = Arrays.asList(MapKeyStringEnum.BOARD.getKeyString());
        Map<String, Object> resultMap = getMapUtil().responseEntityBodyWrapper(new ResultStatusVO(), mapKeyList, inserted);

        return ResponseEntity.ok(resultMap);
    }

    @PostMapping("authorized-only")
    public ResponseEntity authorizedOnlyAccess(Authentication authentication) {
        log.info("Authentication: {}", authentication.toString());
        return ResponseEntity.ok(authentication);
    }
}
