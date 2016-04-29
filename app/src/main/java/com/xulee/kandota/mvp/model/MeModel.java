package com.xulee.kandota.mvp.model;

import android.app.Activity;

import com.liuguangqiang.framework.utils.ToastUtils;
import com.xulee.kandota.R;
import com.xulee.kandota.async.AsyncUtils;
import com.xulee.kandota.entity.Auth;
import com.xulee.kandota.entity.SignResponse;
import com.xulee.kandota.entity.UserResponse;
import com.xulee.kandota.entity.base.BaseResponse;
import com.xulee.kandota.listeners.OnPositiveClickListener;
import com.xulee.kandota.mvp.ui.MeUi;
import com.xulee.kandota.ui.dialogs.MyDialog;
import com.xulee.kandota.utils.LoginManager;
import com.xulee.kandota.utils.SkipUtils;
import com.xulee.kandota.utils.http.JsonResponseHandler;

import javax.inject.Inject;

/**
 * Created by LX on 2016/4/28.
 */
public class MeModel {

    @Inject
    public MeModel(){
    }

    /**
     * 获取用户信息
     * @param context
     * @param ui
     */
    public void getUser(final Activity context, final MeUi ui){
        String uid = null;
        if (LoginManager.isLogin()) {
            uid = String.valueOf(LoginManager.getUser().uid);
        }
        if(null == context || uid == null) return;
        AsyncUtils.getUser(context, uid, new JsonResponseHandler<UserResponse>(UserResponse.class) {
            @Override
            public void onSuccess(UserResponse result) {
                super.onSuccess(result);
                if (result.status.equals("ok")) {
                    LoginManager.saveUser(context, result.data);
                    Auth auth = new Auth();
                    auth.uid = String.valueOf(result.data.uid);
                    auth.token = result.data.token;
                    LoginManager.saveAuth(context, auth);
                    ui.showUser(result.data);
                }
                if (null != result.message) {
                    ToastUtils.show(context, result.message);
                }
            }
        });
    }

    /**
     * 签到
     * @param context
     * @param ui
     */
    public void sign(final Activity context, final MeUi ui) {
        if(null == context){
            return;
        }
        AsyncUtils.sign(context, new JsonResponseHandler<SignResponse>(SignResponse.class){
            @Override
            public void onSuccess(SignResponse result) {
                super.onSuccess(result);
                if(null != result) {
                    ToastUtils.show(context, result.message);
                }

                if(result.status.equalsIgnoreCase("ok")) {
                    ui.setSignEnable(false);
                    MyDialog logoutDialog = new MyDialog(context, result.message);
                    logoutDialog.setMessage(String.format(context.getString(R.string.warn_sign_success), result.data.add_credits));
                    logoutDialog.setPositiveButton(R.string.str_sure, new OnPositiveClickListener() {
                        @Override
                        public void onClick() {

                        }
                    });
                    logoutDialog.show();
                }
            }
        });
    }
}
