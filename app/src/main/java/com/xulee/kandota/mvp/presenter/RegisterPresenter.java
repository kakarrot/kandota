package com.xulee.kandota.mvp.presenter;

import android.app.Activity;
import android.content.Context;

import com.liuguangqiang.android.mvp.Presenter;
import com.xulee.kandota.app.MyApplication;
import com.xulee.kandota.mvp.model.RegisterModel;
import com.xulee.kandota.mvp.ui.RegisterUi;
import com.xulee.kandota.mvp.ui.RegisterUiCallback;

import javax.inject.Inject;

/**
 * Created by LX on 2016/4/27.
 */
public class RegisterPresenter extends Presenter<RegisterUi, RegisterUiCallback> {

    private Activity mContext;

    @Inject
    RegisterModel registerModel;

    public RegisterPresenter(Activity context, RegisterUi ui) {
        super(ui);
        mContext = context;
        MyApplication.from().inject(this);
    }

    @Override
    protected void populateUi(RegisterUi ui) {

    }

    @Override
    protected RegisterUiCallback createUiCallback(RegisterUi ui) {
        return new RegisterUiCallback() {

            @Override
            public void register(Activity context, String mobile, String smsCode, String inviteCode, RegisterUi ui) {
                registerModel.register(context, mobile, smsCode, inviteCode, ui);
            }

            @Override
            public void getSmsCode(Activity context, String mobile, RegisterUi ui) {
                registerModel.getVerifyCode(context, mobile, ui);
            }
        };
    }
}
