package com.dejay.framework.vo;

import com.dejay.framework.common.enums.ResultCodeMsgEnum;
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
        this.code = ResultCodeMsgEnum.SUCCESS.getCode();
        this.message = ResultCodeMsgEnum.SUCCESS.getMsg();
    }

    public ResultVO(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
