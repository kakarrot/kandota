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
import com.xulee.kandota.async.AsyncUtils;
import com.xulee.kandota.base.BaseFragment;
import com.xulee.kandota.entity.Auth;
import com.xulee.kandota.entity.UserResponse;
import com.xulee.kandota.utils.ImageLoaderUtils;
import com.xulee.kandota.utils.LoginManager;
import com.xulee.kandota.utils.SkipUtils;
import com.xulee.kandota.utils.http.JHttpClient;
import com.xulee.kandota.utils.http.JsonResponseHandler;

import butterknife.Bind;
import butterknife.OnClick;


public class MeFragment extends BaseFragment {

    @Bind(R.id.iv_avatar)
    ImageView ivAvatar;
    @Bind(R.id.tv_username)
    TextView tvUserName;
    @Bind(R.id.tv_level)
    TextView tvLevel;

    @Override
    protected int getContentView() {
        return R.layout.fragment_me;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (LoginManager.isLogin()) {
            getUser(String.valueOf(LoginManager.getUser().uid));
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

    private void showUser(UserResponse.User user) {
        if (user != null) {
            tvUserName.setText(user.nickname);
            DisplayImageOptions options = ImageLoaderUtils.createOptions(R.drawable.ic_default_avatar,
                    DisplayUtils.dip2px(getActivity(), 70));
            ImageLoader.getInstance().displayImage(user.avator, ivAvatar, options);
            String level = user.credits;
            tvLevel.setText(String.format(getString(R.string.str_credit), level));
            tvLevel.setVisibility(View.VISIBLE);
        }
    }

    private void clearUser() {
        tvUserName.setText(R.string.str_login_tips);
        tvLevel.setText(R.string.login_desc);
        ivAvatar.setImageResource(R.drawable.ic_default_avatar);
    }

    private void getUser(String uid) {
        AsyncUtils.getUser(getActivity(), uid, new JsonResponseHandler<UserResponse>(UserResponse.class) {
            @Override
            public void onSuccess(UserResponse result) {
                super.onSuccess(result);
                if(result.status.equals("ok")){
                    LoginManager.saveUser(getActivity(), result.data);
                    Auth auth = new Auth();
                    auth.uid = String.valueOf(result.data.uid);
                    auth.token = result.data.token;
                    LoginManager.saveAuth(getActivity(), auth);
                    showUser(result.data);
                }
                if(null != result.message){
                    ToastUtils.show(getActivity(), result.message);
                }
            }
        });
    }

}
