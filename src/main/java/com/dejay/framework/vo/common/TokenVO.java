package com.dejay.framework.vo.common;

import com.dejay.framework.common.enums.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Set;

@ToString
@Getter
@NoArgsConstructor
public class TokenVO {
    private String sub;
    private String userName;
    private String iss;
    private long iat;
    private long exp;
    private Set<Role> roles;

    @Builder
    public TokenVO(String sub, String userName, String iss, long iat, long exp, Set<Role> roles) {
        this.sub = sub;
        this.userName = userName;
        this.iss = iss;
        this.iat = iat;
        this.exp = exp;
        this.roles = roles;
    }
}
