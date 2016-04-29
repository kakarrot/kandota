package com.xulee.kandota.mvp.presenter;

import android.app.Activity;

import com.liuguangqiang.android.mvp.Presenter;
import com.xulee.kandota.app.MyApplication;
import com.xulee.kandota.mvp.model.MeModel;
import com.xulee.kandota.mvp.ui.MeUi;
import com.xulee.kandota.mvp.ui.MeUiCallback;
import com.xulee.kandota.utils.LoginManager;

import javax.inject.Inject;

/**
 * Created by LX on 2016/4/28.
 */
public class MePresenter extends Presenter<MeUi, MeUiCallback>{

    private Activity mContext;

    @Inject
    MeModel meModel;

    public MePresenter(Activity context, MeUi ui) {
        super(ui);
        this.mContext = context;
        MyApplication.from().inject(this);
    }

    @Override
    protected void populateUi(MeUi ui) {
        if (LoginManager.isLogin()) {
            meModel.getUser(mContext, ui);
        }
    }

    @Override
    protected MeUiCallback createUiCallback(final MeUi ui) {
        return new MeUiCallback() {
            @Override
            public void sign() {
                meModel.sign(mContext, ui);
            }
        };
    }
}
