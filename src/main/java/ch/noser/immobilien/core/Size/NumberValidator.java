package ch.noser.immobilien.core.Size;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class NumberValidator implements ConstraintValidator<Number, Integer>{
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context){
        if(value==null){
        return false;
        } else if (value>0) {
            return true;
        }
        else return false;
    }
}
