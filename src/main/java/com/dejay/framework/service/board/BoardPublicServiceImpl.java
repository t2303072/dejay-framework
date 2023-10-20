package com.dejay.framework.service.board;

import com.dejay.framework.common.utils.DateUtil;
import com.dejay.framework.domain.board.Board;
import com.dejay.framework.domain.board.BoardPublic;
import com.dejay.framework.domain.file.File;
import com.dejay.framework.service.common.ParentService;
import com.dejay.framework.vo.board.BoardPublicVO;
import com.dejay.framework.vo.board.BoardVO;
import com.dejay.framework.vo.common.SelectOptionVO;
import com.dejay.framework.vo.file.FileVO;
import com.dejay.framework.vo.member.MemberVO;
import com.dejay.framework.vo.search.SearchVO;
import com.dejay.framework.vo.search.board.BoardSearchVO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.*;

@Primary
@Component
public class BoardPublicServiceImpl extends ParentService implements BoardService {

    @Override
    public int insertBoard(Board board, List<File> files, MemberVO member) throws Exception {
        return 0;
    }

    @Override
    public List<BoardVO> pagingBoard(BoardSearchVO search) {
        return null;
    }

    @Override
    public BoardVO rowBoard(BoardSearchVO search) {
        return null;
    }

    /**
     * 게시판 단 건 조회
     * @param search
     * @return rowData {@link BoardPublicVO}
     */
    @Override
    public BoardPublicVO findById(BoardSearchVO search) {
//        search.setEntityName(TableNameEnum.BOARD.name());

        BoardPublicVO rowData = getCommonMapper().getBoardMapper().findById(search);
        rowData.setRegDtStr(DateUtil.convertLocalDateTimeToString(rowData.getRegDt(), "yyyy-MM-dd HH:mm"));
//        rowData.setLastDtStr(DateUtil.convertLocalDateTimeToString(rowData.getLastDt(), "yyyy-MM-dd HH:mm"));

        // 게시판에 물린 파일 목록 가져오기
//        board.setFileList(boardFileSearch(search));

        return rowData;
    }

    /**
     * 게시판 다 건 조회
     * @param boardSearchVO
     * @return list {@link List}
     */
    @Override
    public List<BoardPublicVO> findAll(BoardSearchVO boardSearchVO) {
        int totalCount = this.totalCount(boardSearchVO);
        if(totalCount < 1) {
            return new ArrayList<>();
        }

        List<BoardPublicVO> list = getCommonMapper().getBoardMapper().findAll(boardSearchVO);
        list.forEach(ele -> {
            ele.setRegDtStr(DateUtil.convertLocalDateTimeToString(ele.getRegDt(), "yyyy-MM-dd HH:mm"));
//            ele.setLastDtStr(DateUtil.convertLocalDateTimeToString(ele.getLastDt(), "yyyy-MM-dd HH:mm"));
        });

        return list;
    }

    @Override
    public void increaseHits(long boardSeq) {
        int updateCount = getCommonMapper().getBoardMapper().increaseHits(boardSeq);
        System.out.println("updateCount " + updateCount);
    }

    @Override
    public List<FileVO> boardFileSearch(BoardSearchVO search) {
        return null;
    }

    @Override
    public int updateBoard(Board board, List<File> fileList, MemberVO member) throws Exception {
        return 0;
    }

    @Override
    public int deleteBoard(Board board, MemberVO member) {
        return 0;
    }

    /**
     * 검색 날짜 범위 조회
     * @return list {@link List}
     */
    public List<SelectOptionVO> getSearchDateRangeOptionList() {
        var list = new ArrayList<SelectOptionVO>();
        list.add(new SelectOptionVO("ALL", "전체"));
        list.add(new SelectOptionVO("REG_DT", "등록일"));
//        list.add(new SelectOptionVO("WEEK", "1 week"));
//        list.add(new SelectOptionVO("ONE", "1 month"));
//        list.add(new SelectOptionVO("THREE", "3 months"));
//        list.add(new SelectOptionVO("SIX", "6 months"));
//        list.add(new SelectOptionVO("YEAR", "1 year"));

        return list;
    }

    /**
     * 검색 키워드 타입 조회
     * @return list {@link List}
     */
    public List<SelectOptionVO> getSearchKeywordTypeList() {
        var list = new ArrayList<SelectOptionVO>();
        list.add(new SelectOptionVO("ALL", "전체"));
        list.add(new SelectOptionVO("TITLE", "제목"));
        list.add(new SelectOptionVO("REG_ID", "등록자"));
//        list.add(new SelectOptionVO("SEQ", "게시글 번호"));
//        list.add(new SelectOptionVO("TITLE", "제목"));
//        list.add(new SelectOptionVO("CONTENTS", "내용"));
//        list.add(new SelectOptionVO("REG_ID", "등록자"));

        return list;
    }

    /**
     * 공통 게시판 전체 게시물 수 조회
     * @param boardSearchVO {@link BoardSearchVO}
     * @return {@link Integer}
     */
    public int totalCount(BoardSearchVO boardSearchVO) {
        return getCommonMapper().getBoardMapper().findAllTotalCount(boardSearchVO);
    }

    /**
     * 게시판 삭제
     * @param lastId {@link String}
     * @param tgtList {@link Map}
     * @return result {@link Map}
     */
    public Map<String, Object> deleteBySeq(String lastId, List<Integer> tgtList) {
        // TODO 삭제 처리 전 해당 시퀀스 조회
        var result = new HashMap<String, Object>();
        result.put("code", 200);

        var paramMap = new HashMap<String, Object>();
        paramMap.put("lastId", lastId);
        paramMap.put("boardSeqList", tgtList);
        int deleteCount = getCommonMapper().getBoardMapper().deleteList(paramMap);
        if(deleteCount < 1) {
            result.put("code", 204);
            result.put("message", "삭제할 대상이 없습니다.");
            return result;
        }
        result.put("message", "삭제 되었습니다.");

        return result;
    }

    /**
     * 게시판 수정
     * @param board {@link Board}
     * @return result {@link Map}
     */
    public Map<String, Object> updateBoard(Board board) {
        // TODO 수정 전 해당 시퀀스 조회
        var result = new HashMap<String, Object>();
        result.put("code", 200);

        int updateCount = getCommonMapper().getBoardMapper().updateBoard(board);
        if(updateCount < 1) {
            result.put("code", 204);
            result.put("message", "수정할 대상이 없습니다.");
            return result;
        }
        result.put("message", "수정 되었습니다.");

        return result;
    }

    public Map<String, Object> registration(BoardPublic boardPublic) {
        var result = new HashMap<String, Object>();
        result.put("code", 200);

        BoardPublic target = BoardPublic.builder()
                .boardCd(boardPublic.getBoardCd())
                .title(boardPublic.getTitle())
                .contents(boardPublic.getContents())
                .regId(boardPublic.getRegId())
                .build();

        Map<String, Object> validated = getValidationUtil().clientRequestParameterValidator(target, BoardPublic.class);
        if(!validated.isEmpty()) {
            result.put("code", 400);
            result.put("message", "잘못된 요청");
            result.put("errMsg", validated);
            return result;
        }

        int regCount = getCommonMapper().getBoardMapper().registrationBoard(boardPublic);
        if(regCount < 1) {
            result.put("code", 204);
            result.put("message", "등록에 실패 했습니다.");
            result.put("errMsg", "incomplete");
            return result;
        }

        result.put("message", "등록 되었습니다.");

        return result;
    }
}
