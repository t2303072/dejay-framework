package com.dejay.framework.restController.test;

import com.dejay.framework.common.config.JasyptConfig;
import com.dejay.framework.common.enums.MapKeyStringEnum;
import com.dejay.framework.common.utils.MapUtil;
import com.dejay.framework.domain.member.LoginRequest;
import com.dejay.framework.service.test.TestService;
import com.dejay.framework.vo.common.PagingVO;
import com.dejay.framework.vo.common.ResultStatusVO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/test")
public class TestController {

    private final TestService testService;
    private final MapUtil mapUtil;
    private final JasyptConfig jasyptConfig;

    /**
     * Jasypt 암호화 test
     * @return
     */
    @GetMapping("/jasypt-encryption")
    public ResponseEntity jasyptEncoding() {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("url", jasyptConfig.jasyptEncrypt("jdbc:log4jdbc:mariadb://10.34.220.168:3307/dejay_common"));
        resultMap.put("username", jasyptConfig.jasyptEncrypt("dejay_was"));
        resultMap.put("password", jasyptConfig.jasyptEncrypt("22@dejay"));

        return ResponseEntity.ok(resultMap);
    }

    /**
     * Index test
     * @return
     */
    @Deprecated
    @GetMapping({"", "/"})
    public ResponseEntity index() {
        return ResponseEntity.ok(testService.getTest());
    }

    /**
     * Paging test
     * @param currentPage
     * @param displayRow
     * @param totalCount
     * @return
     */
    @Deprecated
    @GetMapping("paging")
    public ResponseEntity paging(@RequestParam int currentPage, @RequestParam int displayRow, @RequestParam(required = false) int totalCount) {
        PagingVO paging = testService.paging(currentPage, displayRow, totalCount);
        var mapKeyList = Arrays.asList(MapKeyStringEnum.PAGING.getKeyString());
        Map<String, Object> resultMap = mapUtil.responseEntityBodyWrapper(new ResultStatusVO(), mapKeyList, paging);

        return ResponseEntity.ok(resultMap);
    }

    /**
     * 비밀번호 암호화 test
     * @return
     */
    @Deprecated
    @PostMapping("password-encode")
    public ResponseEntity passwordEncode(@RequestBody @Valid LoginRequest loginRequest) {
        testService.passwordEncode(loginRequest);
        return ResponseEntity.ok().build();
    }

    @Deprecated
    @PostMapping("authorized-only")
    public ResponseEntity authorizedOnlyAccess(Authentication authentication) {
        log.info("Authentication: {}", authentication.toString());
        return ResponseEntity.ok(authentication);
    }

}