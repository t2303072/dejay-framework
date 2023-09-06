package com.dejay.framework.jsp;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SampleVO {
    private String seq;
    private String name;
    private String email;

    public SampleVO(String seq, String name, String email) {
        this.seq = seq;
        this.name = name;
        this.email = email;
    }
}

