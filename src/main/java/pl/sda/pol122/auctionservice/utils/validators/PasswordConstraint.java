package pl.sda.pol122.auctionservice.utils.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordConstraint {

    String message() default "Incorrect password.The password must have a minimum of 8 characters, " +
            "lowercase and uppercase and a special character";
    Class<?>[]groups() default{};
    Class<? extends Payload>[] payload() default{};

}
