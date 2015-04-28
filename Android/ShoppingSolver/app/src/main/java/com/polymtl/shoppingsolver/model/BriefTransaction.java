package com.polymtl.shoppingsolver.model;

/**
 * Created by Zoe on 15-04-19.
 */
public class BriefTransaction {

    private String time;
    private long transactionId;
    private double total;
    private String street;
    private String city;
    private String postcode;
    private String country;
    private String brandName;

    public void setTransactionId(long id) {
        this.transactionId = id;
    }
    public long getTransactionId() {
        return this.transactionId;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    public double getTotal() {
        return this.total;
    }

    public void setStreet(String street) {
        this.street = street;
    }
    public String getStreet() {
        return this.street;
    }

    public void setCity(String city) {
        this.city = city;
    }
    public String getCity() {
        return this.city;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
    public String getPostcode() {
        return this.postcode;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    public String getCountry() {
        return this.country;
    }

    public void setBrandName(String name) {
        this.brandName = name;
    }
    public String getBrandName() {
        return this.brandName;
    }

    public void setTime(String time) {
        this.time = time;
    }
    public String getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        return "Transaction Id: " + this.transactionId
                + "\nTotal: " + this.total + "\nTime: " + this.time
                + "\nShop name: " + this.brandName
                + "\nAddress: " + this.street + " " + this.city + " "
                + this.postcode + " " + this.country + "\n";
    }
}
