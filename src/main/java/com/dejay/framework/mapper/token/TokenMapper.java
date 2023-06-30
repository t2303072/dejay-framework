package com.dejay.framework.mapper.token;

import com.dejay.framework.domain.token.Token;
import com.dejay.framework.vo.common.AuthenticationResponse;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TokenMapper {

    void saveToken(Token token);
}
