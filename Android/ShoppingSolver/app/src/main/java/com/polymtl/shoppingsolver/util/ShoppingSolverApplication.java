package com.polymtl.shoppingsolver.util;

import android.app.Application;

import com.polymtl.shoppingsolver.model.Client;
import com.polymtl.shoppingsolver.model.Shop;
import com.polymtl.shoppingsolver.model.ShoppingRecord;
import com.polymtl.shoppingsolver.ui.CustomerBaseAdapter;

import java.util.ArrayList;

/**
 * Created by Zoe on 15-04-13.
 * This application singleton used to share data between different activities or fragments
 */
public class ShoppingSolverApplication extends Application {

    private ArrayList<ShoppingRecord> shoppingRecords;
    private ShoppingRecord currentRecord;
    private Shop shop;
    private Client newCount;
    private String regId; // the same with deviceKey in Client
    private CustomerBaseAdapter adapter;
    private String dateBegin;
    private String dateEnd;
    private static ShoppingSolverApplication instance;

    //Make sure there is only on instance of this class
    public static ShoppingSolverApplication getInstance() {
        if(instance == null) {
            instance = new ShoppingSolverApplication();
        }

        return instance;
    }

    public ShoppingSolverApplication() {
        shoppingRecords = new ArrayList<>();
        currentRecord = new ShoppingRecord();
        shop = new Shop();
        adapter = new CustomerBaseAdapter();
        newCount = new Client();
    }

    public void setAdapter(CustomerBaseAdapter adapter) {
        this.adapter = adapter;
    }
    public CustomerBaseAdapter getAdapter() {
        return this.adapter;
    }

    public void setShoppingRecords(ArrayList<ShoppingRecord> records) {
        shoppingRecords = records;
    }

    public ArrayList<ShoppingRecord> getShoppingRecords() {
        return this.shoppingRecords;
    }

    public void addCurrentShoppingRecord() {
        shoppingRecords.add(currentRecord);
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }
    public Shop getShop() {
        return this.shop;
    }

    public int getRecordsCount() {
        return shoppingRecords.size();
    }
    public void removeRecord(int index) {
        shoppingRecords.remove(index);
    }

    public void setCurrentRecord(ShoppingRecord record) {
        this.currentRecord = record;
    }
    public ShoppingRecord getCurrentRecord() {
        return this.currentRecord;
    }

    public void setNewCount(Client count) {
        this.newCount = count;
    }
    public Client getNewCount() {
        return this.newCount;
    }

    public void setRegId(String regId) {
        this.regId = regId;
    }
    public String getRegId() {
        return this.regId;
    }

    public void setDateBegin(String date) {
        this.dateBegin = date;
    }
    public String getDateBegin() {
        return this.dateBegin;
    }

    public void setDateEnd(String date) {
        this.dateEnd = date;
    }
    public String getDateEnd() {
        return this.dateEnd;
    }
}
