package com.dejay.framework.service;

import com.dejay.framework.domain.common.Paging;
import com.dejay.framework.vo.TestVO;
import com.dejay.framework.vo.common.PagingVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class TestService {

    public List<TestVO> getTest() {
        List list = new ArrayList<>();
        list.add(new TestVO(1234, "test"));

        return list;
    }

    public PagingVO paging(int currentPage, int displayRow, int totalCount) {
        Paging paging = Paging.builder()
                .currentPage(currentPage)
                .displayRow(displayRow)
                .totalCount(totalCount)
                .build();

        return PagingVO.builder()
                .totalCount(paging.getTotalCount())
                .totalPage(paging.getTotalPage())
                .build();
    }
}
