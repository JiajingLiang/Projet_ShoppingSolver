package com.polymtl.shoppingsolver.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Zoe on 15-04-21.
 */
public class ConnectionChangeReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mobileInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (mobileInfo == null) {
            Log.i("mobileInfo", "is null");
        }
        NetworkInfo wifiInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiInfo == null) {
            Log.i("wifiInfo", "is null");
        }
        NetworkInfo activeInfo = manager.getActiveNetworkInfo();
        if (activeInfo == null) {
            Log.i("activeInfo", "is null");
            Toast.makeText(context, "mobile:"+mobileInfo.isConnected()+"\n"+"wifi:"+wifiInfo.isConnected()
                    ,Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, "mobile:" + mobileInfo.isConnected() + "\n" + "wifi:" + wifiInfo.isConnected()
                    + "\n" + "active:" + activeInfo.getTypeName(), Toast.LENGTH_LONG).show();
        }

    }

}
