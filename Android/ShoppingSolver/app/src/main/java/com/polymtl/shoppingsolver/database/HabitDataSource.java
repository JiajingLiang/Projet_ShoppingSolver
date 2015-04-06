package com.polymtl.shoppingsolver.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.polymtl.shoppingsolver.model.ShoppingRecord;

import java.util.ArrayList;
import java.util.List;

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

    public List<ShoppingRecord> getNecessaryRecords() {

        List<ShoppingRecord> result = new ArrayList<>();

        Cursor cursor = database.rawQuery("SELECT * FROM " + DBHelper.TABLE_CONSUMPTION_HABIT +
                " ORDER BY quantity DESC LIMIT 10", null);

        cursor.moveToFirst();
        while (cursor.moveToNext()) {
            ShoppingRecord shoppingRecord = new ShoppingRecord();
            shoppingRecord.setCode(cursor.getString(cursor.getColumnIndex(DBHelper.KEY_PRIMARY_CODE)));
            shoppingRecord.setName(cursor.getString(cursor.getColumnIndex(DBHelper.KEY_PRODUCT_NAME)));
            shoppingRecord.setQuantity(cursor.getFloat(cursor.getColumnIndex(DBHelper.KEY_QUANTITY)));

            result.add(shoppingRecord);
        }

        // make sure to close the cursor
        cursor.close();

        return result;
    }

    public boolean isExist(ShoppingRecord record) {

        boolean isExist = false;

        Cursor cursor = database.rawQuery("SELECT * FROM " + DBHelper.TABLE_CONSUMPTION_HABIT +
                " WHERE " + DBHelper.KEY_PRIMARY_CODE + " = " + record.getQuantity(), null);

        isExist = cursor.moveToFirst();
        cursor.close();
        return isExist;
    }
    public void updateRecord(ShoppingRecord record) {

        Cursor cursor = database.rawQuery("SELECT * FROM " + DBHelper.TABLE_CONSUMPTION_HABIT +
                " WHERE " + DBHelper.KEY_PRIMARY_CODE + " = " + record.getQuantity(), null);
        float QUANTITY = cursor.getFloat(cursor.getColumnIndex(DBHelper.KEY_QUANTITY)) + record.getQuantity();
        String sql = "UPDATE " + DBHelper.TABLE_CONSUMPTION_HABIT + " SET " + DBHelper.KEY_QUANTITY
                + " = " + QUANTITY + " WHERE " + DBHelper.KEY_PRIMARY_CODE + " = " + record.getQuantity();
        database.execSQL(sql);
        cursor.close();
    }

    public long insertRecord(ShoppingRecord record) {

        ContentValues values = new ContentValues();
        values.put(DBHelper.KEY_PRIMARY_CODE, record.getCode());
        values.put(DBHelper.KEY_PRODUCT_NAME, record.getName());
        values.put(DBHelper.KEY_QUANTITY, record.getQuantity());

        long id = database.insert(DBHelper.TABLE_CONSUMPTION_HABIT, null, values);

        return id;
    }
}
