package com.dejay.framework.controller.utils;

import com.dejay.framework.FrameworkApplicationTests;
import com.dejay.framework.domain.member.LoginRequest;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.FieldDescriptor;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class TokenControllerTest extends FrameworkApplicationTests {

    // Succeeded Response Body Fields
    FieldDescriptor[] successResponseFields = new FieldDescriptor[] {
            fieldWithPath("code").description("로그인 ID"),
            fieldWithPath("message").description("비밀번호"),
            fieldWithPath("specificMsg").description("상세 메시지"),
            fieldWithPath("fieldErrors").description("필수 필드 에러")
    };

    @Test
    void 토큰_재발행_성공() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.add("reissue", "Y");
        headers.add("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJpanpvbmUiLCJ1c2VyTmFtZSI6Imlqem9uZSIsImF1dGhvcml0eSI6WyIwMDAyMDAwMSJdLCJpYXQiOjE2OTM1NTAxMTIsImV4cCI6MTY5NDc1OTcxMiwiaXNzIjoiaWtqb29MZWUifQ.ILe8dp0Tb8fzlndM2snmCmBV3HTgncE1wM76WQXQNaE");

        this.mockMvc.perform(post("/token/reissue-token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .headers(headers))
                .andExpect(status().isOk())
                .andDo(document("post-token-reissue-ok"
                        , responseFields(successResponseFields))
                );
    }

    @Test
    void 토큰_재발행_실패_잘못된_reissue_헤더값() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.add("reissue", "N");
        headers.add("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJpanpvbmUiLCJ1c2VyTmFtZSI6Imlqem9uZSIsImF1dGhvcml0eSI6WyIwMDAyMDAwMSJdLCJpYXQiOjE2OTMzODI0MTEsImV4cCI6MTY5NDU5MjAxMSwiaXNzIjoiaWtqb29MZWUifQ.Sd-D8yt6luPMJmCctOxghvRsJb79sm-HOviNZ5jVf4s");

        this.mockMvc.perform(post("/token/reissue-token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .headers(headers))
                .andExpect(status().isForbidden())
                .andDo(document("post-token-reissue-header-fail"));
    }

    @Test
    void 토큰_재발행_실패_잘못된_bearer_token_헤더값() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.add("reissue", "Y");
        headers.add("Authorization", "Bearer eyJbGciOiJIUzI1NiJ9.eyJzdWIiOiJpanpvbmUiLCJ1c2VyTmFtZSI6Imlqem9uZSIsImF1dGhvcml0eSI6WyIwMDAyMDAwMSJdLCJpYXQiOjE2OTMzNzk4ODcsImV4cCI6MTY5NDU4OTQ4NywiaXNzIjoiaWtqb29MZWUifQ.CkVx_fg57Fmjpy7JKxdmKwYFUcO0h0f04c9cEBBSrmE");

        this.mockMvc.perform(post("/token/reissue-token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .headers(headers))
                .andExpect(status().isForbidden())
                .andDo(document("post-token-bearer-header-fail"));
    }
}