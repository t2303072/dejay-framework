package com.dejay.framework.mapper.code;

import com.dejay.framework.domain.code.Code;
import com.dejay.framework.mapper.common.GeneralMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CodeMapper extends GeneralMapper {

    /**
     * 코드 순서 일괄변경
     * @param list
     * @return
     */
    int updateCodeOrder(Code code);
}
