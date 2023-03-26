package pl.sda.pol122.auctionservice.utils.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PostCodeValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PostCodeConstraint {
    String message() default "Invalid post code. Example (just digits): XX-XXX";
    Class<?>[]groups() default{};
    Class<? extends Payload>[] payload() default{};
}
