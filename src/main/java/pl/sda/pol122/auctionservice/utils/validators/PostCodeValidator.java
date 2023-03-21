package pl.sda.pol122.auctionservice.utils.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PostCodeValidator implements ConstraintValidator<PostCodeConstraint, String> {

    private static final String ZIP_CODE_REGEX = "\\d{2}-\\d{3}";

    @Override
    public void initialize(PostCodeConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String postCode, ConstraintValidatorContext constraintValidatorContext) {
        return postCode != null &&
                postCode.matches(ZIP_CODE_REGEX);
    }
}
