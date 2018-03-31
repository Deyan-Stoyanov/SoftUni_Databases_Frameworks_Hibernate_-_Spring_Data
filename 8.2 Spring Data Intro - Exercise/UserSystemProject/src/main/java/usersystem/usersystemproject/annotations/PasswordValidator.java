package usersystem.usersystemproject.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<Password, String> {

    private boolean matchesCapitalLetters;
    private boolean matchesLowerCaseLetters;
    private boolean matchesDigits;
    private boolean matchesSpecialSymbols;

    @Override
    public void initialize(Password password) {
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        for (Character c : s.toCharArray()) {
            if (c.toString().matches("[A-Z]")) {
                matchesCapitalLetters = true;
            } else if (c.toString().matches("[a-z]")) {
                matchesLowerCaseLetters = true;
            } else if (c.toString().matches("[1-9]")) {
                matchesDigits = true;
            } else if (c.toString().matches("[\\!\\@\\#\\$\\%\\^\\&\\*\\(\\)\\_\\+\\<\\>\\?]")) {
                matchesSpecialSymbols = true;
            }
        }
        return matchesCapitalLetters && matchesLowerCaseLetters && matchesDigits && matchesSpecialSymbols && s.length() >= 6 && s.length() <= 50;
    }
}
