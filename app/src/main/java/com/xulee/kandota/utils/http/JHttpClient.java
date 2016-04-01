package com.xulee.kandota.utils.http;

import android.content.Context;
import android.util.Log;

import com.liuguangqiang.framework.utils.NetworkUtils;
import com.liuguangqiang.framework.utils.ToastUtils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import com.xulee.kandota.R;
import com.xulee.kandota.constant.Constants;
import com.xulee.kandota.entity.Auth;
import com.xulee.kandota.utils.FailureUtils;
import com.xulee.kandota.utils.LoginManager;

import cz.msebera.android.httpclient.Header;


public class JHttpClient {

    private static final String TAG = "JHttpClient";

    private static final int CONNECT_TIMEOUT = 10 * 1000;

    private static AsyncHttpClient httpClient;

    private static final String HEADER_AUTH = "Authorization";

    private static void createHttpCilent() {
        if (httpClient == null) {
            httpClient = new AsyncHttpClient();
            httpClient.setTimeout(CONNECT_TIMEOUT);
            httpClient.addHeader("X-Device", Constants.OEPN_UD_ID);
        }
        setAuthToken();
    }

    private static void setAuthToken() {
        if (LoginManager.isLogin()) {
            Auth auth = LoginManager.getAuth();
            httpClient.removeHeader(HEADER_AUTH);
            httpClient.addHeader(HEADER_AUTH, auth.authToken);
        } else {
            httpClient.removeHeader(HEADER_AUTH);
        }
    }

    public static void get(String url, RequestParams params, BaseResponseHandler handler) {
        get(null, url, params, handler);
    }

    /**
     * HTTP request with GET.
     *
     * @param url     地址
     * @param params  参数
     * @param handler 回调
     */
    public static void get(final Context context, String url, RequestParams params, final BaseResponseHandler handler) {
        createHttpCilent();
//        if(!NetworkUtils.isAvailable(context)){
//            ToastUtils.show(context, R.string.error_network_unavailable_format);
//            return;
//        }
        httpClient.get(url, params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.i(TAG, "get-onFailure:" + statusCode);
                if (handler != null) {
                    handler.onFailure(responseString);
                    handler.onFailure(statusCode, responseString);
                    if (context != null) {
                        FailureUtils.handleHttpRequest(context, responseString, statusCode, throwable);
                    }
                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                if (handler != null) {
                    if (statusCode > 300) {
                        handler.onFailure(statusCode, responseString);
                        if (context != null) {
                            FailureUtils.handleHttpRequest(context, responseString, statusCode, null);
                        }
                    } else {
                        handler.onSuccess(responseString);
                    }
                }
            }

            @Override
            public void onStart() {
                if (handler != null) handler.onStart();
            }

            @Override
            public void onFinish() {
                if (handler != null) handler.onFinish();
            }
        });

    }

    public static void post(String url, RequestParams params, final BaseResponseHandler handler) {
        createHttpCilent();
        httpClient.post(url, params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                if (handler != null) {
                    handler.onFailure(responseString);
                    handler.onFailure(statusCode, responseString);
                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                if (handler != null) handler.onSuccess(responseString);
            }

            @Override
            public void onStart() {
                if (handler != null) handler.onStart();
            }

            @Override
            public void onFinish() {
                if (handler != null) handler.onFinish();
            }
        });
    }

    public static void put(String url, RequestParams params, final BaseResponseHandler handler) {
        createHttpCilent();
        httpClient.put(url, params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                if (handler != null) {
                    handler.onFailure(responseString);
                    handler.onFailure(statusCode, responseString);
                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                if (handler != null) handler.onSuccess(responseString);
            }

            @Override
            public void onStart() {
                if (handler != null) handler.onStart();
            }

            @Override
            public void onFinish() {
                if (handler != null) handler.onFinish();
            }
        });
    }

    public static void delete(String url, RequestParams params, final BaseResponseHandler handler) {
        if (url.contains("?")) {
            url = url + "&" + params.toString();
        } else {
            url = url + "?" + params.toString();
        }
        createHttpCilent();
        httpClient.delete(url, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                if (handler != null) {
                    handler.onFailure(responseString);
                    handler.onFailure(statusCode, responseString);
                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                if (handler != null) handler.onSuccess(responseString);
            }

            @Override
            public void onStart() {
                if (handler != null) handler.onStart();
            }

            @Override
            public void onFinish() {
                if (handler != null) handler.onFinish();
            }
        });
    }
}
