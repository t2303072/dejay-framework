package com.dejay.framework.mapper.authority;

import com.dejay.framework.domain.authority.Authority;
import com.dejay.framework.domain.code.CommonCode;
import com.dejay.framework.mapper.common.GeneralMapper;
import com.dejay.framework.vo.authority.MenuAuthorityVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AuthorityMapper extends GeneralMapper {

    MenuAuthorityVO selectMenuAuthority(@Param("memberId") String memberId, @Param("menuId") String menuId);

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
