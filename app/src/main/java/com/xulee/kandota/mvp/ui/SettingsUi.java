package com.xulee.kandota.mvp.ui;


import com.liuguangqiang.android.mvp.BaseUi;

/**
 * Created by Eric on 15/5/7.
 */
public interface SettingsUi extends BaseUi<SettingUiCallback> {

    void setVersionName(String versionName);

    void setLogoutVisibility(int visibility);

}
