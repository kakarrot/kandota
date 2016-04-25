/*
 * Copyright 2015 qiwenge
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.xulee.kandota.mvp.presenter;

import android.app.Activity;

import com.liuguangqiang.android.mvp.Presenter;
import com.xulee.kandota.app.MyApplication;
import com.xulee.kandota.mvp.model.MainModel;
import com.xulee.kandota.mvp.ui.MainUi;
import com.xulee.kandota.mvp.ui.MainUiCallback;

import javax.inject.Inject;

/**
 * Created by Eric on 15/5/25.
 */
public class MainPresenter extends Presenter<MainUi, MainUiCallback> {

    private Activity mContext;

    @Inject
    MainModel mMainModel;

    public MainPresenter(Activity context, MainUi ui) {
        super(ui);
        mContext = context;
        MyApplication.from().inject(this);
        mMainModel.checkUpdate(mContext);
    }

    @Override
    protected void populateUi(MainUi mainUi) {
        mainUi.showMenu(mMainModel.getMenu(mContext));
    }

    @Override
    protected MainUiCallback createUiCallback(MainUi mainUi) {
        return null;
    }
}
