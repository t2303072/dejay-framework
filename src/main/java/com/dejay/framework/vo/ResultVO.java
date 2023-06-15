package com.dejay.framework.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.validation.FieldError;

import java.util.List;

@ToString
@AllArgsConstructor
@Getter @Setter
public class ResultVO {

    private int code;
    private String message;
    private List<FieldError> fieldErrors;

    public ResultVO() {
        this.code = 200;
        this.message = "success";
    }
}
