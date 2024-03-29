package com.dejay.framework.domain.member;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Getter
@EqualsAndHashCode(of = {"userName", "password"})
@NoArgsConstructor
public class LoginRequest {

    @NotNull(message = "userName은 필수 값 입니다.")
    private String userName;
    @NotNull(message = "password는 필수 값 입니다.")
    private String password;
//    @NotNull(message = "Authority는 필수 값 입니다.")
    private Set<?> authority;

    @Builder
    public LoginRequest(String userName, String password, Set<?> authority) {
        this.userName = userName;
        this.password = password;
        this.authority = authority;
    }
}
