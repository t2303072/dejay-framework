package com.dejay.framework.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@EqualsAndHashCode(of = {"id", "name"})
public class Member {
    private Long id;
    private String name;
    private String email;
    @Builder.Default
    private List<String> list = new ArrayList<>();

    @Builder
    public Member(Long id, @NonNull String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
