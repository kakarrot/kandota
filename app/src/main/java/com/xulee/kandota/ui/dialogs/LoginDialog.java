package com.xulee.kandota.ui.dialogs;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;

import com.loopj.android.http.RequestParams;
import com.xulee.kandota.R;
import com.xulee.kandota.constant.Constants;
import com.xulee.kandota.entity.Auth;
import com.xulee.kandota.entity.User;
import com.xulee.kandota.listeners.LoginListener;
import com.xulee.kandota.login.AuthListener;
import com.xulee.kandota.login.AuthSuccess;
import com.xulee.kandota.login.LoginType;
import com.xulee.kandota.login.SinaWeiboLogin;
import com.xulee.kandota.login.TencentLogin;
import com.xulee.kandota.utils.LoginManager;
import com.xulee.kandota.utils.http.BaseResponseHandler;
import com.xulee.kandota.utils.http.JHttpClient;
import com.xulee.kandota.utils.http.JsonResponseHandler;

/**
 * Created by Eric on 14/11/12.
 */
public class LoginDialog implements AuthListener {

    private MyDialog dialog;

    private Activity act;

    private LoginListener loginListener;

    public void setLoginListener(LoginListener l) {
        loginListener = l;
    }

    public LoginDialog(Activity context) {
        act = context;
        dialog = new MyDialog(context, R.string.choose_login_type);
        String[] items = context.getResources().getStringArray(R.array.login_types);
        dialog.setItems(items, new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        loginSina();
                        break;
                    case 1:
                        loginByTencent();
                        break;
                }
                dialog.showLoading();
            }
        }, false);
    }

    public void show() {
        dialog.show();
    }

    private void loginSina() {
        SinaWeiboLogin.login(act, this);
    }

    private void loginByTencent() {
        TencentLogin.login(act, this);
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onFailure() {
        dialog.dismiss();
    }

    @Override
    public void authSuccess(String uid, String username, String avatarUrl, LoginType loginType) {
        AuthSuccess authSuccess = new AuthSuccess();
        authSuccess.setOpenId(uid);
        authSuccess.setUsername(username);
        authSuccess.setAvatarUrl(avatarUrl);
        authSuccess.setLoginType(loginType);
        login(authSuccess);

        Message msg = mHandler.obtainMessage();
        msg.what = 0;
        msg.sendToTarget();
    }

    private void login(final AuthSuccess authSuccess) {
        String url = "";
        System.out.println("login:" + url);
        RequestParams params = new RequestParams();
        params.put("open_id", authSuccess.getOpenId().toLowerCase());
        JHttpClient.put(url, params, new JsonResponseHandler<Auth>(Auth.class, false) {
            @Override
            public void onSuccess(Auth result) {
                if (result != null) {
//                    getUser(result.userId);
                    LoginManager.saveAuth(act, result);
                }
            }

            @Override
            public void onFailure(int statusCode, String msg) {
//                System.out.println("login-onFailure-statusCode:" + statusCode);
//                System.out.println("login-onFailure-msg:" + msg);
                if (statusCode == Constants.STATUS_CODE_UN_REG) {
                    reg(authSuccess);
                }
            }

            @Override
            public void onFinish() {
                System.out.println("login-onFinish");
                dialog.dismiss(); // 新添加
            }
        });
    }

    private void reg(AuthSuccess authSuccess) {
        String url = "";
        System.out.println("reg:" + url);
        RequestParams params = new RequestParams();
        params.put("username", authSuccess.getUsername());
        params.put("avatar", authSuccess.getAvatarUrl());
        params.put("open_id", authSuccess.getOpenId().toLowerCase());
        params.put("from", authSuccess.getLoginType().toString());
        System.out.println("reg:" + url + "?" + params.toString());
        JHttpClient.post(url, params, new BaseResponseHandler() {
            @Override
            public void onSuccess(String result) {
                System.out.println("reg-onSuccess:" + result);
            }

            @Override
            public void onFailure(int statusCode, String msg) {
                dialog.dismiss();
            }

            @Override
            public void onFinish() {
            }
        });
    }

    private void getUser(String userId) {
        String url = "";
        JHttpClient.get(act, url, null, new JsonResponseHandler<User>(User.class, false) {

            @Override
            public void onOrigin(String json) {
                System.out.println("getUser-onSuccess:" + json);
            }

            @Override
            public void onSuccess(User result) {
                LoginManager.saveUser(act, result);
                Message msg = mHandler.obtainMessage();
                msg.what = 1;
                msg.sendToTarget();
            }

            @Override
            public void onFinish() {
                dialog.dismiss();
            }
        });
    }

    private final Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {

            switch (msg.what) {
                case 0:
                    dialog.showLoading();
                    break;
                case 1:
                    if (loginListener != null) {
                        loginListener.onSuccess();
                    }
                    break;
            }

            return false;
        }
    });


}
