package com.dejay.framework.mapper.common;

import com.dejay.framework.domain.common.BaseEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EntityLogMapper extends GeneralMapper {

    String getTablePrimaryKey(String tableName);

    int saveEntityLogData(BaseEntity baseEntity);
}
