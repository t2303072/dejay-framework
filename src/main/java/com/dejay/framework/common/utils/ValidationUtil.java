package com.dejay.framework.common.utils;

import com.dejay.framework.common.enums.ExceptionMsgEnum;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Set;

@Slf4j
@Component
public class ValidationUtil {
    private ValidatorFactory validatorFactory;
    private Validator validator;

    public ValidationUtil(ValidatorFactory validatorFactory, Validator validator) {
        this.validatorFactory = validatorFactory;
        this.validator = validator;
    }

    public <T> boolean parameterValidator(T obj, Class<T> clazz) {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
        Set<ConstraintViolation<T>> validate = validator.validate((clazz.cast(obj)));

        if(validate.size() > 0) {
            log.error(validate.toString());

            for (ConstraintViolation<T> violation : validate) {
                log.error("[parameterValidator] {}", violation.getMessage());
            }
            throw new IllegalArgumentException(ExceptionMsgEnum.ERR_INVALID_PARAM_EXISTS.getMsg());
        }

        return true;
    }
}
