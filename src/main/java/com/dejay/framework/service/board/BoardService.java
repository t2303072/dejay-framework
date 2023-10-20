package com.dejay.framework.service.board;

import com.dejay.framework.domain.board.Board;
import com.dejay.framework.domain.file.File;
import com.dejay.framework.vo.board.BoardPublicVO;
import com.dejay.framework.vo.board.BoardVO;
import com.dejay.framework.vo.file.FileVO;
import com.dejay.framework.vo.member.MemberVO;
import com.dejay.framework.vo.search.board.BoardSearchVO;

import java.util.List;

public interface BoardService  {
    // 게시판 저장
    int insertBoard(Board board, List<File> files, MemberVO member) throws Exception;

    // 게시판 페이징 조회
    List<BoardVO> pagingBoard(BoardSearchVO search);

    // 게시판 단 건 조회
    BoardVO rowBoard(BoardSearchVO search);

    // 단 건 게시판 파일 조회
    List<FileVO> boardFileSearch(BoardSearchVO search);

    // 게시판 수정
    int updateBoard(Board board, List<File> fileList, MemberVO member) throws Exception;

    // 게시판 삭제
    int deleteBoard(Board board, MemberVO member);

    // 공통 게시판 단 건 조회
    BoardPublicVO findById(BoardSearchVO boardSearchVO);

    // 공통 게시판 목록 조회
    List<BoardPublicVO> findAll(BoardSearchVO boardSearchVO);

    // 조회수 증가
    void increaseHits(long boardSeq);
}
