package com.xulee.kandota.mvp.presenter;

import android.app.Activity;

import com.liuguangqiang.android.mvp.Presenter;
import com.xulee.kandota.app.DotaApplication;
import com.xulee.kandota.mvp.model.AuthorModel;
import com.xulee.kandota.mvp.ui.AuthorUi;
import com.xulee.kandota.mvp.ui.AuthorUiCallback;

import javax.inject.Inject;

/**
 * Created by LX on 2016/1/27.
 */
public class AuthorPresenter extends Presenter<AuthorUi, AuthorUiCallback> {

    private Activity mContext;

    @Inject
    AuthorModel movieModel;

    public AuthorPresenter(Activity context, AuthorUi ui) {
        super(ui);
        this.mContext = context;
        DotaApplication.from(context).inject(this);
    }

    @Override
    protected void populateUi(AuthorUi ui) {

    }

    @Override
    protected AuthorUiCallback createUiCallback(AuthorUi ui) {
        return null;
    }
}
