package softuni.gamestore.validators;

import org.springframework.stereotype.Component;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Component
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ImageUrlValidator.class)
public @interface URL {
    String message() default "Wrong URL";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
