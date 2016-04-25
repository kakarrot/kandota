package com.xulee.kandota.mvp.model;

import android.content.Context;

import com.xulee.kandota.act.ShakeActivity;
import com.xulee.kandota.mvp.ui.ShakeUi;
import com.xulee.kandota.utils.ApiUtils;

import javax.inject.Inject;

/**
 * Created by LX on 2016/4/18.
 */
public class ShakeModel {

    @Inject
    public ShakeModel() {
    }

    public void shake(Context context, ShakeUi ui, int shakeType){
        String url = ApiUtils.getShakeScoreParams(context);
        switch (shakeType){
            case ShakeActivity.TYPE_SHAKE_SCORE:
                break;
            case ShakeActivity.TYPE_SHAKE_LIVE:
                break;
            case ShakeActivity.TYPE_SHAKE_PRIZE:
                break;
        }
    }

}
