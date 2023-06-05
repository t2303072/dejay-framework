package com.dejay.framework;

import com.dejay.framework.model.Hello;
import com.dejay.framework.model.Member;
import com.dejay.framework.model.Test;
import com.dejay.framework.service.MemberService;
import com.dejay.framework.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class IndexController {

    private static final String content = "%s";
    private final AtomicInteger counter = new AtomicInteger();

    private TestService testService;
    private MemberService memberService;

    @Autowired
    public IndexController(TestService testService, MemberService memberService) {
        this.memberService = memberService;
        this.testService = testService;
    }

    @GetMapping("/")
    public Hello index() {
        var hello = new Hello(counter.incrementAndGet(), String.format(content, "Dejay Framework"));
        return hello;
    }

    @GetMapping("/test")
    public List<Test> test() {
        return testService.getTestList();
    }

    @GetMapping("/member")
    public List<Member> member() {
        return memberService.getMemberList();
    }
}



