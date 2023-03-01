package ch.noser.immobilien.core.AlphabetNumb;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
@Constraint(validatedBy = AlphabetNumbValidator.class)
public @interface AlphabetNumb {
    String message() default "The given Text doesnt match the pattern";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
