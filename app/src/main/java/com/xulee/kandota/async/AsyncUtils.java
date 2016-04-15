package com.xulee.kandota.async;

import android.content.Context;

import com.liuguangqiang.framework.utils.Logs;
import com.loopj.android.http.RequestParams;
import com.xulee.kandota.entity.AdsResponse;
import com.xulee.kandota.entity.HudongResponse;
import com.xulee.kandota.entity.NewsResponse;
import com.xulee.kandota.entity.base.BaseResponse;
import com.xulee.kandota.entity.base.HudongTagResponse;
import com.xulee.kandota.utils.ApiUtils;
import com.xulee.kandota.utils.http.JHttpClient;
import com.xulee.kandota.utils.http.JsonResponseHandler;

/**
 * 网络请求
 */
public class AsyncUtils {

    /**
     * 获取资讯
     *
     * @param context
     * @param handler
     */
    public static void getNews(Context context, String fid, int page,
                                 JsonResponseHandler<NewsResponse> handler) {
        String url = ApiUtils.getNewsUrl(fid, page);
        JHttpClient.get(context, url, null, handler);
    }

    /**
     * 获取开机广告
     *
     * @param context
     * @param handler
     */
    public static void getAds(Context context, JsonResponseHandler<AdsResponse> handler) {
        RequestParams params = getParam(ApiUtils.getLanuchAdsUrl());
        JHttpClient.post(context, ApiUtils.URL_GET_ADS, params, handler);
    }


    /**
     * 获取互动
     * @param context
     * @param handler
     */
    public static void getHudong(Context context, JsonResponseHandler<HudongResponse> handler){
        RequestParams params = getParam(ApiUtils.getHudongUrl());
        Logs.i(params);
        JHttpClient.post(context, ApiUtils.URL_GET_HUDONG, params, handler);
    }

    /**
     * 获取互动标识符
     * @param context
     * @param handler
     */
    public static void getHudongTag(Context context, JsonResponseHandler<HudongTagResponse> handler){
        RequestParams params = getParam(ApiUtils.getHudongTagUrl());
        JHttpClient.post(context, ApiUtils.URL_GET_HUDONG_TAG, params, handler);
    }

    public static RequestParams getParam(String qParam){
        RequestParams params = new RequestParams();
        params.add("q", qParam);
        return params;
    }
}
