package com.xulee.kandota.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.liuguangqiang.framework.utils.DisplayUtils;
import com.liuguangqiang.framework.utils.IntentUtils;
import com.liuguangqiang.framework.utils.ToastUtils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.xulee.kandota.R;
import com.xulee.kandota.act.FeedbackActivity;
import com.xulee.kandota.act.SettingActivity;
import com.xulee.kandota.base.BaseFragment;
import com.xulee.kandota.entity.User;
import com.xulee.kandota.entity.UserLevel;
import com.xulee.kandota.listeners.LoginListener;
import com.xulee.kandota.ui.dialogs.LoginDialog;
import com.xulee.kandota.utils.ImageLoaderUtils;
import com.xulee.kandota.utils.LoginManager;
import com.xulee.kandota.utils.http.JHttpClient;
import com.xulee.kandota.utils.http.JsonResponseHandler;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MeFragment extends BaseFragment {

    private final static String LEVEL_FORMAT = "LV.%s (%s/%s)";

    @Bind(R.id.iv_avatar)
    ImageView ivAvatar;
    @Bind(R.id.tv_username)
    TextView tvUserName;
    @Bind(R.id.tv_level)
    TextView tvLevel;

    private LoginDialog loginDialog;

    @Override
    protected int getContentView() {
        return R.layout.fragment_me;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (LoginManager.isLogin()) {
            getUser(LoginManager.getUser().getId());
        }
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

    @OnClick({R.id.tv_set, R.id.tv_feed_back, R.id.tv_rating, R.id.layout_user,
            R.id.tv_download, R.id.tv_caching})
    void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.tv_set:
                startActivity(SettingActivity.class);
                break;
            case R.id.tv_feed_back:
                startActivity(FeedbackActivity.class);
                break;
            case R.id.tv_rating:
                skipToMarket();
                break;
            case R.id.layout_user:
                if (!LoginManager.isLogin())
                    showLoginDialog();
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

    private void showLoginDialog() {
        if (loginDialog == null) {
            loginDialog = new LoginDialog(getActivity());
            loginDialog.setLoginListener(new LoginListener() {
                @Override
                public void onSuccess() {
                    showUser(LoginManager.getUser());
                }
            });
        }
        loginDialog.show();
    }

    private void showUser() {
        if (LoginManager.isLogin()) {
            showUser(LoginManager.getUser());
        }
    }

    private void showUser(User user) {
        if (user != null) {
            tvUserName.setText(user.username);
            DisplayImageOptions options = ImageLoaderUtils.createOptions(R.drawable.ic_default_avatar,
                    DisplayUtils.dip2px(getActivity(), 70));
            ImageLoader.getInstance().displayImage(user.avatar, ivAvatar, options);
            UserLevel level = user.level;
            tvLevel.setText(String.format(LEVEL_FORMAT, level.rank, level.exp, level.next));
            tvLevel.setVisibility(View.VISIBLE);
        }
    }

    private void clearUser() {
        tvUserName.setText(R.string.choose_login_type);
        tvLevel.setText(R.string.login_desc);
        ivAvatar.setImageResource(R.drawable.ic_default_avatar);
    }

    private void getUser(String userId) {
        String url = "";
        JHttpClient.get(getActivity(), url, null, new JsonResponseHandler<User>(User.class, false) {

            @Override
            public void onOrigin(String json) {
            }

            @Override
            public void onSuccess(User result) {
                if (isAdded()) {
                    LoginManager.saveUser(getActivity(), result);
                    showUser(result);
                }
            }

            @Override
            public void onFinish() {

            }
        });
    }

}
