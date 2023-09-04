package com.dejay.framework.mapper.authority;

import com.dejay.framework.mapper.common.GeneralMapper;
import com.dejay.framework.vo.authority.MenuAuthorityVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AuthorityMapper extends GeneralMapper {

    MenuAuthorityVO selectMenuAuthority(@Param("memberId") String memberId, @Param("menuId") String menuId);
}
