package com.dejay.framework.controller.login;

import com.dejay.framework.FrameworkApplicationTests;
import com.dejay.framework.common.enums.MapKeyStringEnum;
import com.dejay.framework.common.exception.CustomLoginException;
import com.dejay.framework.domain.common.TokenObjectVO;
import com.dejay.framework.domain.member.LoginRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.FieldDescriptor;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@Slf4j
class LoginControllerTest extends FrameworkApplicationTests {

    // Request Body Fields
    FieldDescriptor[] requestFields = new FieldDescriptor[] {
            fieldWithPath("userName").description("로그인 ID"),
            fieldWithPath("password").description("비밀번호"),
            fieldWithPath("authority").description("권한").optional()
    };

    // Succeeded Response Body Fields
    FieldDescriptor[] successResponseFields = new FieldDescriptor[] {
            fieldWithPath("resultStatus").description("응답 상태 객체"),
            fieldWithPath("resultStatus.code").description("응답 코드"),
            fieldWithPath("resultStatus.message").description("응답 메시지"),
            fieldWithPath("resultStatus.specificMsg").description("상세 메시지"),
            fieldWithPath("resultStatus.fieldErrors").description("필드 에러"),
            fieldWithPath("data").description("응답 데이터"),
            fieldWithPath("data.tokenObject").description("토큰"),
            fieldWithPath("data.tokenObject.tableName").description("테이블"),
            fieldWithPath("data.tokenObject.logId1").description("PK1"),
            fieldWithPath("data.tokenObject.logId2").description("PK2"),
            fieldWithPath("data.tokenObject.logType").description("처리 유형"),
            fieldWithPath("data.tokenObject.logJson").description("처리 데이터"),
            fieldWithPath("data.tokenObject.remark").description("비고"),
            fieldWithPath("data.tokenObject.regId").description("등록 ID"),
            fieldWithPath("data.tokenObject.key").description("발급 대상"),
            fieldWithPath("data.tokenObject.accessToken").description("액세스 토큰"),
            fieldWithPath("data.tokenObject.refreshToken").description("리프레시 토큰")
    };

    // Failed Response Body Fields with FieldErrors
    FieldDescriptor[] failResponseFieldsWithFieldErrors = new FieldDescriptor[] {
            fieldWithPath("code").description("응답 코드"),
            fieldWithPath("message").description("응답 메시지"),
            fieldWithPath("specificMsg").description("상세 메시지"),
            fieldWithPath("fieldErrors[]").description("필드 에러"),
            fieldWithPath("fieldErrors[].codes[]").description("필수 필드 에러 코드"),
            fieldWithPath("fieldErrors[].arguments[].codes[]").description("필수 매개변수 필드 에러 코드"),
            fieldWithPath("fieldErrors[].arguments[].arguments").description("매개변수"),
            fieldWithPath("fieldErrors[].arguments[].defaultMessage").description("기본 에러 메시지"),
            fieldWithPath("fieldErrors[].arguments[].code").description("매개변수 에러 코드"),
            fieldWithPath("fieldErrors[].defaultMessage").description("기본 에러 메시지"),
            fieldWithPath("fieldErrors[].objectName").description("매개변수 전달 객체 명"),
            fieldWithPath("fieldErrors[].field").description("에러 필드"),
            fieldWithPath("fieldErrors[].rejectedValue").description("반려 필드 값"),
            fieldWithPath("fieldErrors[].bindingFailure").description("바인딩 실패 여부"),
            fieldWithPath("fieldErrors[].code").description("에러 코드")
    };


    // Failed Response Body Fields
    FieldDescriptor[] failResponseFields = new FieldDescriptor[] {
            fieldWithPath("code").description("응답 코드"),
            fieldWithPath("message").description("응답 메시지"),
            fieldWithPath("specificMsg").description("상세 메시지"),
            fieldWithPath("fieldErrors").description("필드 에러")
    };

//    @Rollback(false) // No rollback
    @Test
    void 로그인_성공() throws Exception {
        var target = LoginRequest.builder()
                .userName("ijzone")
                .password("12345")
                .build();
        String content = convertObjectToJsonString(target);

        this.mockMvc.perform(post("/login")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
                )
                .andExpect(status().isOk())
                .andExpect(res -> {
                    TokenObjectVO tokenObjectVO = convertMapToObject(convertJsonToMap(res.getResponse().getContentAsString().toString()), MapKeyStringEnum.TOKEN_OBJECT.getKeyString(), TokenObjectVO.class);
                    assertThat(tokenObjectVO.getKey()).isEqualTo("ijzone");
                })
                .andDo(document("post-login-ok"
                        , requestFields(requestFields)
                        , responseFields(successResponseFields))
                );
    }

    @Test
    void 로그인_아이디정보_없음() throws Exception {
        var target = LoginRequest.builder()
                .password("12345")
                .build();
        String content = convertObjectToJsonString(target);

        this.mockMvc.perform(post("/login")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
                )
                .andExpect(status().isBadRequest())
                .andExpect(res -> res.getResolvedException().getClass().equals(CustomLoginException.class))
                .andDo(document("post-no-login-id-fail"
                        , requestFields(requestFields)
                        , responseFields(failResponseFieldsWithFieldErrors))
                )
                .andDo(res -> {
                    log.info("응답값 => {}", res.getResponse().getContentAsString());
                });
    }

    @Test
    void 로그인_비밀번호_실패() throws Exception {
        var target = LoginRequest.builder()
                .userName("ijzone")
                .password("123456")
                .build();
        String content = objectMapper.writeValueAsString(target);

        this.mockMvc.perform(post("/login")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
                )
                .andExpect(status().isUnauthorized())
                .andExpect(res -> res.getResolvedException().getClass().equals(CustomLoginException.class))
                .andDo(document("post-login-wrong-password-fail"
                        , requestFields(requestFields)
                        , responseFields(failResponseFields))
                );
    }

}