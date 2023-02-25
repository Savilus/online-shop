package pl.sda.pol122.auctionservice.utils.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CCVValidator implements ConstraintValidator<CCVConstraint, String> {

    private static final String CCV_REGEX = "^[0-9]{3,4}$";
    @Override
    public void initialize(CCVConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String ccvNumber, ConstraintValidatorContext constraintValidatorContext) {
        return ccvNumber != null &&
                ccvNumber.matches(CCV_REGEX);
    }
}
