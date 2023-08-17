package com.dejay.framework.domain.token;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@EqualsAndHashCode(of = "id")
public class Token {

    @NotNull(message = "memberId는 필수 값 입니다.")
    private String memberId;
    @NotNull(message = "액세스 토큰은 필수 값 입니다.")
    private String accessToken;
    @NotNull(message = "리프레시 토큰은 필수 값 입니다.")
    private String refreshToken;
    private String tokenType;

    @Builder
    public Token(String memberId, String accessToken, String refreshToken) {
        this.memberId = memberId;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.tokenType = "01";
    }
}
