package com.liuguangqiang.framework.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;

/**
 * NetworkUtils
 * <p/>
 * Created by Eric on 2014-5-19.
 */
public class NetworkUtils {

    /**
     * Return whether network is available.
     *
     * @param context
     * @return true if the network is available, false otherwise.
     */
    public static boolean isAvailable(Context context) {
        if (context != null) {
            ConnectivityManager manager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkinfo = manager.getActiveNetworkInfo();
            return !(networkinfo == null || !networkinfo.isAvailable());
        }
        return false;
    }

    /**
     * Return whether WIFI is enabled or disabled.
     *
     * @param context
     * @return
     */
    public static boolean isWifiEnabled(Context context) {
        return ((WifiManager) context.getSystemService(Context.WIFI_SERVICE))
                .isWifiEnabled();
    }

}
