package com.dejay.framework.controller;

import com.dejay.framework.common.enums.MapKeyStringEnum;
import com.dejay.framework.common.utils.MapUtil;
import com.dejay.framework.domain.common.Paging;
import com.dejay.framework.service.MemberService;
import com.dejay.framework.service.TestService;
import com.dejay.framework.vo.ResultStatusVO;
import com.dejay.framework.vo.TestVO;
import com.dejay.framework.vo.common.PagingVO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

        var mapKeyList = Arrays.asList(MapKeyStringEnum.PAGING.getKey());
        var dataList = new ArrayList<Object>();
        dataList.add(paging);

        Map<String, Object> resultMap = mapUtil.responseObjWrapper(new ResultStatusVO(), mapKeyList, dataList);

        return ResponseEntity.ok(resultMap);
    }
}
