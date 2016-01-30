package com.liuguangqiang.framework.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * NotifyUtils
 * <p/>
 * Created by Eric on 2014-5-19.
 */
public class NotifyUtils {

    /**
     * Clear all notifications.
     *
     * @param context
     */
    public static void clearAll(Context context) {
        NotificationManager manager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        manager.cancelAll();
    }

    /**
     * Show a notifications.
     *
     * @param context
     * @param title
     * @param summary
     * @param iconResId
     * @param intentClass
     */
    public static void showNotify(Context context, String title,
                                  String summary, int iconResId, Class<?> intentClass) {
        showNotify(context, title, summary, iconResId, 0, intentClass);
    }

    /**
     * Show a notifications.
     *
     * @param context
     * @param title
     * @param summary
     * @param iconResId
     * @param soundResId
     * @param intentClass
     */
    public static void showNotify(Context context, String title,
                                  String summary, int iconResId, int soundResId, Class<?> intentClass) {
        NotificationManager manager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        Notification.Builder builder = new Notification.Builder(context);
        builder.setContentTitle(title);
        builder.setContentText(summary);
        builder.setSmallIcon(iconResId);

        if (intentClass != null)
            builder.setContentIntent(createPendingIntent(context, intentClass));

        Notification notification = builder.build();
        notification.flags = Notification.FLAG_AUTO_CANCEL;
        notification.icon = iconResId;
        notification.tickerText = summary;
        if (soundResId > 0) {
            notification.sound = Uri.parse("android.resource://"
                    + context.getPackageName() + "/" + soundResId);
        }
        manager.notify(0, notification);
    }

    /**
     * Return a PendingIntent that should skip to...
     *
     * @param context
     * @param classOf
     * @return
     */
    public static PendingIntent createPendingIntent(Context context,
                                                    Class<?> classOf) {
        Intent intent = new Intent(context, classOf);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent,
                PendingIntent.FLAG_CANCEL_CURRENT);
        return pendingIntent;
    }

}
