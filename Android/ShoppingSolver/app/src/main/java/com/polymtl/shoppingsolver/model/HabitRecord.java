package com.polymtl.shoppingsolver.model;

/**
 * Created by Zoe on 15-04-20.
 */
public class HabitRecord {

    private String productBarCode;
    private String description;
    private float quantity;
    private long clientId;

    public void setProductBarCode(String code) {
        this.productBarCode = code;
    }
    public String getProductBarCode() {
        return this.productBarCode;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }
    public float getQuantity() {
        return this.quantity;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription() {
        return this.description;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public long getClientId() {
        return clientId;
    }

    @Override
    public String toString() {
        return this.description + ", " + "\nBarcode: " + this.productBarCode
                + "\nQuantity: " + this.quantity;
    }
}
