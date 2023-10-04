package com.dejay.framework.domain.member;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(of = {"userId", "password"})
@NoArgsConstructor
public class LoginRequest {

    @NotNull(message = "userId은 필수 값 입니다.")
    private String userId;
    @NotNull(message = "password는 필수 값 입니다.")
    private String password;
//    @NotNull(message = "Authority는 필수 값 입니다.") 임시로 권한 String으로 변환
    private String authority;

    @Builder
    public LoginRequest(String userId, String password, String authority) {
        this.userId = userId;
        this.password = password;
        this.authority = authority;
    }
}
