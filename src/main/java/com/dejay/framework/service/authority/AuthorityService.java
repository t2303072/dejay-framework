package com.dejay.framework.service.authority;

import com.dejay.framework.domain.authority.Authority;
import com.dejay.framework.vo.code.CommonCodeVO;

import java.util.List;
import java.util.Map;

public interface AuthorityService {
    // 메뉴 조회
    List<CommonCodeVO> findMenuList();

    // 개인 권한 저장
    Map<String, Object> saveIndividualAuthority(List<Authority> list);
}
