package com.dejay.framework.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
public class MemberVO {
    private Long id;
    private String name;
    private String email;
    @Setter
    private List<String> list = new ArrayList<>();
}