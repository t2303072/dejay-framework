package com.dejay.framework.controller.test;

import com.dejay.framework.common.enums.MapKeyStringEnum;
import com.dejay.framework.common.utils.MapUtil;
import com.dejay.framework.service.test.TestService;
import com.dejay.framework.vo.common.ResultStatusVO;
import com.dejay.framework.vo.test.TestVO;
import com.dejay.framework.vo.common.PagingVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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

    @GetMapping({"", "/"})
    public ResponseEntity index() {
        List<TestVO> list = testService.getTest();
        return ResponseEntity.ok(list);
    }

    @GetMapping("paging")
    public ResponseEntity paging(@RequestParam int currentPage, @RequestParam int displayRow, @RequestParam(required = false) int totalCount) {
        PagingVO paging = testService.paging(currentPage, displayRow, totalCount);
        var mapKeyList = Arrays.asList(MapKeyStringEnum.PAGING.getKeyString());
        Map<String, Object> resultMap = mapUtil.responseEntityBodyWrapper(new ResultStatusVO(), mapKeyList, paging);

        return ResponseEntity.ok(resultMap);
    }
}
