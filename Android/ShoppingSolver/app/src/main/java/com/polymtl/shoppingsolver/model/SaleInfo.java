package com.polymtl.shoppingsolver.model;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Zoe on 15-04-20.
 */
public class SaleInfo {


    private String address;
    private String description;
    private String price;
    private LatLng latLng;

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPrice() {
        return price;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public LatLng getLatLng() {
        return latLng;
    }
}
