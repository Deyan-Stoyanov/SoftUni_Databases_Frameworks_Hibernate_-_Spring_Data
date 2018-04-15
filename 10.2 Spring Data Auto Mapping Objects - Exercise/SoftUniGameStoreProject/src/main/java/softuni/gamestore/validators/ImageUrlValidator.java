package softuni.gamestore.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ImageUrlValidator implements ConstraintValidator<URL, String> {
    @Override
    public void initialize(URL constraintValidator) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return value.startsWith("http://") || value.startsWith("https://") || value == null;
    }
}
