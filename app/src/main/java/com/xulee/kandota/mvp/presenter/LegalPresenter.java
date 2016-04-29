package com.xulee.kandota.mvp.presenter;

import android.content.Context;


import com.liuguangqiang.android.mvp.Presenter;
import com.xulee.kandota.app.MyApplication;
import com.xulee.kandota.mvp.model.LegalModel;
import com.xulee.kandota.mvp.ui.LegalUi;
import com.xulee.kandota.mvp.ui.LegalUiCallback;

import javax.inject.Inject;

/**
 * Created by Eric on 15/5/12.
 */
public class LegalPresenter extends Presenter<LegalUi, LegalUiCallback> {

    @Inject
    LegalModel mLegalModel;

    public LegalPresenter(Context context, LegalUi ui) {
        super(ui);
        MyApplication.from().inject(this);
    }

    @Override
    protected void populateUi(LegalUi legalUi) {
        mLegalModel.getLegal(legalUi);
    }

    @Override
    protected LegalUiCallback createUiCallback(LegalUi legalUi) {
        return null;
    }
}
