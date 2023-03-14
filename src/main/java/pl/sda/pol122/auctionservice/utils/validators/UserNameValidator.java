package pl.sda.pol122.auctionservice.utils.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import pl.sda.pol122.auctionservice.dao.UserRepository;

public class UserNameValidator implements ConstraintValidator<UserNameConstraint, String> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void initialize(UserNameConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String userName, ConstraintValidatorContext constraintValidatorContext) {
        return userRepository.checkIfUsernameIsAvailable(userName) == 0;
    }
}
