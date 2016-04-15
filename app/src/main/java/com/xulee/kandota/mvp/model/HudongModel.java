package com.xulee.kandota.mvp.model;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;

import com.liuguangqiang.framework.utils.Logs;
import com.liuguangqiang.framework.utils.PreferencesUtils;
import com.nostra13.universalimageloader.utils.L;
import com.xulee.kandota.async.AsyncGetCacheHudong;
import com.xulee.kandota.async.AsyncUtils;
import com.xulee.kandota.constant.Constants;
import com.xulee.kandota.entity.HudongResponse;
import com.xulee.kandota.entity.base.HudongTagResponse;
import com.xulee.kandota.mvp.ui.HudongUi;
import com.xulee.kandota.utils.http.JsonResponseHandler;

import org.w3c.dom.Text;

import javax.inject.Inject;

/**
 * Created by LX on 2016/1/27.
 */
public class HudongModel {

    private final String CACHE_HUDONG = "cahce_hudong";
    private final String CACHE_HUDONG_TAG = "cahce_hudong_tag";

    @Inject
    public HudongModel() {
    }

    /**
     * 取缓存
     *
     * @param context
     * @param ui
     */
    public void getHudongCache(final Context context, final HudongUi ui) {

        new AsyncGetCacheHudong(context, new AsyncGetCacheHudong.CacheHudongHandler() {
            @Override
            public void onSuccess(HudongResponse result) {
                if (null != result) {
                    Logs.i("获取互动缓存数据成功");
                    if (null != result.data && result.data.size() > 0) {
                        ui.showBanner(result.data);
                    }

                    if (null != result.theme && result.theme.size() > 0) {
                        ui.showThemeList(result.theme);
                    }
                    //先显示缓存，后根据标识标识符判断是否取线上数据
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            getHudongTag(context, ui);
                        }
                    }, 1000);
                } else { //无缓存，取线上
                    Logs.i("获取缓存数据失败");
                    getHudongLatest(context, ui);
                }
            }

            @Override
            public void onEmpty() {
                //无缓存，取线上
                L.d("获取互动缓存数据为空");
                getHudongLatest(context, ui);
            }
        }).execute(CACHE_HUDONG);
    }

    /**
     * 取线上最新
     *
     * @param context
     * @param ui
     */
    public void getHudongLatest(final Context context, final HudongUi ui) {
        AsyncUtils.getHudong(context, new JsonResponseHandler<HudongResponse>(HudongResponse.class) {

            @Override
            public void onSuccess(HudongResponse result) {
                if (null != result) {
                    Logs.i("获取互动线上数据成功");
                    if (null != result.data && result.data.size() > 0) {
                        ui.showBanner(result.data);
                    }

                    if (null != result.theme && result.theme.size() > 0) {
                        ui.showThemeList(result.theme);
                    }
                }
            }

            @Override
            public void onSuccess(String json) {
                super.onSuccess(json);
                PreferencesUtils.putString(context, Constants.PRE_SAVE_NAME, CACHE_HUDONG, json);
                Logs.i("获取互动线上数据成功 :" + json);
            }
        });
    }

    /**
     * 取标识符,并与旧标识符对比判断，若不同则更新缓存
     *
     * @param context
     */
    public void getHudongTag(final Context context, final HudongUi ui) {

        AsyncUtils.getHudongTag(context, new JsonResponseHandler<HudongTagResponse>(HudongTagResponse.class) {
            @Override
            public void onSuccess(HudongTagResponse result) {
                super.onSuccess(result);
                String oldTag = PreferencesUtils.getString(context, Constants.PRE_SAVE_NAME, CACHE_HUDONG_TAG);
                if (null != result && null != result.data && !TextUtils.isEmpty(result.data.uptime)
                        && !result.data.uptime.equals(oldTag)) {
                    Logs.i("获取互动标识符不相同，需要获取线上数据");
                    PreferencesUtils.putString(context, Constants.PRE_SAVE_NAME, CACHE_HUDONG_TAG, result.data.uptime);
                    getHudongLatest(context, ui);
                } else {
                    Logs.i("获取互动标识符失败或者标识符相同");
                }
            }

            @Override
            public void onSuccess(String json) {
                super.onSuccess(json);
                Logs.i("获取互动标识符成功 :" + json);
            }
        });
    }

}
