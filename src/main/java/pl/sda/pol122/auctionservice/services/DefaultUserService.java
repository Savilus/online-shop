package pl.sda.pol122.auctionservice.services;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.sda.pol122.auctionservice.config.AuthenticatedUser;
import pl.sda.pol122.auctionservice.dao.OrderRepository;
import pl.sda.pol122.auctionservice.dao.ProductRepository;
import pl.sda.pol122.auctionservice.dao.UserRepository;
import pl.sda.pol122.auctionservice.entities.OrderEntity;
import pl.sda.pol122.auctionservice.entities.ProductEntity;
import pl.sda.pol122.auctionservice.entities.UserEntity;
import pl.sda.pol122.auctionservice.enums.ERole;
import pl.sda.pol122.auctionservice.model.Order;
import pl.sda.pol122.auctionservice.model.Product;
import pl.sda.pol122.auctionservice.model.User;

import java.text.SimpleDateFormat;
import java.util.*;


@Service
public class DefaultUserService implements UserService {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final AuthenticatedUser authenticatedUser;
    private final ProductRepository productRepository;
    private final PasswordEncoder passwordEncoder;

    private final JdbcTemplate jdbcTemplate;

    public DefaultUserService(UserRepository userRepository, OrderRepository orderRepository, PasswordEncoder passwordEncoder, AuthenticatedUser authenticatedUser, ProductRepository productRepository, JdbcTemplate jdbcTemplate) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticatedUser = authenticatedUser;
        this.productRepository = productRepository;
        this.jdbcTemplate = jdbcTemplate;
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
                .roles(Set.of(ERole.USER))
                .build();
        userRepository.save(userEntity);
        addUserAuthorities(user);
    }
    @Override
    public void createAdminAccount(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        UserEntity userEntity = UserEntity
                .builder()
                .login(user.getUserName())
                .password(encodedPassword)
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .enabled(true)
                .roles(Set.of(ERole.ADMIN, ERole.USER))
                .build();
        userRepository.save(userEntity);
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
                    .userName(existingEntityUser.getLogin())
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
            Map<Product, Integer> productsQuantityMap = new HashMap<>();
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
                    .orderTimeStamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(oneOrder.getOrderTimeStamp()))
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


    private void addUserAuthorities(User user) {
        String sqlAddAuthorities = "INSERT INTO authorities (username, authority) VALUES (?,?)";

        jdbcTemplate.update(sqlAddAuthorities, user.getUserName(), "User");
    }

}
