package pl.sda.pol122.auctionservice.controllers.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import pl.sda.pol122.auctionservice.dao.UserRepository;
import pl.sda.pol122.auctionservice.model.User;
import pl.sda.pol122.auctionservice.services.UserService;

import java.util.regex.Pattern;

@Component
public class SignUpValidator implements Validator {

    private UserRepository userRepository;


    public SignUpValidator( UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private static final String EXPECTED_PASSWORD_REGEX =
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";


    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "field.required");
        User user = (User) target;
        Pattern patternMatcher = Pattern.compile(EXPECTED_PASSWORD_REGEX);
        boolean isPasswordCorrect = patternMatcher.matcher(user.getPassword()).matches();

        boolean isUserNameExist = userRepository.checkIfUsernameIsAvailable(user.getUserName()) == 1;

        if (!isPasswordCorrect) {
            errors.rejectValue(
                    "password",
                    "password.error",
                    "The password should contain the lowercase letter, the uppercase letter," +
                            " the number and the special character.");
        }
        if (isUserNameExist) {
            errors.rejectValue(
                    "userName",
                    "userName.error",
                    "The user name is exist.");
        }
    }
}
