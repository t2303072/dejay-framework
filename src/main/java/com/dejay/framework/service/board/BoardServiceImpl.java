package com.dejay.framework.service.board;

import com.dejay.framework.common.enums.RequestTypeEnum;
import com.dejay.framework.common.enums.TableNameEnum;
import com.dejay.framework.common.utils.ObjectHandlingUtil;
import com.dejay.framework.common.utils.StringUtil;
import com.dejay.framework.domain.board.Board;
import com.dejay.framework.domain.common.Paging;
import com.dejay.framework.domain.file.File;
import com.dejay.framework.service.common.ParentService;
import com.dejay.framework.service.file.FileService;
import com.dejay.framework.vo.board.BoardPublicVO;
import com.dejay.framework.vo.board.BoardVO;
import com.dejay.framework.vo.file.FileVO;
import com.dejay.framework.vo.member.MemberVO;
import com.dejay.framework.vo.search.board.BoardSearchVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BoardServiceImpl extends ParentService implements BoardService{

    @Autowired
    private FileService fileService;

    /**
     * 게시판 저장
     * @param board
     * @return
     * @throws Exception
     */
    public int insertBoard(Board board, List<File> files, MemberVO member) throws Exception{

        Board target = Board.builder()
                            .boardCode(board.getBoardCode())
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
                            .regId(member.getUserId())
                            .build();

        int iAffectedRows = getCommonMapper().getBoardMapper().insert(target);


        //파일 저장
        if(StringUtil.isNotEmpty(files)) {
            fileService.saveFile(files, TableNameEnum.BOARD.name(), Long.valueOf(target.getLogId1()));
        }

        return iAffectedRows;
    }

    /**
     * 게시판 페이징 조회
     * @param search
     * @return
     */
    public List<BoardVO> pagingBoard(BoardSearchVO search){
        search.setEntityName(TableNameEnum.BOARD.name());

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
        search.setEntityName(TableNameEnum.BOARD.name());

        BoardVO board = (BoardVO) getCommonMapper().getBoardMapper().rowBySearch(search);

        // 게시판에 물린 파일 목록 가져오기
        board.setFileList(boardFileSearch(search));

        return board;
    }

    @Override
    public BoardPublicVO findById(BoardSearchVO search) {
        return null;
    }

    /**
     *  단 건 게시판 파일 조회
     * @param search
     * @return
     */
    public List<FileVO> boardFileSearch(BoardSearchVO search){
        return  getCommonMapper().getBoardMapper().boardFileSearch(search);
    }

    /**
     * 게시판 수정
     * @param board
     * @return
     */
    public int updateBoard(Board board, List<File> fileList, MemberVO member) throws Exception {
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
                            .regId(member.getUserId())
                            .build();

        // Mapper Update
        int iAffectedRows = getCommonMapper().getBoardMapper().update(target);

        //File Update
        fileService.updateFile(fileList, TableNameEnum.BOARD.name(), target.getBoardSeq());

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
                            .regId(member.getUserId())
                            .build();

        int iAffectedRows = getCommonMapper().getBoardMapper().delete(target);

        // Board에 물린 File삭제
        List<FileVO> fileList = fileService.getFiles(board.getBoardSeq(), target.getTableName());
        for(FileVO file : fileList){
            fileService.deleteFile(file.getFileName());
        }

        return iAffectedRows;
    }

}
