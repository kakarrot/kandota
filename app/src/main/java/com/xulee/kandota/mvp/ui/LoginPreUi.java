package com.xulee.kandota.mvp.ui;

import com.liuguangqiang.android.mvp.BaseUi;

/**
 * Created by LX on 2016/4/20.
 */
public interface LoginPreUi extends BaseUi<LoginPreUiCallback> {
    void setImgVcodeEnable(boolean bool);
    void setBitmap(String vcode);
    void setGetSmsCodeButtonEnable(boolean bool);
    void onGetSmsCodeSuccess();
    void showRegisterDialog();
}
