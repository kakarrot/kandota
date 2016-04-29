package com.xulee.kandota.act;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.liuguangqiang.android.mvp.Presenter;
import com.liuguangqiang.framework.utils.StringUtils;
import com.liuguangqiang.framework.utils.ToastUtils;
import com.xulee.kandota.R;
import com.xulee.kandota.base.BaseActivity;
import com.xulee.kandota.listeners.OnPositiveClickListener;
import com.xulee.kandota.mvp.presenter.RegisterPresenter;
import com.xulee.kandota.mvp.ui.RegisterUi;
import com.xulee.kandota.mvp.ui.RegisterUiCallback;
import com.xulee.kandota.ui.dialogs.MyDialog;
import com.xulee.kandota.utils.SkipUtils;
import butterknife.Bind;
import butterknife.OnClick;

/**
 * 注册
 * 有一个隐匿的请求先获取图片验证码，再根据图片中数字验证码去获取短信验证码（坑~）
 */
public class RegisterActivity extends BaseActivity implements RegisterUi {

    private static final int millisInFuture = 60 * 1000;
    private static final int countDownInterval = 1000;

    @Bind(R.id.et_mobile)
    EditText etMobile;
    @Bind(R.id.et_sms_code)
    EditText etSmsCode;
    @Bind(R.id.btn_get_sms_code)
    Button btnGetSmsCode;
    @Bind(R.id.et_invite_code)
    EditText etInviteCode;
    @Bind(R.id.btn_register)
    Button btnRegister;

    private RegisterUiCallback mCallback;

    //倒计时
    private CountDownTimer countDownTimer = new CountDownTimer(millisInFuture, countDownInterval) {
        @Override
        public void onTick(long l) {
            String text = String.format(getString(R.string.prompt_re_get_time), String.valueOf(l / 1000));
            btnGetSmsCode.setText(text.toLowerCase());
            btnGetSmsCode.setEnabled(false);
        }

        @Override
        public void onFinish() {
            btnGetSmsCode.setText(R.string.prompt_get_verify_code);
            btnGetSmsCode.setEnabled(true);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initViews();
        setTitle(R.string.action_register);
    }

    public void initViews(){
        etMobile.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    btnRegister.setEnabled(true);
                } else {
                    btnRegister.setEnabled(false);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });
    }

    @Override
    public Presenter setPresenter() {
        return new RegisterPresenter(this, this);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_register;
    }

    @Override
    public void setRegisterButtonEnable(boolean enable) {
        btnRegister.setEnabled(enable);
    }

    @Override
    public void setGetSmsButtonEnable(boolean enable) {
        btnGetSmsCode.setEnabled(enable);
    }

    @Override
    public void setCountDownTimerStart() {
        countDownTimer.start();
    }

    @Override
    public void showLoginDialog() {
        MyDialog logoutDialog = new MyDialog(this, R.string.warn_login);
        logoutDialog.setMessage(R.string.warn_login_content);
        logoutDialog.setPositiveButton(R.string.str_sure, new OnPositiveClickListener() {
            @Override
            public void onClick() {
                SkipUtils.skipToLogin(RegisterActivity.this);
                finish();
            }
        });
        logoutDialog.show();
    }

    @Override
    public void setUiCallback(RegisterUiCallback callback) {
        mCallback = callback;
    }

    @OnClick({R.id.btn_get_sms_code, R.id.btn_register})
    void click(View v){
        switch (v.getId()){
            case R.id.btn_get_sms_code:
                String mobile = etMobile.getText().toString().trim();
                if(StringUtils.isEmptyOrNull(mobile) || !StringUtils.isMobileNO(mobile)){
                    ToastUtils.show(this, R.string.error_invalid_phone);
                    return;
                }
                mCallback.getSmsCode(this, mobile, this);
                break;
            case R.id.btn_register:
                String mobile2 = etMobile.getText().toString().trim();
                if(StringUtils.isEmptyOrNull(mobile2) || !StringUtils.isMobileNO(mobile2)){
                    ToastUtils.show(this, R.string.error_invalid_phone);
                    return;
                }
                String smsCode = etSmsCode.getText().toString().trim();
                if(StringUtils.isEmptyOrNull(smsCode)){
                    ToastUtils.show(this, R.string.error_invalid_smscode);
                    return;
                }

                String inviteCode = etInviteCode.getText().toString().trim();
                mCallback.register(this, mobile2, smsCode, inviteCode, this);
                break;
        }
    }
}
