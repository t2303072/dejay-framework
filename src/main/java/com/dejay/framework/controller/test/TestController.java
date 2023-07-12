package com.dejay.framework.controller.test;

import com.dejay.framework.common.enums.MapKeyStringEnum;
import com.dejay.framework.common.utils.JwtUtil;
import com.dejay.framework.common.utils.MapUtil;
import com.dejay.framework.common.utils.ObjectHandlingUtil;
import com.dejay.framework.domain.member.LoginRequest;
import com.dejay.framework.service.test.TestService;
import com.dejay.framework.vo.common.ResultStatusVO;
import com.dejay.framework.vo.common.TokenVO;
import com.dejay.framework.vo.test.TestVO;
import com.dejay.framework.vo.common.PagingVO;
import jakarta.servlet.http.HttpServletRequest;
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
    private final JwtUtil jwtUtil;

    /**
     * Index test
     * @return
     */
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
    @PostMapping("password-encode")
    public ResponseEntity passwordEncode(@RequestBody @Valid LoginRequest loginRequest) {
        testService.passwordEncode(loginRequest);
        return ResponseEntity.ok().build();
    }

}
