package pl.sda.pol122.auctionservice.services;

import org.springframework.stereotype.Service;
import pl.sda.pol122.auctionservice.dao.OrderRepository;
import pl.sda.pol122.auctionservice.entities.OrderEntity;
import pl.sda.pol122.auctionservice.model.Cart;
import pl.sda.pol122.auctionservice.model.CartItem;
import pl.sda.pol122.auctionservice.model.Product;
import pl.sda.pol122.auctionservice.model.User;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DefaultCartService implements CartService {

    private final Cart cart;
    private final OrderRepository orderRepository;
    private final UserService userService;


    public DefaultCartService(Cart cart, OrderRepository orderRepository, UserService userService) {
        this.cart = cart;
        this.orderRepository = orderRepository;
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
    public void decreaseProductQuantityInCart(Product product) {
        cart.decreaseTheAmount(product);
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
        User buyer = userService.getAuthenticatedUser();
        Map<Integer, Integer> orderedProductsWithQuantity = new HashMap<>();

        for(CartItem cartItem : orderedProducts){
            orderedProductsWithQuantity.put(cartItem.getProduct().getId(), cartItem.getQuantity());
        }

        OrderEntity userOrder = OrderEntity.builder()
                .orderedProductsWithQuantity(orderedProductsWithQuantity)
                .buyerUserId(buyer.getId())
                .valueOfOrder(cart.sumPrice())
                .orderTimeStamp(new Timestamp(new Date().getTime()))
                .build();

        orderRepository.save(userOrder);
    }

    @Override
    public void clearCart() {
        cart.clearCart();
    }
}
