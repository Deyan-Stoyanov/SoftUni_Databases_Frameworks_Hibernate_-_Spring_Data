package usersystem.usersystemproject.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<Email, String> {

    @Override
    public void initialize(Email email) {
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s.matches("[A-Za-z1-9]+[\\.\\-\\_]?([a-zA-Z1-9])?([\\.\\-\\_]([a-zA-Z1-9])+)?\\@[a-zA-Z]+\\.[A-Za-z]+(\\.[A-Za-z]+)?");
    }
}
