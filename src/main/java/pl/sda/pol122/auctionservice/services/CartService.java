package pl.sda.pol122.auctionservice.services;

import pl.sda.pol122.auctionservice.model.CartItem;
import pl.sda.pol122.auctionservice.model.Product;

import java.math.BigDecimal;
import java.util.List;

public interface CartService {


    void deleteProductFromCart(CartItem cartItem);

    void addProductToCart(Product product);

    void decreaseProductQuantityInCart(Product product);

    List<CartItem> getAllProducts();

    BigDecimal getTotalPriceOfCart();

    void submitPayment(List<CartItem> orderedProducts);

    void clearCart();
}
