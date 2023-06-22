package com.dejay.framework.mapper.log;

import com.dejay.framework.domain.log.RestApi;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RestApiMapper {

    long insertApiAccessLog(RestApi restApi);
    long updateApiAccessLog(RestApi restApi);
}
