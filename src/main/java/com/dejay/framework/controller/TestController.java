package com.dejay.framework.controller;

import com.dejay.framework.service.MemberService;
import com.dejay.framework.service.TestService;
import com.dejay.framework.vo.TestVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/test")
public class TestController {

    private final TestService testService;

    @GetMapping({"", "/"})
    public ResponseEntity index() {
        List<TestVO> list = testService.getTest();
        return ResponseEntity.ok(list);
    }
}
