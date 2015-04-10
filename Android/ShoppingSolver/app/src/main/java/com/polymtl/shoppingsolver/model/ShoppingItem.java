package com.polymtl.shoppingsolver.model;

/**
 * Created by Zoe on 15-04-01.
 */
public class ShoppingItem {
    private Product product;
    private float quantity; //for product sold by individual


    public ShoppingItem(Product product) {
        this.product = product;
        this.quantity++;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public float getQuantity() {
        return this.quantity;
    }


    public double getItemTotalPrice() {
        return quantity * product.getUnit_price();
    }

}
