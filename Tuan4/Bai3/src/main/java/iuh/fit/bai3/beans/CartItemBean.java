package iuh.fit.bai3.beans;


import java.io.Serializable;

public class CartItemBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Product product;
    private int quantity;

    public CartItemBean() {
    }

    public CartItemBean(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSubtotal() {
        return product.getPrice() * quantity;
    }
}