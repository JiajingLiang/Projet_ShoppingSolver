package com.polymtl.shoppingsolver.model;

import java.util.ArrayList;
import java.util.List;

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

    public void setShoppingList(ArrayList<ShoppingItem> list) {
        this.shoppingList = list;
    }
    public ArrayList<ShoppingItem> getShoppingList() {
        return this.shoppingList;

    }

    public void setAmountProducts(short amount) {
        this.amountProducts = amount;
    }
    public short getAmountProducts() {
        return this.amountProducts;
    }
    public short calculateAmountProducts() {

        for (ShoppingItem item : this.shoppingList ) {
            this.amountProducts += item.getQuantity();
        }
        return this.amountProducts ;
    }

    public void setTaxTPS(float tax) {
        this.taxTPS = tax;
    }
    public float getTaxTPS() {
        return this.taxTPS;
    }
    public float calculateTaxTPS() {

        for (ShoppingItem item : this.shoppingList ) {
            this.taxTPS += item.getItemTotalPrice() * item.getProduct().getCategory().getRatioTPS();
        }
        return this.taxTPS;
    }

    public void setTaxTVQ(float tax) {
        this.taxTVQ = tax;
    }
    public float getTaxTVQ() {
        return this.taxTVQ;
    }
    public float calculateTVQ() {

        for (ShoppingItem item : this.shoppingList ) {
            this.taxTVQ += item.getItemTotalPrice() * item.getProduct().getCategory().getRatioTVQ();
        }
        return this.taxTVQ;
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

    public void setTotalPriceWithTax(float priceWithTax) {
        this.totalPriceWithTax  = priceWithTax;
    }
    public float getTotalPriceWithTax() {
        return this.totalPriceWithTax;
    }

    public float calculateTotalPriceWithTax() {
        this.totalPriceWithTax = this.totalPrice + getTaxTPS() + getTaxTVQ();
        return this.totalPriceWithTax;
    }
}
