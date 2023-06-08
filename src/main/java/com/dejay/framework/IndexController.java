package com.dejay.framework;

import com.dejay.framework.model.Hello;
import com.dejay.framework.model.Member;
import com.dejay.framework.service.MemberService;
import com.dejay.framework.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class IndexController {

    private static final String content = "%s";
    private final AtomicInteger counter = new AtomicInteger();

    @GetMapping("/")
    public ResponseEntity index() {
        var hello = new Hello(counter.incrementAndGet(), String.format(content, "Dejay Framework"));
        HttpHeaders headers = new HttpHeaders();
        headers.add("headertest", "headervalue");

        return ResponseEntity.ok().headers(headers).body(hello);
    }

}



