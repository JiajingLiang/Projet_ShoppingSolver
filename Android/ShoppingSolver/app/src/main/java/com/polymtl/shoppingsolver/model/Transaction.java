package com.polymtl.shoppingsolver.model;

import java.util.ArrayList;

/**
 * Created by Zoe on 15-04-01.
 */
public class Transaction {

    private long id;
    private String storeName; // name of store
    private String address; // address of store
    private short cashDesk; //cash dest number
    private String time; //transaction time including date and hour
    private double totalPrice;
    private float taxTPS;
    private float taxTVQ;
    private double totalPriceWithTax;
    private ArrayList<ShoppingRecord> shoppingList;
    private short amountProducts;

    public Transaction() {}
    public Transaction(ArrayList<ShoppingRecord> shoppingList) {
        this.shoppingList = shoppingList;
    }

    public void setId(long id) {
        this.id = id;
    }
    public long getId(){
        return this.id;
    }

    public void setShoppingList(ArrayList<ShoppingRecord> list) {
        this.shoppingList = list;
    }
    public ArrayList<ShoppingRecord> getShoppingList() {
        return this.shoppingList;

    }

    public String getStrShoppingLis() {
        String result = "";
        for (ShoppingRecord record: this.shoppingList) {
            result += record.toString() + "\n";
        }
        return result;
    }

    public void setAmountProducts(short amount) {
        this.amountProducts = amount;
    }
    public short getAmountProducts() {
        return this.amountProducts;
    }

    public short calculateAmountProducts() {

        for (ShoppingRecord item : this.shoppingList ) {
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

        for (ShoppingRecord item : this.shoppingList ) {
            this.taxTPS += item.getItemTotalPrice() * item.getFederalTaxRatio();
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

        for (ShoppingRecord item : this.shoppingList ) {
            this.taxTVQ += item.getItemTotalPrice() * item.getProvincialTaxRatio();
        }
        return this.taxTVQ;
    }

    public void setStoreName(String store) {
        this.storeName = store;
    }

    public String getStoreName() {
        return this.storeName;
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

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getTotalPrice() {
        return this.totalPrice;
    }

    public void setTotalPriceWithTax(double priceWithTax) {
        this.totalPriceWithTax  = priceWithTax;
    }
    public double getTotalPriceWithTax() {
        return this.totalPriceWithTax;
    }

    public double calculateTotalPriceWithTax() {
        this.totalPriceWithTax = this.totalPrice + getTaxTPS() + getTaxTVQ();
        return this.totalPriceWithTax;
    }

    @Override
    public String toString() {
        return "TransactionID: " + id + "\nTime:" + time + "\nShopping list: \n"
                + getStrShoppingLis() + "Total:" + totalPriceWithTax + "\n";
    }
}
