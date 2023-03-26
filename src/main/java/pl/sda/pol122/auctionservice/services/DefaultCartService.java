package pl.sda.pol122.auctionservice.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.pol122.auctionservice.dao.OrderRepository;
import pl.sda.pol122.auctionservice.dao.ProductRepository;
import pl.sda.pol122.auctionservice.entities.OrderEntity;
import pl.sda.pol122.auctionservice.entities.ProductEntity;
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
@AllArgsConstructor
public class DefaultCartService implements CartService {

    private final Cart cart;
    private final OrderRepository orderRepository;
    
    private final ProductRepository productRepository;
    private final UserService userService;


    @Override
    public void deleteProductFromCart(CartItem cartItem) {
        cart.deleteFromCart(cartItem);
    }

    @Override
    public void addProductToCart(Product product) {
        cart.addToCart(product);
    }

    @Override
    public void increaseProductQuantityInCart(Product product) {
        cart.increaseTheAmount(product);
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

    @Transactional(rollbackOn = Exception.class)
    @Override
    public void submitPayment(List<CartItem> orderedProducts) {
        User buyer = userService.getAuthenticatedUser();
        Map<Integer, Integer> orderedProductsWithQuantity = new HashMap<>();

        for(CartItem cartItem : orderedProducts){
            ProductEntity productEntityById = productRepository.findProductEntityById(cartItem.getProduct().getId());
            orderedProductsWithQuantity.put(cartItem.getProduct().getId(), cartItem.getQuantity());
            int quantityLeft = productEntityById.getAvailableAmount() - cartItem.getQuantity();
            productRepository.updateAvailableAmountOfProducts(quantityLeft, productEntityById.getId());
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
    public boolean checkIfStockIsAvailable() {
        List<CartItem> cartItemList = cart.getCartItemList();
        boolean availableStock = true;

        for(int item = 0; item < cartItemList.size(); item++){
            ProductEntity productEntityById = productRepository.findProductEntityById(cartItemList.get(item).getProduct().getId());
            if(productEntityById.getAvailableAmount() < cartItemList.get(item).getQuantity()){
                availableStock = false;
                break;
            }
        }
            return availableStock;
    }

    @Override
    public void clearCart() {
        cart.clearCart();
    }
}
