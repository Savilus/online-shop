package pl.sda.pol122.auctionservice.utils.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CardNumberValidator implements ConstraintValidator<CardNumberConstraint, String> {

    private static final String MASTERCARD_NUMBER_REGEX =
            "^(5[1-5][0-9]{14}|2(22[1-9][0-9]{12}|2[3-9][0-9]{13}|[3-6][0-9]{14}|7[0-1][0-9]{13}|720[0-9]{12}))$";

    @Override
    public void initialize(CardNumberConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String cardNumber, ConstraintValidatorContext constraintValidatorContext) {
        return cardNumber != null &&
                cardNumber.matches(MASTERCARD_NUMBER_REGEX);
    }
}
