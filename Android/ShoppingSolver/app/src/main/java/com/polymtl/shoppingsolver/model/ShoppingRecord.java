package com.polymtl.shoppingsolver.model;

/**
 * Created by Zoe on 15-04-05.
 */
public class ShoppingRecord {

    private String code;
    private String name;
    private float quantity;

    public ShoppingRecord() {}
    public ShoppingRecord(String code, String name, float quantity) {

        this.code = code;
        this.name = name;
        this.quantity = quantity;
    }

    public void setCode(String code) {
        this.code = code;
    }
    public String getCode() {
        return this.code;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public float getQuantity() {
        return this.quantity;
    }

    public String toString() {
        return this.name + "   account: " + this.quantity;
    }
}
