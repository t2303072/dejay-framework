package com.dejay.framework.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@ToString(callSuper = false)
@Getter
@EqualsAndHashCode(of = {"id", "userId"})
public class Member {

    @Min(message = "아이디는 0보다 큰 수여야 합니다.", value = 0)
    private Long id;
    @NotNull(message = "유저 아이디는 필수값 입니다.")
    private String userId;
    private String name;
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private String email;
    private List<String> list = new ArrayList<>();

    @Builder
    public Member(Long id, String userId, String name, String email) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.email = email;
    }
}
