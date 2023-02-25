package pl.sda.pol122.auctionservice.utils.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EmailValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CCVConstraint {
    String message() default "The given CCV should has 3 or 4 digits.";
    Class<?>[]groups() default{};
    Class<? extends Payload>[] payload() default{};
}
