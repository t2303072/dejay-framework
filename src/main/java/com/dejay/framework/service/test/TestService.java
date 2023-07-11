package com.dejay.framework.service.test;

import com.dejay.framework.common.utils.CryptoUtil;
import com.dejay.framework.common.utils.JwtUtil;
import com.dejay.framework.domain.common.Paging;
import com.dejay.framework.domain.member.LoginRequest;
import com.dejay.framework.vo.test.TestVO;
import com.dejay.framework.vo.common.PagingVO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class TestService {

    private final JwtUtil jwtUtil;
    private final CryptoUtil cryptoUtil;

    private Long expiredMs = 1000 * 60 * 60l;

    public List<TestVO> getTest() {
        List list = new ArrayList<>();
        list.add(new TestVO(1234, "test"));

        return list;
    }

    /**
     * Paging
     * @param currentPage
     * @param displayRow
     * @param totalCount
     * @return
     */
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

    // TODO: IJ 암호화 알고리즘 확인

    /**
     * 비밀번호 암호화 테스트
     */
    public void passwordEncode(@Valid LoginRequest loginRequest) {
        String rawPassword = "ijzone";
        String encodedPassword = cryptoUtil.encodePassword(loginRequest.getPassword());
        log.info("encodedPassword: {}", encodedPassword);
//        boolean passwordMatches = cryptoUtil.isPasswordMatches(loginRequest.getPassword(), encodedPassword);
//        log.info("passwordMatches? {}", passwordMatches);
    }

    /**
     * 생성된 JWT 반환 테스트
     * @param userName
     * @param password
     * @return
     */
    public String loginReturnJwt(String userName, String password, String[] list) {
        return jwtUtil.createJwt(userName, expiredMs, list);
    }
}
