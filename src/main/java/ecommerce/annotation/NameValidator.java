package ecommerce.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NameValidator implements
        ConstraintValidator<InvalidNameAnnotation, String> {

    @Override
    public void initialize(InvalidNameAnnotation name) {
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext cxt) {

        System.out.println("tertfgdfgdfgdfg");
        return name != null && name.matches("^[a-zA-Z]*$");
    }

}
