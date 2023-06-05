package com.dejay.framework.service;

import com.dejay.framework.mapper.TestMapper;
import com.dejay.framework.model.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {

    private TestMapper testMapper;

    @Autowired
    public TestService(TestMapper testMapper) {
        this.testMapper = testMapper;
    }

    public List<Test> getTestList() {
        return testMapper.getTestList();
    }
}
