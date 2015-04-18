package com.polymtl.shoppingsolver.model;

/**
 * Created by Zoe on 15-04-13.
 */
public class Shop {

    private long shopId;
    private String name;
    private String address;

    public Shop() {}

    public Shop(long id) {
        shopId = id;
    }

    public void setShopId(long id) {
        shopId = id;
    }
    public long getShopId() {
        return this.shopId;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public String getAddress() {
        return this.address;
    }
}
