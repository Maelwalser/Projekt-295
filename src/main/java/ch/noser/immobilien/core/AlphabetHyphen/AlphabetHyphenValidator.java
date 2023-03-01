package ch.noser.immobilien.core.AlphabetHyphen;

import ch.noser.immobilien.core.AlphabetNumb.AlphabetNumb;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class AlphabetHyphenValidator  implements ConstraintValidator<AlphabetHyphen, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value==null){
        return false;
        }
        return value.matches("^[A-Za-z-]+$");
    }
}
