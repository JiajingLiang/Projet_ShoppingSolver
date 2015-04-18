package com.polymtl.shoppingsolver.util;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class DetectConnectivity {


    /**
     * Get the network info
     */
    public static NetworkInfo getNetworkInfo(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo();
    }

    /**
     * Check if there is any connectivity
     * if device can connect internet
     */
    public static boolean isConnected(Context context){
        NetworkInfo info = DetectConnectivity.getNetworkInfo(context);
        return (info != null && info.isConnected());
    }



}

