package pl.sda.pol122.auctionservice.services;

import org.springframework.stereotype.Service;
import pl.sda.pol122.auctionservice.dao.OrderRepository;
import pl.sda.pol122.auctionservice.dao.ProductDao;
import pl.sda.pol122.auctionservice.entities.OrderEntity;
import pl.sda.pol122.auctionservice.entities.OrderItemEntity;
import pl.sda.pol122.auctionservice.model.Cart;
import pl.sda.pol122.auctionservice.model.CartItem;
import pl.sda.pol122.auctionservice.model.Product;
import pl.sda.pol122.auctionservice.model.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class DefaultCartService implements CartService {

    private final Cart cart;
    private final OrderRepository orderRepository;
    private final ProductDao productRepository;
    private final UserService userService;


    public DefaultCartService(Cart cart, OrderRepository orderRepository, ProductDao productRepository1, UserService userService) {
        this.cart = cart;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository1;
        this.userService = userService;
    }

    @Override
    public void deleteProductFromCart(CartItem cartItem) {
        cart.deleteFromCart(cartItem);
    }

    @Override
    public void addProductToCart(Product product) {
        cart.addToCart(product);
    }

    @Override
    public void addMoreProductsToCart(Product product, int numberOfProduct) {
        cart.addToCart(product, numberOfProduct);
    }

    @Override
    public List<CartItem> getAllProducts() {
        return cart.getCartItemList();
    }

    @Override
    public BigDecimal getTotalPriceOfCart() {
        return cart.sumPrice();
    }

    @Override
    public void submitPayment(List<CartItem> orderedProducts) {
        List<CartItem> purchasedProducts = cart.getCartItemList();
        User buyer = userService.getAuthenticatedUser();
        String userOrderId = UUID.randomUUID().toString();

        List<OrderItemEntity> orderedItems = purchasedProducts.stream()
                .map(cartItem -> OrderItemEntity.builder()
                        .product(productRepository.findProduct(cartItem.getProduct().getId()))
                        .quantity(cartItem.getQuantity())
                        .price(cartItem.getTotalPrice())
                        .build())
                .toList();


        OrderEntity userOrder = OrderEntity.builder()
                .listOfProducts(orderedItems)
                .buyerUserId(buyer.getId())
                .valueOfOrder(cart.sumPrice())
                .build();

        orderRepository.save(userOrder);



    }

    @Override
    public void clearCart() {
        cart.clearCart();
    }
}
