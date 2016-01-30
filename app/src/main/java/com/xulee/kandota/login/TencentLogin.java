package com.xulee.kandota.login;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;

import com.tencent.open.HttpStatusException;
import com.tencent.open.NetworkUnavailableException;
import com.tencent.tauth.Constants;
import com.tencent.tauth.IRequestListener;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import com.xulee.kandota.constant.Constants_Tencent;

/**
 * QQ登录
 *
 * @author Eric
 */
public class TencentLogin {

    public static Tencent mTencent;

    /**
     * 登录
     *
     * @param activity
     * @param authListener
     */
    public static void login(Activity activity,
                             final AuthListener authListener) {
        ThirdLoginUtils.loginType = LoginType.qq;
        authListener.onStart();
        mTencent = Tencent.createInstance(Constants_Tencent.APP_ID,
                activity.getApplicationContext());
        if (mTencent.isSessionValid()) {
            mTencent.logout(activity);
        }
        mTencent.login(activity, "all", new IUiListener() {

            @Override
            public void onError(UiError arg0) {
                System.out.println("TencentLogin-onError:" + arg0.errorMessage);
                if (authListener != null) authListener.onFailure();
            }

            @Override
            public void onComplete(JSONObject arg0) {
                System.out.println("onComplete");
                String openid = "";
                if (arg0.has("openid")) {
                    try {
                        openid = arg0.getString("openid");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                getUserInfo(openid, authListener);
            }

            @Override
            public void onCancel() {
            }

        });
    }

    /**
     * 获取用户资料。
     *
     * @param openid
     * @param authListener
     */
    public static void getUserInfo(final String openid,
                                   final AuthListener authListener) {
        System.out.println("getUserInfo-openid:" + openid);
        if (mTencent != null) {
            mTencent.requestAsync(Constants.GRAPH_SIMPLE_USER_INFO, null,
                    Constants.HTTP_GET, new IRequestListener() {

                        @Override
                        public void onUnknowException(Exception arg0,
                                                      Object arg1) {
                            if (authListener != null) authListener.onFailure();
                        }

                        @Override
                        public void onSocketTimeoutException(
                                SocketTimeoutException arg0, Object arg1) {
                            if (authListener != null) authListener.onFailure();
                        }

                        @Override
                        public void onNetworkUnavailableException(
                                NetworkUnavailableException arg0, Object arg1) {
                            if (authListener != null) authListener.onFailure();
                        }

                        @Override
                        public void onMalformedURLException(
                                MalformedURLException arg0, Object arg1) {
                            if (authListener != null) authListener.onFailure();
                        }

                        @Override
                        public void onJSONException(JSONException arg0,
                                                    Object arg1) {
                            if (authListener != null) authListener.onFailure();
                        }

                        @Override
                        public void onIOException(IOException arg0, Object arg1) {
                            if (authListener != null) authListener.onFailure();
                        }

                        @Override
                        public void onHttpStatusException(
                                HttpStatusException arg0, Object arg1) {
                            if (authListener != null) authListener.onFailure();
                        }

                        @Override
                        public void onConnectTimeoutException(
                                ConnectTimeoutException arg0, Object arg1) {
                            if (authListener != null) authListener.onFailure();
                        }

                        @Override
                        public void onComplete(JSONObject arg0, Object arg1) {
                            System.out.println("getUserInfo－onComplete");
                            String nickname = "";
                            String avatar = "";// 头像
                            if (arg0.has("nickname")) {
                                try {
                                    nickname = arg0.getString("nickname");
                                    System.out.println("nickname:" + nickname);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            if (arg0.has("figureurl_qq_2")) {
                                try {
                                    avatar = arg0.getString("figureurl_qq_2");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            } else if (arg0.has("figureurl_qq_1")) {
                                try {
                                    avatar = arg0.getString("figureurl_qq_1");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            System.out.println("avatar:" + avatar);

                            if (authListener != null) {
                                authListener.authSuccess(openid, nickname, avatar,
                                        LoginType.qq);
                            }

                        }
                    }, null);
        }
    }
}
