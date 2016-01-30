package com.xulee.kandota.utils;

import android.content.Context;

import com.liuguangqiang.framework.utils.NetworkUtils;
import com.liuguangqiang.framework.utils.TimeUtils;
import com.liuguangqiang.framework.utils.ToastUtils;


/**
 * Created by Eric on 15/1/10.
 */
public class FailureUtils {

    private static final String TAG = "FailureUtils";
    private static final String ERROR_DETAIL = "error:%s,statusCode:%s,throwable:%s";

    private static long lastToastOnNetwork = 0;

    public static void handleHttpRequest(Context context, String error, int statusCode, Throwable throwable) {
//        Log.i(TAG, String.format(ERROR_DETAIL, error, statusCode, throwable));
        if (statusCode == 403) {
            ToastUtils.show(context, "登录已过期,请重新登录");
        }

        if (NetworkUtils.isAvailable(context)) {
            ToastUtils.show(context, error);
        } else {

            long now = TimeUtils.getTimestampSeconds();
            if (now - lastToastOnNetwork > 1) {
                String username = LoginManager.isLogin() ? LoginManager.getUser().username : "";
                ToastUtils.show(context, String.format("error_network_unavailable_format:%s", username));
            }
            lastToastOnNetwork = now;
        }
    }

}
