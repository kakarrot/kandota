package com.xulee.kandota.act;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.liuguangqiang.framework.utils.ToastUtils;
import com.xulee.kandota.R;
import com.xulee.kandota.async.AsyncUtils;
import com.xulee.kandota.base.BaseActivity;
import com.xulee.kandota.entity.Auth;
import com.xulee.kandota.entity.LoginResponse;
import com.xulee.kandota.entity.UserResponse;
import com.xulee.kandota.fragment.LoginFragment;
import com.xulee.kandota.fragment.LoginPreFragment;
import com.xulee.kandota.listeners.LoginListener;
import com.xulee.kandota.listeners.LoginPreListener;
import com.xulee.kandota.utils.LoginManager;
import com.xulee.kandota.utils.SkipUtils;
import com.xulee.kandota.utils.http.JsonResponseHandler;

import butterknife.Bind;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements LoginPreListener, LoginListener {

    private LoginPreFragment loginPreFragment;

    private LoginFragment loginFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_login;
    }

    public void initViews() {
        setTitle(R.string.title_activity_login);
        loginPreFragment = new LoginPreFragment();
        loginPreFragment.setLoginListener(this);
        replaceFragment(R.id.layout_container, loginPreFragment);
    }

    @OnClick(R.id.tv_register)
    void register(View view) {
        SkipUtils.skipToRegister(this);
    }

    @Override
    public void onNext(String mobile, String vcode) {
        if (null == loginFragment) {
            loginFragment = LoginFragment.newInstance(mobile, vcode);
        }
        loginFragment.setLoginListener(this);
        replaceFragment(R.id.layout_container, loginFragment);
    }

    @Override
    public void onLoginSuccess(LoginResponse response) {
        if (null != response && null != response.data) {
            getUser(response.data.uid);
        }
    }

    public void getUser(String uid) {
        AsyncUtils.getUser(this, uid, new JsonResponseHandler<UserResponse>(UserResponse.class) {
            @Override
            public void onSuccess(UserResponse result) {
                super.onSuccess(result);
                if (result.status.equals("ok")) {
                    LoginManager.saveUser(LoginActivity.this, result.data);
                    Auth auth = new Auth();
                    auth.uid = String.valueOf(result.data.uid);
                    auth.token = result.data.token;
                    LoginManager.saveAuth(LoginActivity.this, auth);
                    finish();
                }
                if (null != result.message) {
                    ToastUtils.show(LoginActivity.this, result.message);
                }
            }
        });
    }
}
