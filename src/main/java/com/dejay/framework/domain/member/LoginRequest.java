package com.dejay.framework.domain.member;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@Getter
@EqualsAndHashCode(of = {"userName", "password"})
public class LoginRequest {

    @NotNull(message = "userName은 필수 값 입니다.")
    private String userName;
    private String password;
    @NotNull(message = "Role은 필수 값 입니다.")
    private String[] roles;

    @Builder
    public LoginRequest(String userName, String password, String[] roles) {
        this.userName = userName;
        this.password = password;
        this.roles = roles;
    }
}
