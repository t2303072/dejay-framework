package com.dejay.framework.vo.common;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor
public class TokenVO {
    private String sub;
    private String userName;
    private String iss;
    private long iat;
    private long exp;
    private String[] roles;

    @Builder
    public TokenVO(String sub, String userName, String iss, long iat, long exp, String[] roles) {
        this.sub = sub;
        this.userName = userName;
        this.iss = iss;
        this.iat = iat;
        this.exp = exp;
        this.roles = roles;
    }
}
