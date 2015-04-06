package com.polymtl.shoppingsolver.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.polymtl.shoppingsolver.model.ShoppingItem;

import java.util.ArrayList;


/**
 * Created by Zoe on 15-04-05.
 */
public class DBHelper extends SQLiteOpenHelper {

    //Database name
    private final static String DATABASE_NAME = "shoppingSolver.db";

    //Database version
    private final static int VERSION = 1;

    // consumption habit table
    public static final String TABLE_CONSUMPTION_HABIT = "consumptionHabit";

    public static final String KEY_ID = "_id"; // comment column
    // TABLE_CONSUMPTION_HABIT column names
    public static final String KEY_PRIMARY_CODE = "_primary_code";
    public static final String KEY_PRODUCT_NAME = "product_name";
    public static final String KEY_QUANTITY = "quantity";


    private static final String CREATE_TABLE_CONSUMPTION_HABIT = "CREATE TABLE " + TABLE_CONSUMPTION_HABIT
            + " (" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_PRIMARY_CODE
            + " STRING PRIMARY KEY," + KEY_PRODUCT_NAME + " STRING,"
            + KEY_QUANTITY + " FLOAT" + ")";


    // transactions table
    public static final String TABLE_TRANSACTION = "transactions";
    public static final String KEY_STORE_NAME = "storeName";
    public static final String KEY_STORE_ADDRESS = "storeAddress";
    public static final String KEY_CASHDEST_ID = "cashDestID";
    public static final String KEY_TIME = "time";
    public static final String KEY_TRANSACTION_CODE = "transactionCode";
    public static final String KEY_TOTALPRICE = "totalPrice";
    public static final String KEY_TAX_TPS = "taxTPS";
    public static final String KEY_TAX_TVQ = "taxTVQ";
    public static final String KEY_TOTALPRICE_WITHTAX = "totalPriceWithTax";
    public static final String KEY_AMOUNT_PRODUCTS = "amountProducts";


    // shopping list table
    public static final String TABLE_SHOPPINGLIST = "shoppingList";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_CONSUMPTION_HABIT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONSUMPTION_HABIT);

        //create new tables
        onCreate(db);
    }







    //DELETE FROM table where _id NOT IN (SELECT _id from table ORDER BY insertion_date DESC LIMIT 50)
}
