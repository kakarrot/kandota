package com.xulee.kandota.mvp.presenter;

import android.app.Activity;

import com.liuguangqiang.android.mvp.Presenter;
import com.xulee.kandota.app.MyApplication;
import com.xulee.kandota.mvp.model.LoginModel;
import com.xulee.kandota.mvp.ui.LoginUi;
import com.xulee.kandota.mvp.ui.LoginUiCallback;

import javax.inject.Inject;

/**
 * Created by LX on 2016/4/20.
 */
public class LoginPresenter extends Presenter<LoginUi, LoginUiCallback>{

    private Activity mContext;

    @Inject
    LoginModel loginModel;

    public LoginPresenter(Activity context, LoginUi ui){
        super(ui);
        this.mContext = context;
        MyApplication.from().inject(this);
    }

    @Override
    protected void populateUi(LoginUi ui) {
//        loginModel.getSmsCodeDynamic(mContext, ui.getMobile(), ui.getVcode(), ui);
    }

    @Override
    protected LoginUiCallback createUiCallback(final LoginUi ui) {
        return new LoginUiCallback() {

            @Override
            public void getSmsCodeDynamic(String mobile, String verifyCode) {
                loginModel.getSmsCodeDynamic(mContext, mobile, verifyCode, ui);
            }

            @Override
            public void login(String mobile, String smsCode) {
                loginModel.login(mContext, mobile, smsCode, ui);
            }
        };
    }
}
