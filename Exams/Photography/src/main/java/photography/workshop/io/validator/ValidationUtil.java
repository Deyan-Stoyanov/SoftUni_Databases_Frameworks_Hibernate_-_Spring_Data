package photography.workshop.io.validator;

import org.springframework.stereotype.Component;

import javax.validation.Validation;
import javax.validation.Validator;

@Component
public final class ValidationUtil {

    private static Validator validator = Validation
            .buildDefaultValidatorFactory().getValidator();

    public ValidationUtil() {
    }

    public static <T> boolean isValid(T t) {
        int count = validator.validate(t).size();
        return t != null && validator.validate(t).size() == 0;
    }
}