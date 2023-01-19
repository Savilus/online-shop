package pl.sda.pol122.auctionservice.services;

import org.springframework.stereotype.Service;
import pl.sda.pol122.auctionservice.model.Cart;
import pl.sda.pol122.auctionservice.model.ProductDto;

import java.util.List;
@Service
public class DefaultCartService implements CartService{

    private final Cart cart;

    public DefaultCartService(Cart cart) {
        this.cart = cart;
    }

    @Override
    public boolean deleteProduct(String id) {
        return false;
    }

    @Override
    public ProductDto addMoreProductsToCart(String id) {
        return null;
    }

    @Override
    public List<ProductDto> getAllProducts() {
        return null;
    }
}
