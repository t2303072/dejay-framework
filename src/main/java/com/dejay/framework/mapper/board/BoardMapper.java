package com.dejay.framework.mapper.board;

import com.dejay.framework.mapper.common.GeneralMapper;
import com.dejay.framework.vo.file.FileVO;
import com.dejay.framework.vo.search.SearchVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper extends GeneralMapper {
    List<FileVO> boardFileSearch(SearchVO search);

    /**
     * 공통 게시판 단건 조회
     * @param search {@link SearchVO}
     * @return
     */
    Object findById(SearchVO search);
}
