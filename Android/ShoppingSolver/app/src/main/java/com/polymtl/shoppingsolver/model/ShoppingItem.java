package com.polymtl.shoppingsolver.model;

/**
 * Created by Zoe on 15-04-01.
 */
public abstract class ShoppingItem {
    private Product product;
    private short quantity; //for product sold by individual


    public ShoppingItem(Product product) {
        this.product = product;
        this.quantity++;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setQuantity(short quantity) {
        this.quantity = quantity;
    }

    public short getQuantity() {
        return this.quantity;
    }

    public abstract float getItemPrice();

}
