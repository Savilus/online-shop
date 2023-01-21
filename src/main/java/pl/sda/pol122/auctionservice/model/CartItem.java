package pl.sda.pol122.auctionservice.model;

import java.math.BigDecimal;

public class CartItem {


    private Product product;
    private int amount;
    private BigDecimal totalPrice;

    public CartItem(Product product,  int amount) {
        this.product = product;
        this.amount = amount;
        this.totalPrice = getTotalPrice().multiply(BigDecimal.valueOf(amount));
    }

    public void addAmount(){
        amount++;
        totalPrice = totalPrice.add(product.getPrice());
    }

    public void addAmount(int numberOfProducts){
        amount+= numberOfProducts;
        BigDecimal addPrizeOfProducts = product.getPrice().multiply(BigDecimal.valueOf(numberOfProducts));
        totalPrice = addPrizeOfProducts.add(totalPrice);
    }

    public Product getProduct() {
        return product;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public int getAmount() {
        return amount;
    }

}
