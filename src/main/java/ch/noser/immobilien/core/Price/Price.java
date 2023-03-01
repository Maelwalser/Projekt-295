package ch.noser.immobilien.core.Price;


import jakarta.validation.Constraint;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
@Constraint(validatedBy = PriceValidator.class)
public @interface Price {
}
