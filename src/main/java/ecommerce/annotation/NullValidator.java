package ecommerce.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NullValidator implements
        ConstraintValidator<NotNullAnnotation, String> {

    @Override
    public void initialize(NotNullAnnotation name) {
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext cxt) {

        return s== null;
    }

}
