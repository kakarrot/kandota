package com.xulee.kandota.mvp.presenter;

import android.app.Activity;

import com.liuguangqiang.android.mvp.Presenter;
import com.xulee.kandota.app.MyApplication;
import com.xulee.kandota.mvp.model.LoginPreModel;
import com.xulee.kandota.mvp.ui.LoginPreUi;
import com.xulee.kandota.mvp.ui.LoginPreUiCallback;

import javax.inject.Inject;

/**
 * Created by LX on 2016/4/20.
 */
public class LoginPrePresenter extends Presenter<LoginPreUi, LoginPreUiCallback>{

    private Activity mContext;

    @Inject
    LoginPreModel loginPreModel;

    public LoginPrePresenter(Activity context, LoginPreUi ui){
        super(ui);
        this.mContext = context;
        MyApplication.from().inject(this);
    }

    @Override
    protected void populateUi(LoginPreUi ui) {
        loginPreModel.getVerifyCode(mContext, ui);
    }

    @Override
    protected LoginPreUiCallback createUiCallback(final LoginPreUi ui) {
        return new LoginPreUiCallback() {

            @Override
            public void getVerifyCode() {
                loginPreModel.getVerifyCode(mContext, ui);
            }

            @Override
            public void getSmsCodeDynamic(String mobile, String verifyCode) {
                loginPreModel.getSmsCodeDynamic(mContext, mobile, verifyCode, ui);
            }
        };
    }
}
