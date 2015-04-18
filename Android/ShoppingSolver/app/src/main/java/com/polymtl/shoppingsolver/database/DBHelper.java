package com.polymtl.shoppingsolver.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


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
    public static final String KEY_QUANTITY = "quantity";


    private static final String CREATE_TABLE_CONSUMPTION_HABIT = "CREATE TABLE " + TABLE_CONSUMPTION_HABIT
            + " (" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_PRIMARY_CODE
            + " STRING," + KEY_QUANTITY + " FLOAT" + ")";


    // transactions table
   /* public static final String TABLE_TRANSACTION = "transactions";
    public static final String KEY_TRANSACTION_CODE = "transactionCode";
    public static final String KEY_SHOPE_ID = "idShop";
    public static final String KEY_STORE_NAME = "storeName";
    public static final String KEY_STORE_ADDRESS = "storeAddress";
    public static final String KEY_CASHDEST_ID = "cashDestID";
    public static final String KEY_TIME = "time";
    public static final String KEY_TOTALPRICE = "totalPrice";
    public static final String KEY_TAX_FEDERAL = "taxFederal";
    public static final String KEY_TAX_PROVINCIAL = "taxProvincial";
    public static final String KEY_TOTALPRICE_WITHTAX = "totalPriceWithTax";
    public static final String KEY_AMOUNT_PRODUCTS = "amountProducts";

    private static final String CREATE_TABLE_TRANSACTION = "CREATE TABLE " + TABLE_TRANSACTION
            + " (" + KEY_TRANSACTION_CODE + " LONG PRIMARY KEY," + KEY_STORE_NAME + " STRING,"
            + KEY_STORE_ADDRESS + " STRING," + KEY_CASHDEST_ID + " INTEGER,"
            + KEY_TIME + " STRING," + KEY_SHOPE_ID + " INTEGER," + KEY_TOTALPRICE + " DOUBLE"
            + KEY_TAX_FEDERAL + " DOUBLE," + KEY_TAX_PROVINCIAL + " DOUBLE"
            + KEY_TOTALPRICE_WITHTAX + " DOUBLE," + KEY_AMOUNT_PRODUCTS + "SHORT";

    //TODO shopping list table
    public static final String TABEL_SHOPPINGLIST = "shoppinglist";
    public static final String KEY_TRANSACTION_ID = "transactionCode";


    // shopping list table
    public static final String TABLE_SHOPPINGLIST = "shoppingList";
*/

    // Client information table
    public static final String TABLE_CLIENTINFO = "clientIfo";
    public static final String KEY_CLIENT_ID = "clientId";
    public static final String KEY_CLIENT_NAME = "name";
    public static final String KEY_CLIENT_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_TELEPHONE = "telephone";
    public static final String KEY_STREET = "street";
    public static final String KEY_CITY = "city";
    public static final String KEY_POSTCODE = "postcode";
    public static final String KEY_COUNTRY = "country";
    public static final String KEY_BALANCE = "balance";
    public static final String KEY_DEVICEKEY = "devicekey";
    private static final String CREATE_TABLE_CLIENTINFO = "CREATE TABLE " + TABLE_CLIENTINFO
            + " (" + KEY_CLIENT_ID + " LONG PRIMARY KEY,"
            + KEY_CLIENT_NAME + " STRING,"
            + KEY_CLIENT_EMAIL + " STRING,"
            + KEY_PASSWORD + " STRING,"
            + KEY_TELEPHONE + " STRING,"
            + KEY_STREET + " STRING,"
            + KEY_CITY + " STRING,"
            + KEY_POSTCODE + " STRING,"
            + KEY_COUNTRY + " STRING,"
            + KEY_BALANCE + " DOUBLE,"
            + KEY_DEVICEKEY +" STRING"+ ")";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_CONSUMPTION_HABIT);

        //db.execSQL(CREATE_TABLE_TRANSACTION);

        db.execSQL(CREATE_TABLE_CLIENTINFO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONSUMPTION_HABIT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLIENTINFO);

        //create new tables
        onCreate(db);
    }







    //DELETE FROM table where _id NOT IN (SELECT _id from table ORDER BY insertion_date DESC LIMIT 50)
}
