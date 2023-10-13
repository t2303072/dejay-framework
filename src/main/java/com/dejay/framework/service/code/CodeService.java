package com.dejay.framework.service.code;

import com.dejay.framework.vo.code.CodePublicVO;
import com.dejay.framework.vo.code.CodeVO;
import com.dejay.framework.vo.search.code.CodeSearchVO;

import java.util.List;

public interface CodeService {
   // 사이드바 메뉴 조회
   List<CodePublicVO> listCode();
}
