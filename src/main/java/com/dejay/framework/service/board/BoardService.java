package com.dejay.framework.service.board;

import com.dejay.framework.common.enums.RequestTypeEnum;
import com.dejay.framework.common.enums.TableNameEnum;
import com.dejay.framework.common.utils.CommonUtil;
import com.dejay.framework.common.utils.ObjectHandlingUtil;
import com.dejay.framework.domain.board.Board;
import com.dejay.framework.domain.common.Paging;
import com.dejay.framework.service.common.ParentService;
import com.dejay.framework.vo.board.BoardVO;
import com.dejay.framework.vo.member.MemberVO;
import com.dejay.framework.vo.search.board.BoardSearchVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BoardService extends ParentService {


    /**
     * 게시판 저장
     * @param board
     * @return
     * @throws Exception
     */
    public int insertBoard(Board board, MemberVO member) throws Exception{

        Board target = Board.builder()
                            .boardCd(board.getBoardCd())
                            .title(board.getTitle())
                            .contents(board.getContents())
                            .fixYn(board.getFixYn())
                            .displayYn(board.getDisplayYn())
                            .useYn(board.getUseYn())
                            .tableName(TableNameEnum.BOARD.name())
                            .logId1("")
                            .logType(RequestTypeEnum.CREATE.getRequestType())
                            .logId2(null)
                            .logJson(null)
                            .remark(null)
                            .regId(member.getMemberId())
                            .build();

        int iAffectedRows = getCommonMapper().getBoardMapper().insert(target);

        return iAffectedRows;
    }

    /**
     * 게시판 페이징 조회
     * @param search
     * @return
     */
    public List<BoardVO> pagingBoard(BoardSearchVO search){
        int totalCount = getCommonMapper().getBoardMapper().pagingCountBySearch(search);
        Paging paging = ObjectHandlingUtil.pagingOperatorBySearch(search, totalCount);
        search.setPaging(paging);
        List<BoardVO> boardList = (List<BoardVO>) getCommonMapper().getBoardMapper().pagingBySearch(search);
        return boardList;
    }

    /**
     * 게시판 단 건 조회
     * @param search
     * @return
     */
    public BoardVO rowBoard(BoardSearchVO search){
        return (BoardVO) getCommonMapper().getBoardMapper().rowBySearch(search);
    }

    /**
     * 게시판 수정
     * @param board
     * @return
     */
    public int updateBoard(Board board, MemberVO member){
        Board target = Board.builder()
                            .boardSeq(board.getBoardSeq())
                            .title(board.getTitle())
                            .contents(board.getContents())
                            .fixYn(board.getFixYn())
                            .displayYn(board.getDisplayYn())
                            .useYn(board.getUseYn())
                            .tableName(TableNameEnum.BOARD.name())
                            .logId1(String.valueOf(board.getBoardSeq()))
                            .logType(RequestTypeEnum.UPDATE.getRequestType())
                            .logId2(null)
                            .logJson(null)
                            .remark(null)
                            .regId(member.getMemberId())
                            .build();

        // Mapper Update
        int iAffectedRows = getCommonMapper().getBoardMapper().update(target);

        return iAffectedRows;
    }

    /**
     * 게시판 삭제
     * @param board
     * @return
     */
    public int deleteBoard(Board board, MemberVO member){
        Board target = Board.builder()
                            .boardSeq(board.getBoardSeq())
                            .title(board.getTitle())
                            .contents(board.getContents())
                            .fixYn(board.getFixYn())
                            .displayYn(board.getDisplayYn())
                            .useYn(board.getUseYn())
                            .tableName(TableNameEnum.BOARD.name())
                            .logId1(String.valueOf(board.getBoardSeq()))
                            .logType(RequestTypeEnum.DELETE.getRequestType())
                            .logId2(null)
                            .logJson(null)
                            .remark(null)
                            .regId(member.getMemberId())
                            .build();

        int iAffectedRows = getCommonMapper().getBoardMapper().delete(target);

        return iAffectedRows;
    }

}
