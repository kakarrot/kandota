package com.xulee.kandota.fragment;

import android.graphics.Bitmap;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.liuguangqiang.android.mvp.Presenter;
import com.liuguangqiang.framework.utils.DisplayUtils;
import com.liuguangqiang.framework.utils.StringUtils;
import com.liuguangqiang.framework.utils.ToastUtils;
import com.xulee.kandota.R;
import com.xulee.kandota.base.BaseFragment;
import com.xulee.kandota.listeners.LoginPreListener;
import com.xulee.kandota.listeners.OnPositiveClickListener;
import com.xulee.kandota.mvp.presenter.LoginPrePresenter;
import com.xulee.kandota.mvp.ui.LoginPreUi;
import com.xulee.kandota.mvp.ui.LoginPreUiCallback;
import com.xulee.kandota.ui.dialogs.MyDialog;
import com.xulee.kandota.utils.SkipUtils;
import com.xulee.kandota.utils.VerifyCodeUtil;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 输入图片验证码页面
 */
public class LoginPreFragment extends BaseFragment implements LoginPreUi {

    @Bind(R.id.et_username)
    EditText et_username;
    @Bind(R.id.et_vcode)
    EditText et_vcode;
    @Bind(R.id.iv_vcode)
    ImageView iv_vcode;
    @Bind(R.id.btn_get_sms_code)
    Button btnGetSmsCode;

    private LoginPreUiCallback mCallback;
    private LoginPreListener loginListener;
    private String mobile;
    private String mVcode; //图片验证码
    private Bitmap bitmap;
    private boolean hasInit = false;

    @Override
    protected int getContentView() {
        return R.layout.fragment_login_pre;
    }

    @Override
    public Presenter setPresenter() {
        return new LoginPrePresenter(getActivity(), this);
    }

    @Override
    protected void initViews() {
        super.initViews();
        et_username.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    btnGetSmsCode.setEnabled(true);
                } else {
                    btnGetSmsCode.setEnabled(false);
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

    @OnClick({R.id.iv_vcode, R.id.btn_get_sms_code})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_vcode:
                mCallback.getVerifyCode();
                break;
            case R.id.btn_get_sms_code:
                mobile = et_username.getText().toString();
                if (!StringUtils.isMobileNO(mobile)) {
                    ToastUtils.show(getActivity(), R.string.error_invalid_phone);
                    return;
                }
                if (StringUtils.isEmptyOrNull(et_vcode.getText().toString()) ||
                        !mVcode.equals(et_vcode.getText().toString())) {
                    ToastUtils.show(getActivity(), R.string.error_invalid_vcode);
                    return;
                }
                mCallback.getSmsCodeDynamic(mobile, mVcode);
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (bitmap != null && !bitmap.isRecycled()) {
            bitmap.recycle();
            bitmap = null;
        }
        System.gc();
    }

    @Override
    public void setUiCallback(LoginPreUiCallback callback) {
        this.mCallback = callback;
    }

    public void setLoginListener(LoginPreListener listener) {
        this.loginListener = listener;
    }

    @Override
    public void setImgVcodeEnable(boolean bool) {
        if (null != iv_vcode) {
            iv_vcode.setEnabled(bool);
        }
    }

    /**
     * 更新图片验证码
     *
     * @param vcode
     */
    @Override
    public void setBitmap(String vcode) {
        mVcode = vcode;
        if (bitmap != null && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
        bitmap = VerifyCodeUtil.createBitmap(DisplayUtils.dip2px(getActivity(), 100),
                DisplayUtils.dip2px(getActivity(), 50), vcode, DisplayUtils.sp2px(getActivity(), 24));
        if (null != bitmap) {
            iv_vcode.setImageBitmap(bitmap);
        }
    }

    @Override
    public void setGetSmsCodeButtonEnable(boolean bool) {
        if (null != btnGetSmsCode) btnGetSmsCode.setEnabled(bool);
    }

    @Override
    public void onGetSmsCodeSuccess() {
        if (null != loginListener) {
            loginListener.onNext(mobile, mVcode);
        }
    }

    @Override
    public void showRegisterDialog() {
        MyDialog logoutDialog = new MyDialog(getActivity(), R.string.warn_register);
        logoutDialog.setMessage(R.string.warn_register_content);
        logoutDialog.setPositiveButton(R.string.str_sure, new OnPositiveClickListener() {
            @Override
            public void onClick() {
                SkipUtils.skipToRegister(getActivity());
                getActivity().finish();
            }
        });
        logoutDialog.show();
    }
}
