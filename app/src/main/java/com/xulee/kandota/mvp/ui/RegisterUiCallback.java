package com.xulee.kandota.mvp.ui;

import android.app.Activity;
import android.content.Context;

/**
 * Created by LX on 2016/4/27.
 */
public interface RegisterUiCallback {
    void register(Activity context, String mobile, String smsCode, String inviteCode, final RegisterUi ui);
    void getSmsCode(Activity context, String mobile, RegisterUi ui);
}
