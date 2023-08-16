package com.dejay.framework.mapper.token;

import com.dejay.framework.domain.token.Token;
import com.dejay.framework.mapper.common.GeneralMapper;
import com.dejay.framework.vo.authority.MenuAuthorityVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TokenMapper extends GeneralMapper {

    int isTokenExist(String memberId);
    boolean isValidToken(String accessToken);
}
