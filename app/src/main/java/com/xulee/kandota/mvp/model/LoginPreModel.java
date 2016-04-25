package com.xulee.kandota.mvp.model;


import android.app.Activity;

import com.liuguangqiang.framework.utils.Logs;
import com.liuguangqiang.framework.utils.StringUtils;
import com.liuguangqiang.framework.utils.ToastUtils;
import com.xulee.kandota.async.AsyncUtils;
import com.xulee.kandota.entity.VerifyCodeResponse;
import com.xulee.kandota.mvp.ui.LoginPreUi;
import com.xulee.kandota.utils.http.JsonResponseHandler;

import javax.inject.Inject;

/**
 * Created by LX on 2016/4/20.
 */
public class LoginPreModel {

    @Inject
    public LoginPreModel() {

    }

    /**
     * 获取图片验证码
     *
     * @param context
     * @param ui
     */
    public void getVerifyCode(final Activity context, final LoginPreUi ui) {
        AsyncUtils.getVerifyCode(context, new JsonResponseHandler<VerifyCodeResponse>(VerifyCodeResponse.class) {

            @Override
            public void onStart() {
                super.onStart();
                ui.setImgVcodeEnable(false);
            }

            @Override
            public void onSuccess(VerifyCodeResponse result) {
                super.onSuccess(result);
                ui.setBitmap(result.getDecryptData());
            }

            @Override
            public void onSuccess(String json) {
                super.onSuccess(json);
                Logs.i("图片验证码：" + json);
            }

            @Override
            public void onFinish() {
                super.onFinish();
                ui.setImgVcodeEnable(true);
            }
        });
    }

    /**
     * 获取动态短信验证码
     *
     * @param context
     * @param ui
     */
    public void getSmsCodeDynamic(final Activity context, String mobile, String verifyCode, final LoginPreUi ui) {
        if(StringUtils.isEmptyOrNull(verifyCode)){
            return;
        }
        AsyncUtils.getSmsCodeDynamic(context, mobile, verifyCode, new JsonResponseHandler<VerifyCodeResponse>(VerifyCodeResponse.class){

            @Override
            public void onStart() {
                super.onStart();
                ui.setImgVcodeEnable(false);
                ui.setGetSmsCodeButtonEnable(false);
            }

            @Override
            public void onSuccess(VerifyCodeResponse result) {
                super.onSuccess(result);
                ToastUtils.show(context, result.message);
                if(result.status.equalsIgnoreCase("ok")) {
                    ui.onGetSmsCodeSuccess();
                }
                if(result.message.contains("不存在")){
                    ui.showRegisterDialog();
                }
            }

            @Override
            public void onSuccess(String json) {
                super.onSuccess(json);
                Logs.i("短信验证码：" + json);
            }

            @Override
            public void onFinish() {
                super.onFinish();
                ui.setImgVcodeEnable(true);
                ui.setGetSmsCodeButtonEnable(true);
            }
        });
    }
}
