package com.dejay.framework.service.test;

import com.dejay.framework.common.utils.CryptoUtil;
import com.dejay.framework.common.utils.JwtUtil;
import com.dejay.framework.domain.common.Paging;
import com.dejay.framework.vo.test.TestVO;
import com.dejay.framework.vo.common.PagingVO;
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

    private final CryptoUtil cryptoUtil;

    @Value("${jwt.secret}")
    private String secretKey;

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
     * 비밀번호 암호화
     */
    public void passwordEncode() {
        String rawPassword = "ijzone";
        String encodedPassword = cryptoUtil.encodePassword(rawPassword);
        log.info("encodedPassword: {}", encodedPassword);
        boolean passwordMatches = cryptoUtil.isPasswordMatches(rawPassword, encodedPassword);
        log.info("passwordMatches? {}", passwordMatches);
        cryptoUtil.isPasswordMatches("", "");
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
//        String result = encoder.encode("ijzone");
//        log.info("matches => {}", encoder.matches("ijzone", result));
//
//        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//        String ijzone = passwordEncoder.encode("ijzone");
//        String ijzone2 = passwordEncoder.encode("ijzone");
//        log.info("encoded password of ijzone: => {}", ijzone);
//        log.info("BCryptPassword => {}", encoder.matches("ijzone", ijzone));
//
//        User.UserBuilder users = User.withDefaultPasswordEncoder();
//        UserDetails user = users.username("user")
//                .password("password")
//                .roles("USER")
//                .build();
//        UserDetails admin = users.username("admin")
//                .password("password2")
//                .roles("USER", "ADMIN")
//                .build();
//
//        List authList = List.of(user.getAuthorities().toArray());
//        log.info("admin: {}", admin.toString());
//        log.info("user: {}", user.toString());
    }

    public String loginReturnJwt(String userName, String password) {
        return JwtUtil.createJwt(userName, secretKey, expiredMs);
    }
}
