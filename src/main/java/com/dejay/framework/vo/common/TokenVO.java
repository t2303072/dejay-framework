package com.dejay.framework.vo.common;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor
public class TokenVO {
    private String userName;
    private String iss;
    private long iat;
    private long exp;

    @Builder
    public TokenVO(String userName, String iss, long iat, long exp) {
        this.userName = userName;
        this.iss = iss;
        this.iat = iat;
        this.exp = exp;
    }
}
