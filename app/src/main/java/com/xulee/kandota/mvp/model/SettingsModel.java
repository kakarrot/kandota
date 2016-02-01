package com.xulee.kandota.mvp.model;

import android.content.Context;

import com.liuguangqiang.framework.utils.AppUtils;

import javax.inject.Inject;

/**
 * Created by Eric on 15/5/7.
 */
public class SettingsModel {

    @Inject
    public SettingsModel() {
    }

    public String getVersionName(Context context) {
        return AppUtils.getVersionName(context);
    }

}
