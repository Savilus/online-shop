package pl.sda.pol122.auctionservice.services;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sda.pol122.auctionservice.config.AuthenticatedUser;
import pl.sda.pol122.auctionservice.dao.OrderRepository;
import pl.sda.pol122.auctionservice.dao.ProductRepository;
import pl.sda.pol122.auctionservice.dao.UserRepository;
import pl.sda.pol122.auctionservice.entities.OrderEntity;
import pl.sda.pol122.auctionservice.entities.ProductEntity;
import pl.sda.pol122.auctionservice.entities.UserAddressEntity;
import pl.sda.pol122.auctionservice.entities.UserEntity;
import pl.sda.pol122.auctionservice.model.Order;
import pl.sda.pol122.auctionservice.model.Product;
import pl.sda.pol122.auctionservice.model.User;
import pl.sda.pol122.auctionservice.model.UserAddress;

import java.text.SimpleDateFormat;
import java.util.*;


@Service
@AllArgsConstructor
public class DefaultUserService implements UserService {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final AuthenticatedUser authenticatedUser;
    private final ProductRepository productRepository;
    private final PasswordEncoder passwordEncoder;

    private final JdbcTemplate jdbcTemplate;

    @Override
    public UserEntity getUserById(Integer id) {
        return userRepository.getUserEntityById(id);
    }

    @Override
    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }


    @Transactional(rollbackFor = Exception.class)
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
        addUserAuthorities(user);
    }

    @Transactional(rollbackFor = Exception.class)
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
                .build();
        userRepository.save(userEntity);
        addAdminAuthorities(user);
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
        if (userEntity.isPresent() && userEntity.get().getAddress() != null) {
            UserEntity existingEntityUser = userEntity.get();
            user = User.builder()
                    .id(existingEntityUser.getId())
                    .enabled(existingEntityUser.getEnabled())
                    .userName(existingEntityUser.getLogin())
                    .firstName(existingEntityUser.getFirstName())
                    .lastName(existingEntityUser.getLastName())
                    .email(existingEntityUser.getEmail())
                    .userAddress(UserAddress.builder()
                            .city(existingEntityUser.getAddress().getCity())
                            .postCode(existingEntityUser.getAddress().getPostCode())
                            .buildingNumber(existingEntityUser.getAddress().getBuildingNumber())
                            .flatNumber(existingEntityUser.getAddress().getFlatNumber())
                            .street(existingEntityUser.getAddress().getStreet())
                            .build())
                    .build();
        } else if (userEntity.isPresent() && userEntity.get().getAddress() == null) {
            UserEntity existingEntityUser = userEntity.get();
            user = User.builder()
                    .id(existingEntityUser.getId())
                    .enabled(existingEntityUser.getEnabled())
                    .userName(existingEntityUser.getLogin())
                    .firstName(existingEntityUser.getFirstName())
                    .lastName(existingEntityUser.getLastName())
                    .email(existingEntityUser.getEmail())
                    .build();
        } else {
            throw new RuntimeException("This admin does not exist");
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
    public void saveAccountChanges(User user, UserAddress userAddress) {
        UserEntity userEntityById = userRepository.getUserEntityById(authenticatedUser.get().orElseThrow(RuntimeException::new).getId());

        if (user.getFirstName() != null && !user.getFirstName().isBlank()) {
            userEntityById.setFirstName(user.getFirstName().trim());
        }
        if (user.getLastName() != null && !user.getLastName().isBlank()) {
            userEntityById.setLastName(user.getLastName().trim());
        }
        if (user.getEmail() != null && !user.getEmail().isBlank()) {
            userEntityById.setEmail(user.getEmail().trim());
        }

        UserAddressEntity userAddressEntity;
        if (userEntityById.getAddress() == null) {
            userAddressEntity = new UserAddressEntity();
        } else {
            userAddressEntity = userEntityById.getAddress();
        }
        UserAddressEntity updatedUserAddress = updateUserAddressEntity(userAddressEntity, userAddress);

        userEntityById.setAddress(updatedUserAddress);
        userRepository.save(userEntityById);
    }


    private void addUserAuthorities(User user) {
        String sqlAddAuthorities = "INSERT INTO authorities (username, authority) VALUES (?,?)";

        jdbcTemplate.update(sqlAddAuthorities, user.getUserName(), "USER");
    }

    private void addAdminAuthorities(User user) {
        String sqlAddAuthorities = "INSERT INTO authorities (username, authority) VALUES (?,?)";

        jdbcTemplate.update(sqlAddAuthorities, user.getUserName(), "ADMIN");
    }

    private UserAddressEntity updateUserAddressEntity(UserAddressEntity userAddressEntity, UserAddress userAddress) {

        if (userAddress.getCity() != null && !userAddress.getCity().isBlank()) {
            userAddressEntity.setCity(userAddress.getCity());
        }

        if (userAddress.getPostCode() != null && !userAddress.getPostCode().isBlank()) {
            userAddressEntity.setPostCode(userAddress.getPostCode());
        }
        if (userAddress.getBuildingNumber() != null && !userAddress.getBuildingNumber().isBlank()) {
            userAddressEntity.setBuildingNumber(userAddress.getBuildingNumber());
        }
        if (userAddress.getFlatNumber() != null && !userAddress.getFlatNumber().isBlank()) {
            userAddressEntity.setFlatNumber(userAddress.getFlatNumber());
        }
        if (userAddress.getStreet() != null && !userAddress.getStreet().isBlank()) {
            userAddressEntity.setStreet(userAddress.getStreet());
        }

        return userAddressEntity;
    }

}
