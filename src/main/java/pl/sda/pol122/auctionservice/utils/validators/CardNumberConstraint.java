package pl.sda.pol122.auctionservice.utils.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CardNumberValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CardNumberConstraint {
    String message() default "Invalid card number. It should looks like: XXXX-XXXX-XXXX-XXXX";
    Class<?>[]groups() default{};
    Class<? extends Payload>[] payload() default{};
}
