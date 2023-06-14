package com.dejay.framework.common.utils;

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

        for (ConstraintViolation<T> violation : validate) {
            log.info("[parameterValidator] Error Message: {}", violation.getMessage());
        }
        if(validate.size() > 0) {
            log.info("{}", validate.toString());
            throw new IllegalArgumentException("유효하지 않은 매개변수 값이 존재합니다.");
        }

        return true;
    }
}
