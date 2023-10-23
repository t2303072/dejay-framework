package com.dejay.framework.mapper.record;

import com.dejay.framework.mapper.common.GeneralMapper;
import com.dejay.framework.vo.record.RecordVO;
import com.dejay.framework.vo.search.SearchVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RecordMapper extends GeneralMapper {

    /**
     * 이력 내역 total
     * @param searchVO
     * @return
     */
    int findAllTotalCount(SearchVO searchVO);

    /**
     * 이력 목록 조회
     * @param searchVO
     * @return
     */
    List<RecordVO> findAll(SearchVO searchVO);

    /**
     * 이력 단 건 조회
     * @param logSeq
     * @return
     */
    RecordVO findById(long logSeq);


}
