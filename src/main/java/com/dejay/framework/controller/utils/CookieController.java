package com.dejay.framework.controller.utils;

import com.dejay.framework.common.utils.CookieFactory;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/cookie")
public class CookieController {

    private final CookieFactory cookieFactory;

    @GetMapping("/set-cookie")
    public ResponseEntity setCookie(HttpServletRequest request, HttpServletResponse response) {
        cookieFactory.setCookie(response, "res-cookie1", "cookie-test1", true, false, null, 0, null);
        cookieFactory.setCookie(response, "res-cookie2", "cookie-test2", true, false, null, 0, null);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get-cookie")
    public ResponseEntity getCookie(HttpServletRequest request, HttpServletResponse response) {
        cookieFactory.getCookieList(request);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/remove/{key}")
    public ResponseEntity removeCookie(HttpServletRequest request, HttpServletResponse response, @PathVariable String key/*, @CookieValue(value = "res-cookie2", required = false) String cookie*/) {
//        String key = Arrays.stream(request.getCookies()).filter(c -> c.getName().equals("res-cookie2")).findAny().get().getName();
        cookieFactory.removeCookie(request, response, key);

        return ResponseEntity.ok().build();
    }
}
