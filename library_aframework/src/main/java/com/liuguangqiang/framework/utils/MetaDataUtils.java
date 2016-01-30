package com.liuguangqiang.framework.utils;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;

/**
 * Created by Eric on 15/4/1.
 */
public class MetaDataUtils {

    public static String getMetaData(Context context, String metaDataName) {
        String result = "";
        try {
            ApplicationInfo appInfo = context.getPackageManager()
                    .getApplicationInfo(context.getPackageName(),
                            PackageManager.GET_META_DATA);
            result = appInfo.metaData.getString(metaDataName);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public static String getMetaData(Activity context, String metaDataName) {
        String result = "";
        try {
            ActivityInfo activityInfo = context.getPackageManager().getActivityInfo(context.getComponentName(),
                    PackageManager.GET_META_DATA);
            result = activityInfo.metaData.getString(metaDataName);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public static String getMetaData(Service context, String metaDataName) {
        String result = "";
        try {
            ComponentName componentName = new ComponentName(context, context.getClass());
            ServiceInfo serviceInfo = context.getPackageManager().getServiceInfo(componentName,
                    PackageManager.GET_META_DATA);
            result = serviceInfo.metaData.getString(metaDataName);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

}
