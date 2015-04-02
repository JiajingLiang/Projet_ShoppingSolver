package com.polymtl.shoppingsolver.model;

import java.util.ArrayList;

/**
 * Created by Zoe on 15-04-01.
 */
public class Transaction {

    private String store; // name of store
    private String address; // address of store
    private short cashDesk; //cash dest number
    private String time; //transaction time including date and hour
    private long transactionCode; // transaction code
    private float totalPrice;
    private float taxTPS;
    private float taxTVQ;
    private float totalPriceWithTax;
    private ArrayList<ShoppingItem> shoppingList;
    private short amountProducts;

    public Transaction(ArrayList<ShoppingItem> shoppingList) {
        this.shoppingList = shoppingList;
    }

    public ArrayList<ShoppingItem> getShoppingList() {
        return this.shoppingList;

    }

    public short getAmountProducts() {
        short result = 0;
        for (ShoppingItem item : this.shoppingList ) {
            result += item.getQuantity();
        }
        return result;
    }

    public float getTaxTPS() {
        float result = 0.0f;
        for (ShoppingItem item : this.shoppingList ) {
            result += item.getItemPrice() * item.getProduct().getCategory().getRatioTPS();
        }
        return result;
    }

    public float getTaxTVQ() {
        float result = 0.0f;
        for (ShoppingItem item : this.shoppingList ) {
            result += item.getItemPrice() * item.getProduct().getCategory().getRatioTVQ();
        }
        return result;
    }

    public float getTotalPriceWithTax() {
        return this.totalPrice + getTaxTPS() + getTaxTVQ();
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getStore() {
        return this.store;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return this.address;
    }

    public void setCashDesk(short cashDesk) {
        this.cashDesk = cashDesk;
    }

    public short getCashDesk() {
        return this.cashDesk;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return this.time;
    }

    public void setTransactionCode(long num) {
        this.transactionCode = num;
    }

    public long getTransactionCode() {
        return this.transactionCode;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public float getTotalPrice() {
        return this.totalPrice;
    }

}
