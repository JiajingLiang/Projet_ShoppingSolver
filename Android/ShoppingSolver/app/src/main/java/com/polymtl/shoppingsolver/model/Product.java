package com.polymtl.shoppingsolver.model;

import java.io.Serializable;

/**
 * Created by Zoe on 15-04-01.
 */
public class Product implements Serializable {

    private String productBarCode;
    private String productName;
    private String categoryName;
    private String description;
    private float federalTaxRatio; // Taxe sur les produits et services
    private float provincialTaxRatio; // Taxe de vente du Quebec
    private double unit_price;


    public void setProductBarCode(String code) {
        this.productBarCode = code;
    }
    public String getProductBarCode() {
        return this.productBarCode;
    }

    public void setProductName(String name) {
        this.productName = name;
    }
    public String getProductName() {
        return this.productName;
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

}
