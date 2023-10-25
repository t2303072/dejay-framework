package com.dejay.framework.service.authority;

import com.dejay.framework.vo.code.CommonCodeVO;

import java.util.List;

public interface AuthorityService {
    // 메뉴 조회
    List<CommonCodeVO> findMenuList();
}
