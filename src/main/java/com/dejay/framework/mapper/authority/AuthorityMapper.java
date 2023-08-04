package com.dejay.framework.mapper.authority;

import com.dejay.framework.mapper.common.GeneralMapper;
import com.dejay.framework.vo.authority.MenuAuthorityVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AuthorityMapper extends GeneralMapper {

    MenuAuthorityVO selectMenuAuthority(String memberId, String menuId);
}
