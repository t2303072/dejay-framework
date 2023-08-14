package com.dejay.framework.common.exception;

import com.dejay.framework.common.enums.ExceptionCodeMsgEnum;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.security.GeneralSecurityException;

@Getter
@Component
public class CustomLoginException extends GeneralSecurityException {

    private int code;
    private String msg;

    public CustomLoginException() {
        super();
    }

    public CustomLoginException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
