package beans.model;

import business.entities.Products;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class ShoppingCartItem implements Serializable {

    private Products product;
    private int quantity;

    public ShoppingCartItem() {
    }

    public ShoppingCartItem(Products product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Products getProduct() {
        return product;
    }

    public void setProduct(Products product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal absoluteTotal() {
        return null;
    }

    public BigDecimal getTotalPrice() {
        return product.getProductPrice().multiply(BigDecimal.valueOf(quantity)).setScale(2, RoundingMode.HALF_UP);
    }
}
