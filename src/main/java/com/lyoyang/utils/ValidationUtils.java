package com.lyoyang.utils;

import org.apache.commons.collections.CollectionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class ValidationUtils {

    public static <T> String validate(T obj) {
        if(obj == null)
            return null;
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<T>> validate = validator.validate(obj);
        StringBuilder errMsg = new StringBuilder();
        if(CollectionUtils.isNotEmpty(validate)) {
            for (ConstraintViolation constraintViolation : validate) {
                errMsg.append(constraintViolation.getMessage() + ",");
            }
            errMsg.deleteCharAt(errMsg.lastIndexOf(","));
        }
        return errMsg.toString();
    }

}
