package com.xulee.kandota.utils.level;

import android.content.Context;

import com.liuguangqiang.framework.utils.PreferencesUtils;
import com.liuguangqiang.framework.utils.TimeUtils;
import com.loopj.android.http.RequestParams;
import com.xulee.kandota.utils.ApiUtils;
import com.xulee.kandota.utils.http.BaseResponseHandler;
import com.xulee.kandota.utils.http.JHttpClient;

/**
 * 经验管理
 * <p/>
 * Created by Eric on 14/11/26.
 */
public class LevelUtils {

    private final static String NAME = "LEVEL_MANAGE";

    private final static String DAILY_LOGIN = "DAILY_LOGIN";

    private static void addExp(int type, BaseResponseHandler responseHandler) {
        String url = "";
        RequestParams param = new RequestParams();
        param.put("type", "" + type);
        JHttpClient.post(url, param, responseHandler);
    }

    public static void dailyLogin(final Context context) {
        int daily = PreferencesUtils.getInt(context, NAME, DAILY_LOGIN);
        final int date = Integer.parseInt(TimeUtils.getDateTimeByFormat("yyyyMMdd"));
        if (date > daily) {
            addExp(ExpType.DAILY_LOGIN, new BaseResponseHandler() {
                @Override
                public void onSuccess(String result) {
                    PreferencesUtils.putInt(context, NAME, DAILY_LOGIN, date);
                }
            });
        }
    }

}
