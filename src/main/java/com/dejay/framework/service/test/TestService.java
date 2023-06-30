package com.dejay.framework.service.test;

import com.dejay.framework.domain.common.Paging;
import com.dejay.framework.vo.test.TestVO;
import com.dejay.framework.vo.common.PagingVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
        String result = encoder.encode("ijzone");
        log.info("matches => {}", encoder.matches("ijzone", result));

    }
}
