package com.masdefect.parser;


import javax.validation.Validation;
import javax.validation.Validator;

public final class ValidationUtil {
    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public ValidationUtil() {
    }

    public static <T> boolean isValid(T t){
        int count = validator.validate(t).size();
        return count == 0;
    }
}
