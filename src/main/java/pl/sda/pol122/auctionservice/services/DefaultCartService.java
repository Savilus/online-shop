package pl.sda.pol122.auctionservice.services;

import org.springframework.stereotype.Service;
import pl.sda.pol122.auctionservice.model.Cart;
import pl.sda.pol122.auctionservice.model.CartItem;
import pl.sda.pol122.auctionservice.model.Product;

import java.math.BigDecimal;
import java.util.List;

@Service
public class DefaultCartService implements CartService{

    private final Cart cart;

    public DefaultCartService(Cart cart) {
        this.cart = cart;
    }

    @Override
    public boolean deleteProductFromCart(CartItem cartItem) {
        return cart.deleteFromCart(cartItem);
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
}
