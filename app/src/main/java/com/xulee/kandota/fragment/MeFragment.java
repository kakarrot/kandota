package com.xulee.kandota.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.liuguangqiang.android.mvp.Presenter;
import com.liuguangqiang.framework.utils.DisplayUtils;
import com.liuguangqiang.framework.utils.IntentUtils;
import com.liuguangqiang.framework.utils.StringUtils;
import com.liuguangqiang.framework.utils.ToastUtils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.xulee.kandota.R;
import com.xulee.kandota.act.FeedbackActivity;
import com.xulee.kandota.act.SettingActivity;
import com.xulee.kandota.base.BaseFragment;
import com.xulee.kandota.entity.UserResponse;
import com.xulee.kandota.mvp.presenter.MePresenter;
import com.xulee.kandota.mvp.ui.MeUi;
import com.xulee.kandota.mvp.ui.MeUiCallback;
import com.xulee.kandota.utils.ImageLoaderUtils;
import com.xulee.kandota.utils.LoginManager;
import com.xulee.kandota.utils.SkipUtils;

import butterknife.Bind;
import butterknife.OnClick;


public class MeFragment extends BaseFragment implements MeUi {

    @Bind(R.id.iv_avatar)
    ImageView ivAvatar;
    @Bind(R.id.tv_username)
    TextView tvUserName;
    @Bind(R.id.tv_level)
    TextView tvLevel;

    private MeUiCallback mCallback;

    @Override
    protected int getContentView() {
        return R.layout.fragment_me;
    }

    @Override
    public void setUiCallback(MeUiCallback callback) {
        mCallback = callback;
    }

    @Override
    public Presenter setPresenter() {
        return new MePresenter(getActivity(), this);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (LoginManager.isLogout && !LoginManager.isLogin()) {
            LoginManager.isLogout = false;
            clearUser();
        }
        showUser();
    }

    @OnClick({R.id.tv_set, R.id.tv_feed_back, R.id.tv_user_info, R.id.layout_user,
            R.id.tv_download, R.id.tv_exchange})
    void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.tv_set:
                startActivity(SettingActivity.class);
                break;
            case R.id.tv_feed_back:
                startActivity(FeedbackActivity.class);
                break;
            case R.id.tv_user_info:
                skipToMarket();
                break;
            case R.id.layout_user:
                if (!LoginManager.isLogin())
                    SkipUtils.skipToLogin(getActivity());
                break;
        }
    }

    private void skipToMarket() {
        try {
            IntentUtils.skipToMarket(getActivity());
        } catch (Exception ex) {
            ToastUtils.show(getActivity(), getString(R.string.error_not_find_market));
        }
    }

    private void showUser() {
        if (LoginManager.isLogin()) {
            showUser(LoginManager.getUser());
        }
    }

    @Override
    public void showUser(UserResponse.User user) {
        if (user != null) {
            tvUserName.setText(user.nickname);
            if (!StringUtils.isEmptyOrNull(user.avator)) {
                DisplayImageOptions options = ImageLoaderUtils.createOptions(R.drawable.ic_default_avatar,
                        DisplayUtils.dip2px(getActivity(), 70));
                ImageLoader.getInstance().displayImage(user.avator, ivAvatar, options);
            }
            String level = user.credits;
            tvLevel.setText(String.format(getString(R.string.str_credit), level));
            tvLevel.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setSignEnable(boolean isEnable) {

    }

    private void clearUser() {
        tvUserName.setText(R.string.str_login_tips);
        tvLevel.setText(R.string.login_desc);
        ivAvatar.setImageResource(R.drawable.ic_default_avatar);
    }

}
