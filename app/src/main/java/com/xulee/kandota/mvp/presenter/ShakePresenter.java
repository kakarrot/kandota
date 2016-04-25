package com.xulee.kandota.mvp.presenter;

import android.app.Activity;

import com.liuguangqiang.android.mvp.Presenter;
import com.xulee.kandota.app.MyApplication;
import com.xulee.kandota.mvp.model.ShakeModel;
import com.xulee.kandota.mvp.ui.ShakeUi;
import com.xulee.kandota.mvp.ui.ShakeUiCallback;

import javax.inject.Inject;

/**
 * Created by LX on 2016/4/18.
 */
public class ShakePresenter extends Presenter<ShakeUi, ShakeUiCallback> {

    Activity mContext;

    @Inject
    ShakeModel shakeModel;

    public ShakePresenter(Activity context, ShakeUi ui) {
        super(ui);
        this.mContext = context;
        MyApplication.from().inject(this);
    }

    @Override
    protected void populateUi(ShakeUi ui) {

    }

    @Override
    protected ShakeUiCallback createUiCallback(final ShakeUi ui) {
        return new ShakeUiCallback() {
            @Override
            public void shake() {
                shakeModel.shake(mContext, ui, ui.getShakeType());
            }

            @Override
            public void showShake(int shakeType) {
                ui.showShake(shakeType);
            }
        };
    }
}
