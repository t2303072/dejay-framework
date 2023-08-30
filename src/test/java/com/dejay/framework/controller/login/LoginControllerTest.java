package com.dejay.framework.controller.login;

import com.dejay.framework.FrameworkApplicationTests;
import com.dejay.framework.common.exception.CustomLoginException;
import com.dejay.framework.domain.member.LoginRequest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class LoginControllerTest extends FrameworkApplicationTests {

    @Test
    void 로그인_성공() throws Exception {
        var target = LoginRequest.builder()
                .userName("ijzone")
                .password("12345")
                .build();
        String content = objectMapper.writeValueAsString(target);

        this.mockMvc.perform(post("/login")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("post-login-ok"));
    }

    @Test
    void 로그인_아이디정보_없음() throws Exception {
        var target = LoginRequest.builder()
                .password("12345")
                .build();
        String content = objectMapper.writeValueAsString(target);

        this.mockMvc.perform(post("/login")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> result.getResolvedException().getClass().equals(CustomLoginException.class))
                .andDo(document("post-no-login-id-fail"));
    }

    @Test
    void 로그인_비밀번호_실패() throws Exception {
        var target = LoginRequest.builder()
                .userName("ijzone")
                .password("123456")
                .build();
        String content = objectMapper.writeValueAsString(target);

        this.mockMvc.perform(post("/login")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> result.getResolvedException().getClass().equals(CustomLoginException.class))
                .andDo(document("post-login-wrong-password-fail"));
    }
}