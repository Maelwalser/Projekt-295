package ch.noser.immobilien.core.Price;

import ch.noser.immobilien.core.AlphabetNumb.AlphabetNumb;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PriceValidator implements ConstraintValidator<Price, Integer> {


    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {

        if(value>=500 && value <=4500){
            return true;
        }
        else {
            return false;
        }
    }
}
