package pl.sda.pol122.auctionservice.utils.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UserNameValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserNameConstraint {
    String message() default "This admin name is exist. Choose another one.";
    Class<?>[]groups() default{};
    Class<? extends Payload>[] payload() default{};
}
