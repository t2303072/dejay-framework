package com.dejay.framework;

import com.dejay.framework.domain.Hello;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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



