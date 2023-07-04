package com.dejay.framework.domain.member;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(of = {"userName", "password"})
public class LoginRequest {

    @NotNull(message = "userName은 필수 값 입니다.")
    private String userName;
    private String password;

    @Builder
    public LoginRequest(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}
