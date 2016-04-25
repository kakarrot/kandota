package com.xulee.kandota.mvp.ui;

import com.liuguangqiang.android.mvp.BaseUi;
import com.xulee.kandota.entity.LoginResponse;

/**
 * Created by LX on 2016/4/20.
 */
public interface LoginUi extends BaseUi<LoginUiCallback> {
    void setGetSmsCodeEnable(boolean bool);

    void setCountDownTimerStart();

    String getMobile();

    String getVcode();

    void onLoginSuccess(LoginResponse loginResponse);
}
