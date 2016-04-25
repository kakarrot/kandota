package com.xulee.kandota.mvp.model;


import android.app.Activity;
import android.support.annotation.NonNull;

import com.liuguangqiang.framework.utils.GsonUtils;
import com.liuguangqiang.framework.utils.Logs;
import com.liuguangqiang.framework.utils.StringUtils;
import com.liuguangqiang.framework.utils.ToastUtils;
import com.xulee.kandota.async.AsyncUtils;
import com.xulee.kandota.entity.LoginResponse;
import com.xulee.kandota.entity.VerifyCodeResponse;
import com.xulee.kandota.mvp.ui.LoginUi;
import com.xulee.kandota.utils.http.JsonResponseHandler;

import javax.inject.Inject;

/**
 * Created by LX on 2016/4/20.
 */
public class LoginModel {

    @Inject
    public LoginModel() {

    }

    /**
     * 获取动态短信验证码
     *
     * @param context
     * @param ui
     */
    public void getSmsCodeDynamic(@NonNull final Activity context, @NonNull String mobile, @NonNull String verifyCode,@NonNull final LoginUi ui) {
        if (StringUtils.isEmptyOrNull(verifyCode)) {
            return;
        }

        AsyncUtils.getSmsCodeDynamic(context, mobile, verifyCode, new JsonResponseHandler<VerifyCodeResponse>(VerifyCodeResponse.class) {

            @Override
            public void onSuccess(VerifyCodeResponse result) {
                super.onSuccess(result);
                ToastUtils.show(context, result.message);
                if (result.status.equalsIgnoreCase("ok")) {
                    ui.setCountDownTimerStart();
                }
            }

            @Override
            public void onSuccess(String json) {
                super.onSuccess(json);
                Logs.i("短信验证码：" + json);
            }
        });
    }

    /**
     * 登录
     * @param context
     * @param mobile
     * @param smsCode
     * @param ui
     */
    public void login(@NonNull final Activity context, @NonNull String mobile, @NonNull String smsCode, @NonNull final LoginUi ui) {
        if (StringUtils.isEmptyOrNull(smsCode)) {
            return;
        }

        AsyncUtils.login(context, mobile, smsCode, new JsonResponseHandler<LoginResponse>(LoginResponse.class) {
            @Override
            public void onSuccess(LoginResponse result) {
                super.onSuccess(result);
                if(null != result && result.status.equals("ok")){
                    ui.onLoginSuccess(result);
                }
//                if(null != result){
//                    ToastUtils.show(context, result.message);
//                }
            }

            @Override
            public void onSuccess(String json) {
                super.onSuccess(json);
                ToastUtils.show(context, GsonUtils.getResult(json, "message"));
            }
        });
    }
}
