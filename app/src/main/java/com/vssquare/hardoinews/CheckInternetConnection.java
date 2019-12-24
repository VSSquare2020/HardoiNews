package com.vssquare.hardoinews;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class CheckInternetConnection {

    public static boolean IsNetworkAvailable(Context ct){
       ConnectivityManager connectivityManager = (ConnectivityManager)ct.getSystemService(Context.CONNECTIVITY_SERVICE);
       if((connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE) != null
       && (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState()== NetworkInfo.State.CONNECTED)
       ) || (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)!= null)&&
               (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState()== NetworkInfo.State.CONNECTED)){
            return true;
       }
       else {
           return false;
       }
    }

}
