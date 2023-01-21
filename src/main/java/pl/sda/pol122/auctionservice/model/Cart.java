package pl.sda.pol122.auctionservice.model;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Cart {

    private List<CartItem> cartItemList;

    public Cart() {
        cartItemList = new ArrayList<>();
    }

    public void addToCart(Product product){
        Optional<CartItem> cartItem =
                cartItemList.stream().filter(item -> item.getProduct().getId() == product.getId()).findAny();
        cartItem.ifPresentOrElse(CartItem::addAmount, () -> {
            cartItemList.add(new CartItem(product, 1));
        });
    }

    public void addToCart(Product product, Integer numberOfProducts){
        Optional<CartItem> cartItem =
                cartItemList.stream().filter(item -> item.getProduct().getId() == product.getId()).findAny();
        cartItem.ifPresentOrElse(item -> item.addAmount(numberOfProducts), () -> {
            cartItemList.add(new CartItem(product, numberOfProducts));
        });
    }

    public boolean deleteFromCart(CartItem cartItem){
       return cartItemList.remove(cartItem);

    }

    public List<CartItem> getCartItemList() {
        return cartItemList;
    }


}
