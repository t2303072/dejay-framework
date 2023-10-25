package com.dejay.framework.service.code;

import com.dejay.framework.vo.code.CodePublicVO;
import com.dejay.framework.vo.code.CodeVO;
import com.dejay.framework.vo.code.CommonCodeVO;
import com.dejay.framework.vo.common.SelectOptionVO;
import com.dejay.framework.vo.search.code.CodeSearchVO;

import java.util.List;

public interface CodeService {
   // 사이드바 메뉴 조회
   List<CodePublicVO> listCode();

   // 공통 코드 그룹
   List<SelectOptionVO> commonCodeGroupList();

   // 공통 코드 구분(카테고리)
   List<SelectOptionVO> commonCodeCategoryList(String code);

   // 공통 코드 조회
   List<CommonCodeVO> findAll(CodeSearchVO codeSearchVO);
}
