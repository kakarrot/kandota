package com.xulee.kandota.async;

import android.content.Context;

import com.xulee.kandota.entity.AdsResponse;
import com.xulee.kandota.entity.NewsResponse;
import com.xulee.kandota.utils.ApiUtils;
import com.xulee.kandota.utils.http.JHttpClient;
import com.xulee.kandota.utils.http.JsonResponseHandler;

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
        String url = ApiUtils.getLanuchAdsUrl();
        JHttpClient.get(context, url, null, handler);
    }

}
