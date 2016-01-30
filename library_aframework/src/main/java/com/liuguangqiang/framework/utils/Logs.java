package com.liuguangqiang.framework.utils;

import android.util.Log;

/**
 * Created by Eric on 15/3/11.
 */
public class Logs {

    private static String TAG = "AFramework";

    private static boolean isDebug = true;

    private Logs() {
    }

    public static void setTAG(String tag) {
        TAG = tag;
    }

    public static void setIsDebug(boolean debug) {
        isDebug = debug;
    }

    public static void i(Object obj) {
        Logs.i(TAG, obj.toString());
    }

    public static void i(String msg) {
        i(TAG, msg);
    }

    public static void i(String tag, String msg) {
        if (isDebug) {
            Log.i(tag, msg);
        }
    }

}
