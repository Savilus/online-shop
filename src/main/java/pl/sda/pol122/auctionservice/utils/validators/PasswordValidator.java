package pl.sda.pol122.auctionservice.utils.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.sda.pol122.auctionservice.utils.AuthenticatedUserProvider;

public class PasswordValidator implements ConstraintValidator<PasswordConstraint, String> {

    private static final String PASSWORD_REGEX =
            "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$";

    @Override
    public void initialize(PasswordConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();

        return checkConstraintLoggedUserPassword(password, context) &
                checkConstraintCorrectPassword(password, context);


    }

    private boolean checkConstraintLoggedUserPassword(String password, ConstraintValidatorContext context) {
        boolean valid = true;
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        if(AuthenticatedUserProvider.getLoggedUser() != null){
            if (!encoder.matches(password,AuthenticatedUserProvider.getLoggedUser().getPassword() )) {
                valid = false;
                context.buildConstraintViolationWithTemplate("Your password is incorrect. Enter correct password if you want to " +
                        "save changes.").addConstraintViolation();
            }
        }
        return valid;
    }

    private boolean checkConstraintCorrectPassword(String password, ConstraintValidatorContext context) {

        boolean valid = true;
        if (!(password != null && password.matches(PASSWORD_REGEX))) {
            valid = false;

            context.buildConstraintViolationWithTemplate("Incorrect password.The password must have a minimum of 8 characters, " +
                    "lowercase and uppercase and a special character").addConstraintViolation();
        }

        return valid;
    }
}


