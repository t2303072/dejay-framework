package com.dejay.framework.service.test;

import com.dejay.framework.common.utils.CryptoUtil;
import com.dejay.framework.domain.common.Paging;
import com.dejay.framework.vo.test.TestVO;
import com.dejay.framework.vo.common.PagingVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class TestService {

    private final CryptoUtil cryptoUtil;

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

    // TODO: IJ
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
}
