package com.polymtl.shoppingsolver.model;

/**
 * Created by Zoe on 15-04-01.
 */
public class Product {

    private String name;
    private Category category;
    private float unit_price;

    public void setName(String name) {
        this.name = name;

    }
    public String getName() {
        return this.name;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
    public Category getCategory() {
        return this.category;
    }

    public void setUnit_price(float price) {
        this.unit_price = price;
    }

    public float getUnit_price() {
        return this.unit_price;
    }

}
