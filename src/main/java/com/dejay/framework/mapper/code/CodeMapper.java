package com.dejay.framework.mapper.code;

import com.dejay.framework.domain.code.Code;
import com.dejay.framework.domain.code.CommonCode;
import com.dejay.framework.mapper.common.GeneralMapper;
import com.dejay.framework.vo.code.CodePublicVO;
import com.dejay.framework.vo.code.CommonCodeVO;
import com.dejay.framework.vo.common.SelectOptionVO;
import com.dejay.framework.vo.search.code.CodeSearchVO;
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

    /**
     * 코드 그룹 조회
     * @return
     */
    List<SelectOptionVO> commonCodeGroupList();

    /**
     * 공통 코드 구분(카테고리)
     * @return
     */
    List<SelectOptionVO> commonCodeCategoryList(String code);

    /**
     * 공통 코드 목록 조회
     * @param codeSearchVO
     * @return
     */
    List<CommonCodeVO> findAll(CodeSearchVO codeSearchVO);

    /**
     * 공통 코드 저장
     * @param commonCode
     * @return
     */
    int saveCommonCode(CommonCode commonCode);
    /**
     * 공통 코드 수정
     * @param commonCode
     * @return
     */
    int updateCommonCode(CommonCode commonCode);
}
