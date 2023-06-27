package com.dejay.framework.vo.common;

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
public class ResultStatusVO {

    private int code;
    private String message;
    private String specificMsg;
    private List<FieldError> fieldErrors;

    public ResultStatusVO() {
        this.code = ResultCodeMsgEnum.SUCCESS.getCode();
        this.message = ResultCodeMsgEnum.SUCCESS.getMsg();
    }

    public ResultStatusVO(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
