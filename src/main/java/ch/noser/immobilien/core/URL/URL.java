package ch.noser.immobilien.core.URL;


import ch.noser.immobilien.core.AlphabetNumb.AlphabetNumbValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
@Constraint(validatedBy = URLValidator.class)
public @interface URL {

    String message() default "The given Text doesnt match the pattern";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
