package pl.sda.pol122.auctionservice.validators;

import jakarta.validation.ConstraintViolation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.SpringConstraintValidatorFactory;
import org.springframework.web.context.WebApplicationContext;
import pl.sda.pol122.auctionservice.dao.UserRepository;
import pl.sda.pol122.auctionservice.model.User;

import java.util.List;
import java.util.Set;


@SpringBootTest
public class UserValidationInTest {

    @MockBean
    private UserRepository userRepository;

    private LocalValidatorFactoryBean validator;
    @Autowired
    private WebApplicationContext webApplicationContext;
    private User userDto;

    @BeforeEach
    void setUp() {
        SpringConstraintValidatorFactory springConstraintValidatorFactory
                = new SpringConstraintValidatorFactory(webApplicationContext.getAutowireCapableBeanFactory());

        validator = new LocalValidatorFactoryBean();
        validator.setConstraintValidatorFactory(springConstraintValidatorFactory);
        validator.setApplicationContext(webApplicationContext);
        validator.afterPropertiesSet();

        Mockito.when(userRepository.checkIfUsernameIsAvailable(ArgumentMatchers.anyString())).thenReturn(0);

        userDto = new User();
        userDto.setUserName("superUserTest");
        userDto.setEmail("superusertest@gmail.com");
        userDto.setPassword("Password123!");
    }

    @Test
    public void shouldReturnNoViolationWhenUserIsCorrect() {
        Set<ConstraintViolation<User>> violations = validator.validate(userDto);

        Assertions.assertTrue(violations.isEmpty());
        Mockito.verify(userRepository, Mockito.times(1))
                .checkIfUsernameIsAvailable(ArgumentMatchers.anyString());
    }

    @Test
    public void shouldReturnViolationWhenEmailIsIncorrect() {
        userDto.setEmail("superuser.pl");
        final String EXPECTED_MESSAGE_FOR_INCORRECT_EMAIL =
                "Invalid e-mail address; example: martin@yahoo.com";
        Set<ConstraintViolation<User>> violations = validator.validate(userDto);

        Assertions.assertFalse(violations.isEmpty());
        Assertions.assertEquals(1, violations.size());
        Assertions.assertEquals(EXPECTED_MESSAGE_FOR_INCORRECT_EMAIL,
                violations.stream()
                .findFirst()
                        .get()
                        .getMessageTemplate());
        Mockito.verify(userRepository, Mockito.times(1))
                .checkIfUsernameIsAvailable(ArgumentMatchers.anyString());
    }
    @Test
    public void shouldReturnViolationWhenPasswordIsIncorrect() {
        userDto.setPassword("password");
        final String EXPECTED_MESSAGE_FOR_INCORRECT_PASSWORD =
                "Incorrect password." +
                "The password must have a minimum of 8 characters, " +
                "lowercase and uppercase and a special character";
        Set<ConstraintViolation<User>> violations = validator.validate(userDto);

        Assertions.assertFalse(violations.isEmpty());
        Assertions.assertEquals(1, violations.size());
        Assertions.assertEquals(EXPECTED_MESSAGE_FOR_INCORRECT_PASSWORD,
                violations.stream()
                .findFirst()
                        .get()
                        .getMessageTemplate());
        Mockito.verify(userRepository, Mockito.times(1))
                .checkIfUsernameIsAvailable(ArgumentMatchers.anyString());
    }

    @Test
    public void shouldReturnViolationsWhenParametersAreIncorrect(){
        userDto.setEmail("superuser.pl");
        userDto.setPassword("password");
        final String EXPECTED_MESSAGE_FOR_INCORRECT_EMAIL =
                "Invalid e-mail address; example: martin@yahoo.com";
        final String EXPECTED_MESSAGE_FOR_INCORRECT_PASSWORD =
                "Incorrect password." +
                        "The password must have a minimum of 8 characters, " +
                        "lowercase and uppercase and a special character";
        Set<ConstraintViolation<User>> violations = validator.validate(userDto);

        Assertions.assertFalse(violations.isEmpty());
        Assertions.assertEquals(2, violations.size());
        Assertions.assertEquals(List.of(EXPECTED_MESSAGE_FOR_INCORRECT_PASSWORD, EXPECTED_MESSAGE_FOR_INCORRECT_EMAIL),
                violations.stream()
                        .map(s -> s.getMessageTemplate())
                        .toList());
    }
}