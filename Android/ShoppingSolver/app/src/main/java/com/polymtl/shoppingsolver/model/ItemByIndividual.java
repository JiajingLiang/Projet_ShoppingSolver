package com.polymtl.shoppingsolver.model;

/**
 * Created by Zoe on 15-04-01.
 */
public class ItemByIndividual extends ShoppingItem {

    public ItemByIndividual(Product product) {
        super(product);

    }

    public void increaseQuantity() {
        setQuantity((short)(getQuantity() + 1));
    }

    public float getItemPrice() {
        return getQuantity() * getProduct().getUnit_price();
    }

}
