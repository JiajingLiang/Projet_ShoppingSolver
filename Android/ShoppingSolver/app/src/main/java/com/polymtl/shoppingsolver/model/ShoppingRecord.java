package com.polymtl.shoppingsolver.model;

import android.util.Log;

/**
 * Created by Zoe on 15-04-01.
 */
public class ShoppingRecord {

    private float quantity; //for product sold by individual
    private String productBarCode;
    private String categoryName;
    private String description;
    private float federalTaxRatio; // Taxe sur les produits et services
    private float provincialTaxRatio; // Taxe de vente du Quebec
    private double unit_price;

    public ShoppingRecord() {}
    public ShoppingRecord(String barCode) {
        this.productBarCode = barCode;
        this.quantity++;
    }

    public void setProductBarCode(String code) {
        this.productBarCode = code;
        Log.i("set BarCode", code);
    }
    public String getProductBarCode() {
        Log.i("get BarCode", this.productBarCode);
        return this.productBarCode;
    }

    public void setCategoryName(String name) {
        this.categoryName = name;
    }
    public String getCategoryName() {
        return this.categoryName;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription() {
        return this.description;
    }

    public void setFederalTaxRatio(float taxRatio) {
        this.federalTaxRatio = taxRatio;
    }
    public float getFederalTaxRatio() {

        return this.federalTaxRatio;
    }

    public void setProvincialTaxRatio(float taxRatio) {
        this.provincialTaxRatio = taxRatio;
    }

    public float getProvincialTaxRatio() {
        return this.provincialTaxRatio;
    }

    public void setUnit_price(double price) {
        this.unit_price = price;
    }

    public double getUnit_price() {
        return this.unit_price;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public float getQuantity() {
        return this.quantity;
    }


    public double getItemTotalPrice() {
        return quantity * unit_price;
    }

    @Override
    public String toString() {
        return description + " Unit_price: " + unit_price + "$" + " Quantity" + quantity + "\n";
    }
}
