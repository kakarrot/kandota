package com.liuguangqiang.framework.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * ToastUtils
 * <p/>
 * Created by Eric on 2014-5-19
 */
public class ToastUtils {

    /**
     * Show a toast.
     *
     * @param context
     * @param resId
     */
    public static void show(Context context, int resId) {
        show(context, context.getString(resId));
    }

    /**
     * Show a toast.
     *
     * @param context
     * @param content
     */
    public static void show(Context context, String content) {
        if (context == null || content == null) {
            return;
        }
        Toast.makeText(context, content, Toast.LENGTH_LONG).show();
    }

    private static final int SHOW_OFFSET = 2000;
    private static long lastShowTime = 0;

    public static void show(Context context, int resId, boolean limit) {
        show(context, context.getString(resId), limit);
    }

    public static void show(Context context, String content, boolean limit) {
        long now = System.currentTimeMillis();
        long offset = now - lastShowTime;
        if (limit && offset > SHOW_OFFSET) {
            lastShowTime = now;
            show(context, content);
        } else if (!limit) {
            show(context, content);
        }
    }

}
