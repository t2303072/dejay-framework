package com.dejay.framework.controller.utils;

import com.dejay.framework.common.enums.TokenType;
import com.dejay.framework.common.utils.JwtUtil;
import com.dejay.framework.domain.common.LoginRequest;
import com.dejay.framework.domain.token.Token;
import com.dejay.framework.service.token.TokenService;
import com.dejay.framework.vo.common.AuthenticationResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/token")
@RestController
public class AuthenticationController {

    public AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final TokenService tokenService;

    @PostMapping("/generate-token")
    public ResponseEntity generateToken(@RequestBody @Valid LoginRequest loginRequest) {
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
//        );

//        SecurityContextHolder.getContext().setAuthentication(authentication);
        var jwtToken = jwtUtil.generateToken(new HashMap<>(), loginRequest);
        var refreshToken = jwtUtil.generateRefreshToken(new HashMap<>(), loginRequest);
        var authenticationResponse = AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
        var token = Token.builder()
                .token(jwtToken)
                .refreshToken(refreshToken)
                .tokenType(TokenType.BEARER)
                .build();

        tokenService.saveToken(token);

        return ResponseEntity.ok(authenticationResponse);
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')")
    public String userAccess() {
        return "UserContent";
    }
}
