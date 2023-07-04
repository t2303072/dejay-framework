package com.dejay.framework.controller.test;

import com.dejay.framework.common.enums.MapKeyStringEnum;
import com.dejay.framework.common.utils.MapUtil;
import com.dejay.framework.domain.member.LoginRequest;
import com.dejay.framework.service.test.TestService;
import com.dejay.framework.vo.common.ResultStatusVO;
import com.dejay.framework.vo.test.TestVO;
import com.dejay.framework.vo.common.PagingVO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/test")
public class TestController {

    private final TestService testService;
    private final MapUtil mapUtil;

    /**
     * Index test
     * @return
     */
    @GetMapping({"", "/"})
    public ResponseEntity index() {
        List<TestVO> list = testService.getTest();

        return ResponseEntity.ok(list);
    }

    /**
     * Paging test
     * @param currentPage
     * @param displayRow
     * @param totalCount
     * @return
     */
    @GetMapping("paging")
    public ResponseEntity paging(@RequestParam int currentPage, @RequestParam int displayRow, @RequestParam(required = false) int totalCount) {
        // TODO: IJ Basically, GET METHOD has no body object
        PagingVO paging = testService.paging(currentPage, displayRow, totalCount);
        var mapKeyList = Arrays.asList(MapKeyStringEnum.PAGING.getKeyString());
        Map<String, Object> resultMap = mapUtil.responseEntityBodyWrapper(new ResultStatusVO(), mapKeyList, paging);

        return ResponseEntity.ok(resultMap);
    }

    /**
     * 비밀번호 암호화 test
     * @return
     */
    @GetMapping("password-encode")
    public ResponseEntity passwordEncode() {
        testService.passwordEncode();

        return ResponseEntity.ok().build();
    }

    /**
     * 로그인 토큰 생성 test
     * @param loginRequest
     * @param authentication
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginRequest loginRequest, Authentication authentication) {
        return ResponseEntity.ok().body(testService.loginReturnJwt(loginRequest.getUserName(), loginRequest.getPassword()));
    }

    /**
     * 토큰인증 정보 확인
     * @param authentication
     * @return
     */
    @PostMapping("/authentication-info")
    public ResponseEntity authentication(Authentication authentication) {
        log.info("Authentication: userName => {}", authentication.getName());
        authentication.getAuthorities().forEach(a -> log.info("권한: {}", a.getAuthority()));
        return ResponseEntity.ok("userName: " + authentication.getName() + " / authorities: " + authentication.getAuthorities());
    }

}
