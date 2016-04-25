package com.xulee.kandota.async;

import android.content.Context;

import com.liuguangqiang.framework.utils.Logs;
import com.loopj.android.http.RequestParams;
import com.xulee.kandota.entity.AdsResponse;
import com.xulee.kandota.entity.HudongResponse;
import com.xulee.kandota.entity.LoginResponse;
import com.xulee.kandota.entity.NewsResponse;
import com.xulee.kandota.entity.UserResponse;
import com.xulee.kandota.entity.base.HudongTagResponse;
import com.xulee.kandota.entity.VerifyCodeResponse;
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
        RequestParams params = getParam(ApiUtils.getLanuchAdsParams());
        JHttpClient.post(context, ApiUtils.URL_GET_ADS, params, handler);
    }


    /**
     * 获取互动
     * @param context
     * @param handler
     */
    public static void getHudong(Context context, JsonResponseHandler<HudongResponse> handler){
        RequestParams params = getParam(ApiUtils.getHudongParams());
        Logs.i(params);
        JHttpClient.post(context, ApiUtils.URL_GET_HUDONG, params, handler);
    }

    /**
     * 获取互动标识符
     * @param context
     * @param handler
     */
    public static void getHudongTag(Context context, JsonResponseHandler<HudongTagResponse> handler){
        RequestParams params = getParam(ApiUtils.getHudongTagParams());
        JHttpClient.post(context, ApiUtils.URL_GET_HUDONG_TAG, params, handler);
    }

    /**
     * 获取图片验证码
     * @param context
     * @param handler
     */
    public static void getVerifyCode(Context context, JsonResponseHandler<VerifyCodeResponse> handler){
        RequestParams params = new RequestParams();
        params.add("imei", ApiUtils.getVerifyCodeParams());
        JHttpClient.post(context, ApiUtils.URL_GET_VERIFY_CODE, params, handler);
    }

    /**
     * 获取短信验证码（登录）
     * @param context
     * @param mobile
     * @param verifyCode
     * @param handler
     */
    public static void getSmsCodeDynamic(Context context, String mobile, String verifyCode, JsonResponseHandler<VerifyCodeResponse> handler){
        RequestParams params = getParam(ApiUtils.getSmsCodeDynamicParams(mobile, verifyCode));
        JHttpClient.post(context, ApiUtils.URL_GET_SMS_CODE_DYNAMIC, params, handler);
    }

    /**
     * 登录
     * @param context
     * @param mobile 手机号
     * @param smsCode 短信验证码
     * @param handler
     */
    public static void login(Context context, String mobile, String smsCode, JsonResponseHandler<LoginResponse> handler){
        RequestParams params = getParam(ApiUtils.getLoginParams(mobile, smsCode));
        JHttpClient.post(context, ApiUtils.URL_LOGIN, params, handler);
    }

    /**
     * 注册
     * @param context
     * @param smsCode
     * @param handler
     */
    public static void register(Context context, String smsCode, JsonResponseHandler<LoginResponse> handler){
        RequestParams params = getParam(ApiUtils.getRegisterParams(smsCode));
        JHttpClient.post(context, ApiUtils.URL_REGISTER, params, handler);
    }

    /**
     * 获取用户个人信息
     * @param context
     * @param uid
     * @param handler
     */
    public static void getUser(Context context, String uid, JsonResponseHandler<UserResponse> handler){
        RequestParams params = getParam(ApiUtils.getUserParams(uid));
        JHttpClient.post(context, ApiUtils.URL_GET_USER, params, handler);
    }

    /**
     * 加密参数统一返回格式
     * @param qParam
     * @return
     */
    public static RequestParams getParam(String qParam){
        RequestParams params = new RequestParams();
        params.add("q", qParam);
        return params;
    }
}
