package com.xulee.kandota.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liuguangqiang.android.mvp.Presenter;
import com.liuguangqiang.framework.utils.IntentUtils;
import com.liuguangqiang.framework.utils.ToastUtils;
import com.xulee.kandota.R;
import com.xulee.kandota.act.LegalActivity;
import com.xulee.kandota.async.AsyncCheckUpdate;
import com.xulee.kandota.base.BaseFragment;
import com.xulee.kandota.constant.Constants;
import com.xulee.kandota.listeners.OnPositiveClickListener;
import com.xulee.kandota.mvp.presenter.SettingsPresenter;
import com.xulee.kandota.mvp.ui.SettingUiCallback;
import com.xulee.kandota.mvp.ui.SettingsUi;
import com.xulee.kandota.ui.dialogs.MyDialog;
import com.xulee.kandota.utils.ImageLoaderUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingFragment extends BaseFragment implements SettingsUi {

    @Bind(R.id.set_tv_update)
    TextView tvCheckUpdate;

    @Bind(R.id.tv_version_name)
    TextView tvVersionName;

    @Bind(R.id.layout_set_user)
    LinearLayout layoutUser;

    @Bind(R.id.iv_save_model)
    ImageView ivSaveModel;

    private MyDialog logoutDialog;
    private boolean selectSaveModel = false;

    SettingUiCallback mCallback;

    @Override
    protected int getContentView() {
        return R.layout.activity_setting;
    }

    @Override
    public Presenter setPresenter() {
        return new SettingsPresenter(getActivity(), this);
    }

    @Override
    public void setUiCallback(SettingUiCallback settingsUiCallback) {
        mCallback = settingsUiCallback;
    }

    @Override
    protected void initViews() {
        if (!ImageLoaderUtils.isOpen()) {
            setCheckbox(ivSaveModel, true);
            selectSaveModel = true;
        }

        if (Constants.DISABLE_UPDATE) {
            tvCheckUpdate.setVisibility(View.GONE);
        }
    }

    /**
     * 切换流量模式
     */
    @OnClick(R.id.iv_save_model)
    public void switchSaveModel() {
        if (selectSaveModel) {
            setCheckbox(ivSaveModel, false);
            ImageLoaderUtils.openLoader(getActivity());
        } else {
            setCheckbox(ivSaveModel, true);
            ImageLoaderUtils.closeLoader(getActivity());
        }
        selectSaveModel = !selectSaveModel;
    }

    @OnClick(R.id.set_tv_rating)
    public void skipToMarket() {
        try {
            IntentUtils.skipToMarket(getActivity());
        } catch (Exception ex) {
            ToastUtils.show(getActivity(), getString(R.string.error_not_find_market));
        }
    }

    @OnClick(R.id.set_tv_legal)
    public void skipToLegal() {
        startActivity(LegalActivity.class);
    }

    @OnClick(R.id.set_tv_update)
    public void chkVersionUpdate() {
        new AsyncCheckUpdate(getActivity()).checkUpdate();
    }

    @OnClick(R.id.set_tv_logout)
    public void showLogoutDialog() {
        logoutDialog = new MyDialog(getActivity(), R.string.set_logout_title);
        logoutDialog.setMessage(R.string.set_logout_msg);
        logoutDialog.setPositiveButton(R.string.str_sure, new OnPositiveClickListener() {
            @Override
            public void onClick() {
                mCallback.logout();
            }
        });
        logoutDialog.show();
    }

    private void setCheckbox(ImageView iv, boolean ischecked) {
        if (ischecked) {
            iv.setBackgroundResource(R.drawable.icon_switch_on);
        } else {
            iv.setBackgroundResource(R.drawable.icon_switch_off);
        }
    }

    @Override
    public void setVersionName(String versionName) {
        tvVersionName.setText(versionName);
    }

    @Override
    public void setLogoutVisibility(int visibility) {
        layoutUser.setVisibility(visibility);
    }
}


