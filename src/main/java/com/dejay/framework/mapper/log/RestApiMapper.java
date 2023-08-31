package com.dejay.framework.mapper.log;

import com.dejay.framework.domain.log.RestAPI;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RestApiMapper {

    long insertApiAccessLog(RestAPI restApi);
    long updateApiAccessLog(RestAPI restApi);
}
