package pl.sda.pol122.auctionservice.services;

import pl.sda.pol122.auctionservice.model.CartItem;
import pl.sda.pol122.auctionservice.model.Product;

import java.math.BigDecimal;
import java.util.List;

public interface CartService {


    boolean deleteProductFromCart(CartItem cartItem);

    void addProductToCart(Product product);

    void addMoreProductsToCart(Product product, int numberOfProduct);

    List<CartItem> getAllProducts();

    BigDecimal getTotalPriceOfCart();
}
