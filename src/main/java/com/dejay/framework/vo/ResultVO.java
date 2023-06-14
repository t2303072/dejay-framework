package com.dejay.framework.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ResultVO {

    private int code;
    private String message;

    public ResultVO() {
        this.code = 0;
        this.message = "success";
    }
}
