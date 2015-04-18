package com.polymtl.shoppingsolver.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.polymtl.shoppingsolver.model.Client;

import java.util.concurrent.ConcurrentLinkedDeque;

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

        Cursor cursor = database.rawQuery("SELECT * FROM " + DBHelper.TABLE_CLIENTINFO, null);

        // if there is client info, delete and save new client info
        if (cursor.moveToFirst()) {
            database.delete(dbHelper.TABLE_CLIENTINFO, null, null);
        }

        // save client info
        ContentValues values = new ContentValues();
        values.put(DBHelper.KEY_CLIENT_ID, client.getClientId());
        values.put(DBHelper.KEY_CLIENT_EMAIL, client.getEmail());
        values.put(DBHelper.KEY_CLIENT_NAME, client.getName());
        values.put(DBHelper.KEY_PASSWORD, client.getPassword());
        values.put(DBHelper.KEY_TELEPHONE, client.getTelephone());
        values.put(DBHelper.KEY_STREET, client.getStreet());
        values.put(DBHelper.KEY_CITY, client.getCity());
        values.put(DBHelper.KEY_POSTCODE, client.getPostcode());
        values.put(DBHelper.KEY_COUNTRY, client.getCountry());
        values.put(DBHelper.KEY_BALANCE, client.getBalance());
        values.put(DBHelper.KEY_DEVICEKEY, client.getBalance());
        values.put(DBHelper.KEY_DEVICEKEY, client.getDeviceKey());

        database.insert(DBHelper.TABLE_CLIENTINFO, null, values);

        cursor.close();
    }

    public Client getClient() {
        Client client = new Client();
        Cursor cursor = database.rawQuery("SELECT * FROM " + DBHelper.TABLE_CLIENTINFO, null);
        if (cursor.moveToFirst()) {

            client.setClientId(cursor.getLong(cursor.getColumnIndex(DBHelper.KEY_CLIENT_ID)));
            client.setPassword(cursor.getString(cursor.getColumnIndex(DBHelper.KEY_PASSWORD)));
            client.setName(cursor.getString(cursor.getColumnIndex(DBHelper.KEY_CLIENT_NAME)));
            client.setBalance(cursor.getDouble(cursor.getColumnIndex(DBHelper.KEY_BALANCE)));
            client.setEmail(cursor.getString(cursor.getColumnIndex(DBHelper.KEY_CLIENT_EMAIL)));
            client.setTelephone((cursor.getString(cursor.getColumnIndex(DBHelper.KEY_TELEPHONE))));
            client.setStreet(cursor.getString(cursor.getColumnIndex(DBHelper.KEY_STREET)));
            client.setCity(cursor.getString(cursor.getColumnIndex(DBHelper.KEY_CITY)));
            client.setCountry(cursor.getString(cursor.getColumnIndex(DBHelper.KEY_COUNTRY)));
            client.setPostcode(cursor.getString(cursor.getColumnIndex(DBHelper.KEY_POSTCODE)));
            client.setDeviceKey(cursor.getString(cursor.getColumnIndex(DBHelper.KEY_DEVICEKEY)));

        }

        return client;
    }
}
