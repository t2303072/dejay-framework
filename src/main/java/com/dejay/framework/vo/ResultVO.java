package com.dejay.framework.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter @Setter
public class ResultVO {

    private int code;
    private String message;

    public ResultVO() {
        this.code = 200;
        this.message = "success";
    }
}
