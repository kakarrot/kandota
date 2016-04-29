package com.xulee.kandota.mvp.model;

import android.app.Activity;
import android.content.Context;

import com.liuguangqiang.framework.utils.Logs;
import com.liuguangqiang.framework.utils.StringUtils;
import com.liuguangqiang.framework.utils.ToastUtils;
import com.xulee.kandota.R;
import com.xulee.kandota.async.AsyncUtils;
import com.xulee.kandota.entity.Auth;
import com.xulee.kandota.entity.LoginResponse;
import com.xulee.kandota.entity.RegisterResponse;
import com.xulee.kandota.entity.UserResponse;
import com.xulee.kandota.entity.VerifyCodeResponse;
import com.xulee.kandota.mvp.ui.RegisterUi;
import com.xulee.kandota.utils.LoginManager;
import com.xulee.kandota.utils.http.JsonResponseHandler;

import javax.inject.Inject;

/**
 * Created by LX on 2016/4/27.
 */
public class RegisterModel {

    @Inject
    public RegisterModel() {
    }

    /**
     * 获取图片验证码
     *
     * @param context
     * @param ui
     */
    public void getVerifyCode(final Activity context, final String mobile, final RegisterUi ui) {
        AsyncUtils.getVerifyCode(context, new JsonResponseHandler<VerifyCodeResponse>(VerifyCodeResponse.class) {

            @Override
            public void onStart() {
                super.onStart();
                ui.setGetSmsButtonEnable(false);
            }

            @Override
            public void onSuccess(VerifyCodeResponse result) {
                super.onSuccess(result);
                if(null != result){
                    getSmsCodeDynamic(context, mobile, result.getDecryptData(), ui);
                } else {
                    ui.setGetSmsButtonEnable(true);
                }
            }

            @Override
            public void onFailure(String msg) {
                super.onFailure(msg);
                ui.setGetSmsButtonEnable(true);
            }

            @Override
            public void onSuccess(String json) {
                super.onSuccess(json);
                Logs.i("图片验证码：" + json);
            }

        });
    }

    /**
     * 获取动态短信验证码
     *
     * @param context
     * @param ui
     */
    public void getSmsCodeDynamic(final Activity context, String mobile, String verifyCode, final RegisterUi ui) {
        if (StringUtils.isEmptyOrNull(verifyCode)) {
            return;
        }
        AsyncUtils.getRegisterSmsCode(context, mobile, verifyCode, new JsonResponseHandler<VerifyCodeResponse>(VerifyCodeResponse.class) {

            @Override
            public void onSuccess(VerifyCodeResponse result) {
                super.onSuccess(result);
                ToastUtils.show(context, result.message);
                if (result.status.equalsIgnoreCase("ok")) {
                    ui.setCountDownTimerStart();
                } else {
                    ui.setGetSmsButtonEnable(true);
                }
                if (result.message.contains("已经注册")) {
                    ui.showLoginDialog();
                }
            }

            @Override
            public void onSuccess(String json) {
                super.onSuccess(json);
                Logs.i("短信验证码：" + json);
            }

            @Override
            public void onFailure(String msg) {
                super.onFailure(msg);
                ui.setGetSmsButtonEnable(true);
            }
        });
    }

    public void register(final Activity context, String mobile, String smsCode, String inviteCode, final RegisterUi ui) {
        AsyncUtils.register(context, mobile, smsCode, inviteCode, new JsonResponseHandler<RegisterResponse>(RegisterResponse.class) {

            @Override
            public void onStart() {
                super.onStart();
                ui.setRegisterButtonEnable(false);
            }

            @Override
            public void onSuccess(RegisterResponse result) {
                super.onSuccess(result);
                if(null != result){
                    RegisterResponse.RegisterData.RegisterUserInfo userInfo = result.data.getRegisterUserInfo();
                    login(context, userInfo.username, userInfo.password, ui);
                } else {
                    ui.setRegisterButtonEnable(true);
                }
            }

            @Override
            public void onFailure(String msg) {
                super.onFailure(msg);
                ui.setRegisterButtonEnable(true);
            }
        });
    }

    /**
     * 注册完成后自动登录
     */
    public void login(final Activity context, final String username, String password, final RegisterUi ui) {
        AsyncUtils.login2(context, username, password, new JsonResponseHandler<LoginResponse>(LoginResponse.class){
            @Override
            public void onSuccess(LoginResponse result) {
                super.onSuccess(result);
                if(null != result){
                    ToastUtils.show(context, result.message);
                    getUser(context, result.data.uid, ui);
                } else {
                    ui.setRegisterButtonEnable(true);
                }
            }

            @Override
            public void onFailure(String msg) {
                super.onFailure(msg);
                ui.setRegisterButtonEnable(true);
            }
        });
    }

    /**
     * 登录成功后获取用户信息
     * @param context
     * @param uid
     * @param ui
     */
    public void getUser(final Activity context, String uid, final RegisterUi ui) {
        AsyncUtils.getUser(context, uid, new JsonResponseHandler<UserResponse>(UserResponse.class) {
            @Override
            public void onSuccess(UserResponse result) {
                super.onSuccess(result);
                if (null != result && result.status.equals("ok")) {
                    LoginManager.saveUser(context, result.data);
                    Auth auth = new Auth();
                    auth.uid = String.valueOf(result.data.uid);
                    auth.token = result.data.token;
                    LoginManager.saveAuth(context, auth);
                    context.finish();
                }
                if (null != result.message) {
                    ToastUtils.show(context, result.message);
                }
            }

            @Override
            public void onFinish() {
                super.onFinish();
                ui.setRegisterButtonEnable(true);
            }
        });
    }
}
