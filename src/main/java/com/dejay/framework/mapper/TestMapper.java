package com.dejay.framework.mapper;

import com.dejay.framework.model.Test;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestMapper {

    List<Test> getTestList();
}
