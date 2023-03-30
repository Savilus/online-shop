package pl.sda.pol122.auctionservice.model;

import lombok.Getter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Getter
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Cart {

    private List<CartItem> cartItemList;

    private BigDecimal price;

    public Cart() {
        cartItemList = new ArrayList<>();
        price = sumPrice();
    }

    public BigDecimal sumPrice() {
        List<CartItem> itemsInCart = cartItemList;
        BigDecimal finalCartValue = BigDecimal.ZERO;

        for (int i = 0; i < itemsInCart.size(); i++) {
            int quantity = itemsInCart.get(i).getQuantity();
            BigDecimal productPrices = itemsInCart.get(i).getProduct().getPrice().multiply(BigDecimal.valueOf(quantity));
            finalCartValue = finalCartValue.add(productPrices);
        }
        return finalCartValue;
    }

    public void addToCart(Product product) {
        Optional<CartItem> cartItem =
                cartItemList.stream().filter(item -> item.getProduct().getId() == product.getId()).findAny();
        cartItem.ifPresentOrElse(CartItem::addQuantity, () -> {
            cartItemList.add(new CartItem(product, 1));
        });
    }

    public void deleteFromCart(CartItem cartItem) {
        int productId = cartItem.getProduct().getId();
        CartItem itemToDelete = cartItemList.stream().filter(product -> product.getProduct().getId() == productId).findAny().orElseThrow();
        cartItemList.remove(itemToDelete);
    }

    public void increaseTheAmount(Product product) {
        Optional<CartItem> cartItems = cartItemList.stream().filter(item -> item.getProduct().getId() == product.getId()).findAny();
        cartItems.ifPresent(CartItem::addQuantity);
    }

    public void decreaseTheAmount(Product product) {
        Optional<CartItem> cartItem =
                cartItemList.stream().filter(item -> item.getProduct().getId() == product.getId()).findAny();
        cartItem.ifPresent(CartItem::decreaseQuantity);

    }

    public void clearCart(){
        cartItemList = new ArrayList<>();
    }


}
