package com.dejay.framework.mapper.authority;

import com.dejay.framework.domain.authority.Authority;
import com.dejay.framework.domain.code.CommonCode;
import com.dejay.framework.mapper.common.GeneralMapper;
import com.dejay.framework.vo.authority.MenuAuthorityVO;
import com.dejay.framework.vo.code.CommonCodeVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AuthorityMapper extends GeneralMapper {

    /**
     * 메뉴 조회
     * @param memberId
     * @param menuId
     * @return
     */
    MenuAuthorityVO selectMenuAuthority(@Param("memberId") String memberId, @Param("menuId") String menuId);

    /**
     * 부서 목록 조회
     * @return
     */
    List<CommonCodeVO> findDepartmentList();

    /**
     * 개인 권한 저장
     * @param authority
     * @return
     */
    int saveIndividualAuthority(Authority authority);

    /**
     * 개인 권한 수정
     * @param authority
     * @return
     */
    int updateIndividualAuthority(Authority authority);
}
