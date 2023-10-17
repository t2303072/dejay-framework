package com.dejay.framework.service.board;

import com.dejay.framework.common.utils.DateUtil;
import com.dejay.framework.domain.board.Board;
import com.dejay.framework.domain.file.File;
import com.dejay.framework.service.common.ParentService;
import com.dejay.framework.vo.board.BoardPublicVO;
import com.dejay.framework.vo.board.BoardVO;
import com.dejay.framework.vo.common.SelectOptionVO;
import com.dejay.framework.vo.file.FileVO;
import com.dejay.framework.vo.member.MemberVO;
import com.dejay.framework.vo.search.SearchVO;
import com.dejay.framework.vo.search.board.BoardSearchVO;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public BoardPublicVO findById(BoardSearchVO search) {
//        search.setEntityName(TableNameEnum.BOARD.name());

        BoardPublicVO rowData = getCommonMapper().getBoardMapper().findById(search);
        rowData.setRegDtStr(DateUtil.convertLocalDateTimeToString(rowData.getRegDt(), "yyyy-MM-dd"));
        rowData.setLastDtStr(DateUtil.convertLocalDateTimeToString(rowData.getLastDt(), "yyyy-MM-dd"));

        // 게시판에 물린 파일 목록 가져오기
//        board.setFileList(boardFileSearch(search));

        return rowData;
    }

    @Override
    public List<BoardPublicVO> findAll(BoardSearchVO boardSearchVO) {
        int totalCount = this.totalCount(boardSearchVO);
        if(totalCount < 1) {
            return new ArrayList<>();
        }

        List<BoardPublicVO> list = getCommonMapper().getBoardMapper().findAll(boardSearchVO);
        list.forEach(ele -> {
            ele.setRegDtStr(DateUtil.convertLocalDateTimeToString(ele.getRegDt(), "yyyy-MM-dd"));
            ele.setLastDtStr(DateUtil.convertLocalDateTimeToString(ele.getLastDt(), "yyyy-MM-dd"));
        });

        return list;
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
     * 검색 날짜 범위
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
     * 검색 키워드 타입
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
     * 공통 게시판 전체 게시물 수
     * @param boardSearchVO {@link BoardSearchVO}
     * @return {@link Integer}
     */
    public int totalCount(BoardSearchVO boardSearchVO) {
        return getCommonMapper().getBoardMapper().findAllTotalCount(boardSearchVO);
    }

    public Map<String, Object> deleteBySeq(List<Integer> tgtList) {
        var result = new HashMap<String, Object>();
        result.put("code", 200);

        var list = new HashMap<String, Object>();
        list.put("boardSeqList", tgtList);
        int deleteCount = getCommonMapper().getBoardMapper().deleteList(list);
        if(deleteCount < 1) {
            result.put("code", 204);
            result.put("message", "삭제할 대상이 없습니다.");
            return result;
        }
        result.put("message", "삭제 되었습니다.");

        return result;
    }
}
