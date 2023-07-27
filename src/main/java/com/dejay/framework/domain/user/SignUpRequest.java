package com.dejay.framework.domain.user;

import com.dejay.framework.common.enums.Authority;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;


@ToString(callSuper = false)
@Getter
@EqualsAndHashCode(of = {"id", "name"})
public class SignUpRequest {

    private long seq;
    @NotNull(message = "아이디는 필수 값 입니다.")
    private String id;
    @NotNull(message = "비밀번호는 필수 값 입니다.")
    private String password;
    @NotNull(message = "이름은 필수 값 입니다.")
    private String name;
    private String email;
    private String picture;
    @NotNull(message = "권한은 필수 값 입니다.")
    private Set<Authority> authority;

    @Builder
    public SignUpRequest(long seq, String id, String password, String name, String email, String picture, Set<Authority> authority) {
        this.seq = seq;
        this.id = id;
        this.password = password;
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.authority = authority;
    }
}
