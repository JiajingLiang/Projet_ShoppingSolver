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


    // TABLE_CONSUMPTION_HABIT column names
    public static final String KEY_PRIMARY_CODE = "primary_code";
    public static final String KEY_QUANTITY = "quantity";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_CLIENTID = "clientId";

    private static final String CREATE_TABLE_CONSUMPTION_HABIT = "CREATE TABLE " + TABLE_CONSUMPTION_HABIT
            + " (" + KEY_PRIMARY_CODE + " VARCHAR(20) PRIMARY KEY,"
            + KEY_QUANTITY + " FLOAT," + KEY_DESCRIPTION + " STRING," + KEY_CLIENTID + " LONG" + ")";


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

        db.execSQL(CREATE_TABLE_CLIENTINFO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONSUMPTION_HABIT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLIENTINFO);

        //create new tables
        onCreate(db);
    }

}
