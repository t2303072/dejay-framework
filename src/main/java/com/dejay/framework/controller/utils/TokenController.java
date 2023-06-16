package com.dejay.framework.controller.utils;

import com.dejay.framework.common.utils.TokenFactory;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/token")
@RestController
public class TokenController {

    private final TokenFactory tokenFactory;

    @GetMapping("/generate-token")
    public ResponseEntity generateToken(HttpServletRequest request) {
        String jwtToken = tokenFactory.generateJwtToken();

        return ResponseEntity.ok(jwtToken);
    }
}
