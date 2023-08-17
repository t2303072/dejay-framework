package com.dejay.framework.domain.common;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor
public class TokenObjectVO {

    private String key;
    private String accessToken;
    private String refreshToken;

    @Builder
    public TokenObjectVO(String key, String accessToken, String refreshToken) {
        this.key = key;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
