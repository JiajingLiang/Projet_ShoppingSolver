package com.polymtl.shoppingsolver.model;

/**
 * Created by Zoe on 15-04-01.
 */
public class ItemByWeight extends ShoppingItem {

    private float weight; // for product sold by weight

    public ItemByWeight(Product product, float weight) {
        super(product);
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getWeight() {
        return this.weight;
    }

    public float getItemPrice() {
        return this.weight * getProduct().getUnit_price();
    }
}
