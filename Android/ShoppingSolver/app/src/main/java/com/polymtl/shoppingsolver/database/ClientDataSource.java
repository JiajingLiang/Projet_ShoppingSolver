package com.polymtl.shoppingsolver.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.polymtl.shoppingsolver.model.Client;

/**
 * Created by Zoe on 15-04-08.
 */
public class ClientDataSource {

    // Database fields
    private SQLiteDatabase database;
    private DBHelper dbHelper;

    public ClientDataSource(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void saveClientInfo(Client client) {

        Cursor cursor = database.rawQuery("SELECT * FROM " + DBHelper.TABLE_CONSUMPTION_HABIT, null);

        // if there is client info, delete and save new client info
        if (cursor.moveToFirst()) {
            database.delete(dbHelper.TABLE_CLIENTINFO, null, null);
        }

        // save client info
        ContentValues values = new ContentValues();
        values.put(DBHelper.KEY_CLIENT_ID, client.getClientId());
        values.put(DBHelper.KEY_CLIENT_EMAIL, client.getEmail());
        values.put(DBHelper.KEY_PASSWORD, client.getPassword());
        values.put(DBHelper.KEY_TELEPHONE, client.getTelephone());
        values.put(DBHelper.KEY_STREET, client.getStreet());
        values.put(DBHelper.KEY_CITY, client.getCity());
        values.put(DBHelper.KEY_POSTCODE, client.getPostcode());
        values.put(DBHelper.KEY_COUNTRY, client.getCountry());
        values.put(DBHelper.KEY_BALANCE, client.getBalance());

        database.insert(DBHelper.TABLE_CLIENTINFO, null, values);

        cursor.close();
    }
}
