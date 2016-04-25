package com.xulee.kandota.mvp.ui;

/**
 * Created by LX on 2016/4/20.
 */
public interface LoginPreUiCallback {
    void getVerifyCode();

    void getSmsCodeDynamic(String mobile, String verifyCode);
}
