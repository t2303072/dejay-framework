package com.dejay.framework.service.board;

import com.dejay.framework.common.utils.DateUtil;
import com.dejay.framework.domain.board.Board;
import com.dejay.framework.domain.file.File;
import com.dejay.framework.service.common.ParentService;
import com.dejay.framework.vo.board.BoardPublicVO;
import com.dejay.framework.vo.board.BoardVO;
import com.dejay.framework.vo.file.FileVO;
import com.dejay.framework.vo.member.MemberVO;
import com.dejay.framework.vo.search.board.BoardSearchVO;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;

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
    public List<BoardPublicVO> getList() {
        List<BoardPublicVO> list = getCommonMapper().getBoardMapper().getList();
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
}
