package ch.noser.immobilien.core.AlphabetNumb;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class AlphabetNumbValidator implements ConstraintValidator<AlphabetNumb, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context){
        if(value == null){
            return false;
        }
        return value.matches("^[A-Za-z0-9]+$");
    }
}
