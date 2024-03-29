package pl.sda.pol122.auctionservice.model;

import java.math.BigDecimal;

public class CartItem {


    private Product product;
    private int quantity;
    private BigDecimal totalPrice;

    public CartItem(Product product,  int amount) {
        this.product = product;
        this.quantity = amount;
        this.totalPrice = calculateTotalPrice();
    }

    private BigDecimal calculateTotalPrice(){
        return product.getPrice().multiply(BigDecimal.valueOf(quantity));
    }

    public void addQuantity(){
        if(product.getAvailableAmount() > quantity){
            quantity++;
            totalPrice = totalPrice.add(product.getPrice());
        }
    }

    public void decreaseQuantity(){
        if(quantity > 1){
            quantity--;
            totalPrice = totalPrice.add(product.getPrice());
        }
    }


    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

}