package com.dejay.framework.controller.utils;

import com.dejay.framework.FrameworkApplicationTests;
import com.dejay.framework.domain.member.LoginRequest;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class TokenControllerTest extends FrameworkApplicationTests {

    @Test
    void 토큰_재발행_성공() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.add("reissue", "Y");
        headers.add("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJpanpvbmUiLCJ1c2VyTmFtZSI6Imlqem9uZSIsImF1dGhvcml0eSI6WyIwMDAyMDAwMSJdLCJpYXQiOjE2OTMzODMwNDcsImV4cCI6MTY5NDU5MjY0NywiaXNzIjoiaWtqb29MZWUifQ.MB7hg86cYr2KRq9PaDazqUOtIq0kNAVw6SazB-4IbqI");

        this.mockMvc.perform(post("/token/reissue-token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .headers(headers))
                .andExpect(status().isOk())
                .andDo(document("post-token-reissue-ok"));
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