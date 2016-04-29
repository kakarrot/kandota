package com.xulee.kandota.mvp.ui;

import com.liuguangqiang.android.mvp.BaseUi;

/**
 * Created by LX on 2016/4/27.
 */
public interface RegisterUi extends BaseUi<RegisterUiCallback> {
    void setRegisterButtonEnable(boolean enable);
    void setGetSmsButtonEnable(boolean enable);
    void setCountDownTimerStart();
    void showLoginDialog();
}
