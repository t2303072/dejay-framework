package com.dejay.framework.domain.user;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Set;

@ToString(callSuper = false)
@Getter
@EqualsAndHashCode(of = {"id", "name"})
public class User {

    private long seq;
    @NotNull(message = "아이디는 필수 값 입니다.")
    private String id;
    @NotNull(message = "비밀번호는 필수 값 입니다.")
    private String password;
    @NotNull(message = "이름은 필수 값 입니다.")
    private String name;
    private String email;
    private String picture;
    private Set<Role> roles;

    @Builder
    public User(String id, String password, String name, String email, String picture, Set<Role> roles) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.roles = roles;
    }

    public User update(String name, String picture) {
        this.name = name;
        this.picture = picture;

        return this;
    }

    public String getRoleKey() {
        return String.valueOf(this.roles);
    }
}
