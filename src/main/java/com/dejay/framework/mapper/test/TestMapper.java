package com.dejay.framework.mapper.test;

import com.dejay.framework.domain.test.Board;
import com.dejay.framework.vo.test.BoardVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestMapper {

    List<BoardVO> getBoardList();
    long insertBoard(Board target);
}
