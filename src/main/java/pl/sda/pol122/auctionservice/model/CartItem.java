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

    public void addAmount(){
        quantity++;
        totalPrice = totalPrice.add(product.getPrice());
    }

    public void addAmount(int numberOfProducts){
        quantity += numberOfProducts;
        BigDecimal addPrizeOfProducts = product.getPrice().multiply(BigDecimal.valueOf(numberOfProducts));
        totalPrice = addPrizeOfProducts.add(totalPrice);
    }

    public Product getProduct() {
        return product;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public int getQuantity() {
        return quantity;
    }

}