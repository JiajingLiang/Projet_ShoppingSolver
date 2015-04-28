package com.polymtl.shoppingsolver.util;

import android.app.Activity;
import android.app.Application;

import com.polymtl.shoppingsolver.model.BriefTransaction;
import com.polymtl.shoppingsolver.model.Client;
import com.polymtl.shoppingsolver.model.SaleInfo;
import com.polymtl.shoppingsolver.model.Shop;
import com.polymtl.shoppingsolver.model.ShoppingRecord;
import com.polymtl.shoppingsolver.model.Transaction;
import com.polymtl.shoppingsolver.ui.CustomerBaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zoe on 15-04-13.
 * This application singleton used to share data between different activities or fragments
 */
public class ShoppingSolverApplication extends Application {

    private static ShoppingSolverApplication instance;

    private ArrayList<ShoppingRecord> shoppingRecords;
    private ShoppingRecord currentRecord;
    private Shop shop;
    private Client newCount, currentClient;
    private String regId; // the same with deviceKey in Client
    private CustomerBaseAdapter adapter;
    private Transaction theLastTransaction;
    private ArrayList<BriefTransaction> briefTransactions;
    private SaleInfo saleInfo;
    private float startPower;
    private List<Activity> activityList = new ArrayList<>();

    //Make sure there is only on instance of this class
    public static ShoppingSolverApplication getInstance() {
        if(instance == null) {
            instance = new ShoppingSolverApplication();
        }


        return instance;
    }

    public void addActivity(Activity activity) {
        activityList.add(activity);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        //Turn all the activities let exit application perfectly
        for(Activity activity : activityList) {
            activity.finish();
        }

        System.exit(0);
    }

    public ShoppingSolverApplication() {
        shoppingRecords = new ArrayList<>();
        currentRecord = new ShoppingRecord();
        shop = new Shop();
        adapter = new CustomerBaseAdapter();
        newCount = new Client();
        briefTransactions = new ArrayList<>();
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

    public void clearShoppingRecords() {
        this.shoppingRecords.clear();
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


    public void setTheLastTransaction(Transaction transaction) {
        this.theLastTransaction = transaction;
    }
    public Transaction getTheLastTransaction() {
        return this.theLastTransaction;
    }

    public void setBriefTransactions(ArrayList<BriefTransaction> list) {
        this.briefTransactions = list;
    }
    public ArrayList<BriefTransaction> getBriefTransactions() {
        return this.briefTransactions;
    }
    public void addBriefTransaction(BriefTransaction briefTransaction) {
        this.briefTransactions.add(briefTransaction);
    }
    public void clearBriefTransaction() {
        this.briefTransactions.clear();
    }

    public void setSaleInfo(SaleInfo saleInfo) {
        this.saleInfo = saleInfo;
    }

    public SaleInfo getSaleInfo() {
        return saleInfo;
    }

    public void setStartPower(float startPower) {
        this.startPower = startPower;
    }

    public float getStartPower() {
        return startPower;
    }

    public void setCurrentClient(Client currentClient) {
        this.currentClient = currentClient;
    }

    public Client getCurrentClient() {
        return currentClient;
    }
}
