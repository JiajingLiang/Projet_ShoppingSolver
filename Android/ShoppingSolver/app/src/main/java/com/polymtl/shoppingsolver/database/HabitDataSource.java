package com.polymtl.shoppingsolver.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.polymtl.shoppingsolver.model.HabitRecord;

import java.util.ArrayList;


/**
 * Created by Zoe on 15-04-06.
 */
public class HabitDataSource {

    // Database fields
    private SQLiteDatabase database;
    private DBHelper dbHelper;

    public HabitDataSource(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public ArrayList<HabitRecord> getNecessaryRecords(long clientId) {

        ArrayList<HabitRecord> result = new ArrayList<>();

        Cursor cursor = database.rawQuery("SELECT * FROM " + DBHelper.TABLE_CONSUMPTION_HABIT
                + " WHERE " + DBHelper.KEY_CLIENTID + " = " + clientId +
                " ORDER BY quantity DESC LIMIT 10", null);
        Log.i("SQLQuerry","SELECT * FROM " + DBHelper.TABLE_CONSUMPTION_HABIT
                + " WHERE " + DBHelper.KEY_CLIENTID + " = " + clientId +
                " ORDER BY quantity DESC LIMIT 10");


        while (cursor.moveToNext()) {
            HabitRecord record = new HabitRecord();
            record.setProductBarCode(cursor.getString(cursor.getColumnIndex(DBHelper.KEY_PRIMARY_CODE)));
            Log.i("get record", cursor.getString(cursor.getColumnIndex(DBHelper.KEY_PRIMARY_CODE)));
            record.setQuantity(cursor.getFloat(cursor.getColumnIndex(DBHelper.KEY_QUANTITY)));
            record.setDescription(cursor.getString(cursor.getColumnIndex(DBHelper.KEY_DESCRIPTION)));
            result.add(record);

            Log.i("getRecords", record.getDescription());
        }

        // make sure to close the cursor
        cursor.close();

        return result;
    }

    public ArrayList<String> getNecessaryProductsCode(long clientId) {

        ArrayList<String> result = new ArrayList<>();

        Cursor cursor = database.rawQuery("SELECT * FROM " + DBHelper.TABLE_CONSUMPTION_HABIT
                + " WHERE " + DBHelper.KEY_CLIENTID + " = " + clientId +
                " ORDER BY quantity DESC LIMIT 10", null);

        Log.i("count", "" + cursor.getCount());

        while (cursor.moveToNext()) {
            String barCode = cursor.getString(cursor.getColumnIndex(DBHelper.KEY_PRIMARY_CODE));

            Log.i("getNecessary", barCode);
            result.add(barCode);
        }

        // make sure to close the cursor
        cursor.close();

        return result;
    }

    public boolean isExist(HabitRecord record) {


        Cursor cursor = database.rawQuery("SELECT * FROM " + DBHelper.TABLE_CONSUMPTION_HABIT +
                " WHERE " + DBHelper.KEY_PRIMARY_CODE + " = " + record.getProductBarCode() + " AND "
                + DBHelper.KEY_CLIENTID + " = " + record.getClientId(), null);

        boolean isExist = cursor.moveToFirst();
        cursor.close();
        return isExist;
    }
    public void updateRecord(HabitRecord record) {

        Log.i("updat1", record.getProductBarCode());
        // A Cursor object, which is positioned before the first entry
        Cursor cursor = database.rawQuery("SELECT * FROM " + DBHelper.TABLE_CONSUMPTION_HABIT +
                " WHERE " + DBHelper.KEY_PRIMARY_CODE + " = " + record.getProductBarCode() + " AND "
                + DBHelper.KEY_CLIENTID + " = " + record.getClientId(), null);

        cursor.moveToFirst();

        float QUANTITY = cursor.getFloat(cursor.getColumnIndex(DBHelper.KEY_QUANTITY)) + record.getQuantity();

        ContentValues values = new ContentValues();

        Log.i("update", record.getProductBarCode());

        values.put(DBHelper.KEY_PRIMARY_CODE, record.getProductBarCode());
        values.put(DBHelper.KEY_QUANTITY, QUANTITY);
        values.put(DBHelper.KEY_DESCRIPTION, record.getDescription());

        database.update(DBHelper.TABLE_CONSUMPTION_HABIT, values, DBHelper.KEY_PRIMARY_CODE + " = ?",
                new String[] {record.getProductBarCode()});

        cursor.close();
    }

    public void insertRecord(HabitRecord record) {

        ContentValues values = new ContentValues();
        values.put(DBHelper.KEY_PRIMARY_CODE, record.getProductBarCode());
        values.put(DBHelper.KEY_QUANTITY, record.getQuantity());
        values.put(DBHelper.KEY_DESCRIPTION, record.getDescription());
        values.put(DBHelper.KEY_CLIENTID, record.getClientId());

        Log.i("insert barCode", record.getProductBarCode());
        database.insert(DBHelper.TABLE_CONSUMPTION_HABIT, null, values);

    }

    public void addRecord(HabitRecord record) {

        getAllRecords(); //test
       Log.i("addRecord", record.getProductBarCode());
       if (isExist(record)) {
           updateRecord(record);
       } else {
           insertRecord(record);
       }

    }

    public void getAllRecords() {

        Cursor cursor = database.rawQuery("SELECT * FROM " + DBHelper.TABLE_CONSUMPTION_HABIT , null);

        while (cursor.moveToNext()) {
            Log.i("DB product code:", cursor.getString(cursor.getColumnIndex(DBHelper.KEY_PRIMARY_CODE)));
            Log.i("DB clientId:", cursor.getString(cursor.getColumnIndex(DBHelper.KEY_CLIENTID)));
        }
    }
}
