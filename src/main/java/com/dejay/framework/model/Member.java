package com.dejay.framework.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@EqualsAndHashCode(of = {"id", "name"})
public class Member {
    private Long id;
    @NonNull
    private String name;
    private String email;
    @Builder.Default
    private List<String> list = new ArrayList<>();
}
