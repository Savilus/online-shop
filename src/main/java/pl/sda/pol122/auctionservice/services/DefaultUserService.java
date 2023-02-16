package pl.sda.pol122.auctionservice.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.DataBinder;
import org.springframework.validation.ObjectError;
import pl.sda.pol122.auctionservice.controllers.validators.SignUpValidator;
import pl.sda.pol122.auctionservice.config.AuthenticatedUser;
import pl.sda.pol122.auctionservice.dao.OrderRepository;
import pl.sda.pol122.auctionservice.dao.ProductRepository;
import pl.sda.pol122.auctionservice.dao.UserRepository;
import pl.sda.pol122.auctionservice.entities.OrderEntity;
import pl.sda.pol122.auctionservice.entities.ProductEntity;
import pl.sda.pol122.auctionservice.entities.UserEntity;
import pl.sda.pol122.auctionservice.model.Order;
import pl.sda.pol122.auctionservice.model.Product;
import pl.sda.pol122.auctionservice.model.User;

import java.util.*;

@Service
public class DefaultUserService implements UserService {

    private final UserRepository userRepository;
    private final SignUpValidator signUpValidator;
    private final OrderRepository orderRepository;
    private final AuthenticatedUser authenticatedUser;
    private final ProductRepository productRepository;
    private final PasswordEncoder passwordEncoder;


    public DefaultUserService(UserRepository userRepository, SignUpValidator signUpValidator, OrderRepository orderRepository, PasswordEncoder passwordEncoder, AuthenticatedUser authenticatedUser, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.signUpValidator = signUpValidator;
        this.orderRepository = orderRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticatedUser = authenticatedUser;
        this.productRepository = productRepository;
    }


    @Override
    public UserEntity getUserById(Integer id) {
        return userRepository.getUserEntityById(id);
    }

    @Override
    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public void createUserAccount(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        UserEntity userEntity = UserEntity
                .builder()
                .login(user.getUserName())
                .password(encodedPassword)
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .enabled(true)
                .build();
        userRepository.save(userEntity);
    }

    public List<ObjectError> validatePasswordAndLogin(User user) {
        DataBinder dataBinder = new DataBinder(user);
        dataBinder.addValidators(signUpValidator);
        dataBinder.validate();
        List<ObjectError> allErrors = dataBinder.getBindingResult().getAllErrors();
        return allErrors;
    }

    @Override
    public void saveAccountStatus(Integer userId, boolean accountStatus) {
        UserEntity userEntityById = userRepository.getUserEntityById(userId);
        userEntityById.setEnabled(accountStatus);
        userRepository.save(userEntityById);
    }

    public User getAuthenticatedUser() {
        Optional<UserEntity> userEntity = authenticatedUser.get();
        User user;
        if (userEntity.isPresent()) {
            UserEntity existingEntityUser = userEntity.get();
            user = User.builder()
                    .id(existingEntityUser.getId())
                    .userName(existingEntityUser.getFirstName())
                    .firstName(existingEntityUser.getFirstName())
                    .lastName(existingEntityUser.getLastName())
                    .email(existingEntityUser.getEmail())
                    .build();
        } else {
            throw new RuntimeException("This user does not exist");
        }
        return user;
    }

    @Override
    public List<Order> getUserOrderHistory() {
        List<OrderEntity> allByBuyerUserId = orderRepository.findAllByBuyerUserId(getAuthenticatedUser().getId());
        List<Order> orders = new ArrayList<>();


        for (OrderEntity oneOrder : allByBuyerUserId) {
            Map<Integer, Integer> orderedProductsWithQuantity = oneOrder.getOrderedProductsWithQuantity();
            Map<Product,Integer> productsQuantityMap = new HashMap<>();
            for (Map.Entry<Integer, Integer> entry : orderedProductsWithQuantity.entrySet()) {
                Integer productId = entry.getKey();
                Integer productQuantity = entry.getValue();
                ProductEntity productEntityById = productRepository.findProductEntityById(productId);
                Product orderedProduct = Product.builder()
                        .name(productEntityById.getName())
                        .image(productEntityById.getImage())
                        .price(productEntityById.getPrice())
                        .build();
                productsQuantityMap.put(orderedProduct, productQuantity);
            }

            orders.add(Order.builder()
                    .orderId(oneOrder.getId())
                    .productQuantityMap(productsQuantityMap)
                    .valueOfOrder(oneOrder.getValueOfOrder())
                    .build());


        }
        return orders;
    }

    @Override
    public void saveAccountChanges(User user) {
        UserEntity userEntity = UserEntity
                .builder()
                .login(user.getUserName())
                .password(user.getPassword())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .enabled(user.getEnabled())
                .build();
        userRepository.save(userEntity);
    }

}
