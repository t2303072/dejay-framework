package com.dejay.framework.domain;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@ToString(callSuper = false)
@Getter
@EqualsAndHashCode(of = {"id", "userId"})
public class Member {

    @Max(message = "아이디는 10보다 작은 수여야 합니다.", value = 9)
    private Long id;
    @NotNull(message = "유저 아이디는 필수값 입니다.")
    private String userId;
    private String name;
    private String email;
    private List<String> list = new ArrayList<>();

    @Builder
    public Member(Long id, String userId, String name, String email, String role) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.email = email;
    }
}
