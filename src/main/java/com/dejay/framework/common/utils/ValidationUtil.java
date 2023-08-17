package com.dejay.framework.common.utils;

import com.dejay.framework.common.enums.ExceptionCodeMsgEnum;
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

    /**
     * 생성된 객체에 대한 검증
     * @param obj {@link Object}  검증 대상 객체
     * @param clazz {@link Class} 대상 객체 클래스
     * @return {@link Boolean}
     * @param <T>
     */
    public <T> boolean parameterValidator(T obj, Class<T> clazz) {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
        Set<ConstraintViolation<T>> validate = validator.validate((clazz.cast(obj)));

        if(validate.size() > 0) {
            log.error(validate.toString());

            for (ConstraintViolation<T> violation : validate) {
                log.error("[parameterValidator] {}", violation.getMessage());
            }
            // TODO: IJ 공통 토큰 익셉션 개발
            throw new IllegalArgumentException(ExceptionCodeMsgEnum.SERVER_DATA_ERROR.getMsg());
        }

        return true;
    }
}
