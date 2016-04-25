package com.xulee.kandota.fragment;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.liuguangqiang.android.mvp.Presenter;
import com.liuguangqiang.framework.utils.StringUtils;
import com.liuguangqiang.framework.utils.ToastUtils;
import com.xulee.kandota.R;
import com.xulee.kandota.base.BaseFragment;
import com.xulee.kandota.entity.LoginResponse;
import com.xulee.kandota.listeners.LoginListener;
import com.xulee.kandota.mvp.presenter.LoginPresenter;
import com.xulee.kandota.mvp.ui.LoginUi;
import com.xulee.kandota.mvp.ui.LoginUiCallback;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 登录页面
 */
public class LoginFragment extends BaseFragment implements LoginUi {

    public static final String KEY_MOBILE = "key_phone";
    public static final String KEY_VCODE = "key_vcode";
    private static final int millisInFuture = 60 * 1000;
    private static final int countDownInterval = 1000;

    @Bind(R.id.tv_phone)
    TextView tv_phone;
    @Bind(R.id.tv_password)
    TextView tv_password;
    @Bind(R.id.et_password)
    EditText et_password;
    @Bind(R.id.tv_get_verify_code)
    TextView tvGetVerifyCode;
    @Bind(R.id.btn_login)
    Button btn_login;

    private LoginUiCallback mCallback;
    private LoginListener loginListener;
    private String mobile;
    private String vcode;

    //倒计时
    private CountDownTimer countDownTimer = new CountDownTimer(millisInFuture, countDownInterval) {
        @Override
        public void onTick(long l) {
            String text = String.format(getString(R.string.prompt_re_get_time), String.valueOf(l / 1000));
            tvGetVerifyCode.setText(text);
            tvGetVerifyCode.setEnabled(false);
        }

        @Override
        public void onFinish() {
            tvGetVerifyCode.setText(R.string.prompt_get_verify_code);
            tvGetVerifyCode.setEnabled(true);
        }
    };

    public static LoginFragment newInstance(String mobile, String vcode) {
        LoginFragment loginFragment = new LoginFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_MOBILE, mobile);
        bundle.putString(KEY_VCODE, vcode);
        loginFragment.setArguments(bundle);
        return loginFragment;
    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_login;
    }

    @Override
    public Presenter setPresenter() {
        return new LoginPresenter(getActivity(), this);
    }

    public void setLoginListener(LoginListener listener) {
        this.loginListener = listener;
    }

    @Override
    protected void initViews() {
        super.initViews();
        mobile = getArguments().getString(KEY_MOBILE);
        vcode = getArguments().getString(KEY_VCODE);
        if (!StringUtils.isEmptyOrNull(mobile)) {
            tv_phone.setText(mobile);
        }

        et_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    btn_login.setEnabled(true);
                } else {
                    btn_login.setEnabled(false);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });
        countDownTimer.start();
    }

    @Override
    public void onDestroy() {
        countDownTimer.cancel();
        super.onDestroy();
    }

    @Override
    public void setGetSmsCodeEnable(boolean bool) {
        tvGetVerifyCode.setEnabled(bool);
    }

    @Override
    public void setCountDownTimerStart() {
        countDownTimer.start();
    }

    @Override
    public String getMobile() {
        return mobile;
    }

    @Override
    public String getVcode() {
        return vcode;
    }

    @Override
    public void onLoginSuccess(LoginResponse loginResponse) {
        if (null != loginListener) {
            loginListener.onLoginSuccess(loginResponse);
        }
    }

    @Override
    public void setUiCallback(LoginUiCallback callback) {
        this.mCallback = callback;
    }

    @OnClick({R.id.tv_get_verify_code, R.id.btn_login})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_get_verify_code:
                mCallback.getSmsCodeDynamic(mobile, vcode);
                break;
            case R.id.btn_login:
                String smsCode = et_password.getText().toString();
                if (StringUtils.isEmptyOrNull(smsCode)) {
                    ToastUtils.show(getActivity(), R.string.error_invalid_smscode);
                    return;
                }
                mCallback.login(mobile, smsCode);
                break;
        }
    }
}
