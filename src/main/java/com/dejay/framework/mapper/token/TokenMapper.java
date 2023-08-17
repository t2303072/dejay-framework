package com.dejay.framework.mapper.token;

import com.dejay.framework.mapper.common.GeneralMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TokenMapper extends GeneralMapper {

    int isTokenExist(String memberId);
    boolean isValidToken(@Param("token") String token, @Param("reissue") String reissue);
}
