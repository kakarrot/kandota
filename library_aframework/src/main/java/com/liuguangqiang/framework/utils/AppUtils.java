package com.liuguangqiang.framework.utils;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.util.List;

/**
 * AppUtils
 * <p>
 * Created by Eric on 2014-5-19.
 */
public class AppUtils {

    /**
     * whether application is in background.
     * <ul>
     * <li>this method need use permission android.permission.GET_TASKS in AndroidManifest.xml</li>
     * </ul>
     */
    public static boolean isApplicationInBackground(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> taskList = am.getRunningTasks(1);
        if (taskList != null && !taskList.isEmpty()) {
            ComponentName topActivity = taskList.get(0).topActivity;
            if (topActivity != null && !topActivity.getPackageName().equals(context.getPackageName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Return the model of product.
     *
     * @return
     */
    public static String getPhoneModel() {
        return android.os.Build.MODEL;
    }

    /**
     * Return the name of the overall product.
     *
     * @return
     */
    public static String getProduct() {
        return android.os.Build.PRODUCT;
    }

    public static String getVersionRELEASE() {
        return android.os.Build.VERSION.RELEASE;
    }

    /**
     * Return the IMEI code
     *
     * @param context
     * @return
     */
    public static String getImeiCode(Context context) {
        TelephonyManager tm = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getDeviceId();
    }

    /**
     * Return the android id.
     *
     * @param context
     * @return
     */
    public static String getDeviceId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);
    }

    /**
     * Return the mobile number.
     *
     * @param context
     * @return
     */
    public static String getMobileNumber(Context context) {
        TelephonyManager tm = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getLine1Number();
    }

    /**
     * Return version name of the application.
     *
     * @param context
     * @return
     */
    public static String getVersionName(Context context) {
        PackageInfo info = getPackageInfo(context);
        return info.versionName;
    }

    /**
     * Return version code of the application.
     *
     * @param context
     * @return
     */
    public static int getVersionCode(Context context) {
        PackageInfo info = getPackageInfo(context);
        return info.versionCode;
    }

    /**
     * Return the package name of the application.
     *
     * @param context
     * @return
     */
    public static String getPackageName(Context context) {
        PackageInfo info = getPackageInfo(context);
        return info.packageName;
    }

    /**
     * Return the package info.
     *
     * @param context
     * @return
     */
    public static PackageInfo getPackageInfo(Context context) {
        PackageManager manager;
        PackageInfo info = null;
        manager = context.getPackageManager();
        try {
            info = manager.getPackageInfo(context.getPackageName(), 0);
        } catch (NameNotFoundException e) {
            Log.e("AppUtils", e.getMessage());
        }
        return info;
    }

    public static String getProcessName(Context cxt, int pid) {
        ActivityManager am = (ActivityManager) cxt.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningApps = am.getRunningAppProcesses();
        if (runningApps == null || runningApps.isEmpty()) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo procInfo : runningApps) {
            if (procInfo.pid == pid) {
                return procInfo.processName;
            }
        }
        return null;
    }

}
