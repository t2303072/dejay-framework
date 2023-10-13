package com.dejay.framework.mapper.code;

import com.dejay.framework.domain.code.Code;
import com.dejay.framework.mapper.common.GeneralMapper;
import com.dejay.framework.vo.code.CodePublicVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CodeMapper extends GeneralMapper {

    /**
     * 코드 순서 일괄변경
     * @param list
     * @return
     */
    int updateCodeOrder(Code code);

    /**
     * 사이드바 메뉴 조회
     * @return
     */
    List<CodePublicVO> listCode();
}
