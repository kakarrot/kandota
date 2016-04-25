package com.xulee.kandota.mvp.presenter;

import android.content.Context;
import android.view.View;


import com.liuguangqiang.android.mvp.Presenter;
import com.xulee.kandota.app.MyApplication;
import com.xulee.kandota.mvp.model.SettingsModel;
import com.xulee.kandota.mvp.ui.SettingUiCallback;
import com.xulee.kandota.mvp.ui.SettingsUi;
import com.xulee.kandota.utils.LoginManager;

import javax.inject.Inject;

/**
 * Created by Eric on 15/5/7.
 */
public class SettingsPresenter extends Presenter<SettingsUi, SettingUiCallback> {

    Context mContext;

    @Inject
    SettingsModel settingsModel;

    public SettingsPresenter(Context context, SettingsUi ui) {
        super(ui);
        this.mContext = context;
        MyApplication.from().inject(this);
    }

    @Override
    protected void populateUi(SettingsUi settingsUi) {
        settingsUi.setVersionName(settingsModel.getVersionName(mContext));

        int visibility = LoginManager.isLogin() ? View.VISIBLE : View.GONE;
        settingsUi.setLogoutVisibility(visibility);
    }

    @Override
    protected SettingUiCallback createUiCallback(final SettingsUi settingsUi) {
        return new SettingUiCallback() {
            @Override
            public void logout() {
                LoginManager.logout(mContext);
                settingsUi.setLogoutVisibility(View.GONE);
            }
        };
    }

}
