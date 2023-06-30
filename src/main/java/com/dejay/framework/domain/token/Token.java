package com.dejay.framework.domain.token;

import com.dejay.framework.common.enums.TokenType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class Token {

    private long id;
    private String token;
    private String refreshToken;
    private TokenType tokenType;
}
