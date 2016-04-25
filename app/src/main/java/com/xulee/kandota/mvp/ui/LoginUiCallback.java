package com.xulee.kandota.mvp.ui;

/**
 * Created by LX on 2016/4/20.
 */
public interface LoginUiCallback {
    void getSmsCodeDynamic(String mobile, String verifyCode);
    void login(String mobile, String smsCode);
}
