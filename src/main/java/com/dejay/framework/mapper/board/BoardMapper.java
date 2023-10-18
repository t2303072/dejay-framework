package com.dejay.framework.mapper.board;

import com.dejay.framework.domain.board.Board;
import com.dejay.framework.mapper.common.GeneralMapper;
import com.dejay.framework.vo.board.BoardPublicVO;
import com.dejay.framework.vo.file.FileVO;
import com.dejay.framework.vo.search.SearchVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface BoardMapper extends GeneralMapper {
    List<FileVO> boardFileSearch(SearchVO search);

    /**
     * 공통 게시판 전체 게시물 수
     * @param searchVO
     * @return int {@link Integer}
     */
    int findAllTotalCount(SearchVO searchVO);

    /**
     * 공통 게시판 목록 조회
     * @return
     */
    List<BoardPublicVO> findAll(SearchVO searchVO);

    /**
     * 공통 게시판 단건 조회
     * @param search {@link SearchVO}
     * @return
     */
    BoardPublicVO findById(SearchVO search);

    /**
     * 공통 게시판 삭제
     * @param list
     * @return
     */
    int deleteList(Map<String, Object> list);

    /**
     * 공통 게시판 수정
     * @param board {@link Board}
     * @return {@link Integer}
     */
    int updateBoard(Board board);
}
