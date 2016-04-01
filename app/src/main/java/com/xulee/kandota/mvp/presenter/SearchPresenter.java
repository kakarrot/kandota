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

import android.content.Context;

import com.liuguangqiang.android.mvp.Presenter;
import com.xulee.kandota.app.MyApplication;
import com.xulee.kandota.entity.NewsResponse;
import com.xulee.kandota.mvp.model.MovieModel;
import com.xulee.kandota.mvp.ui.SearchUi;
import com.xulee.kandota.mvp.ui.SearchUiCallback;
import com.xulee.kandota.utils.SkipUtils;

import javax.inject.Inject;

/**
 * Created by Eric on 15/5/21.
 */
public class SearchPresenter extends Presenter<SearchUi, SearchUiCallback> {

    @Inject
    MovieModel mBookModel;

    Context mContext;

    public SearchPresenter(Context context, SearchUi ui) {
        super(ui);
        MyApplication.from(context).inject(this);
        mContext = context;
    }

    @Override
    protected void populateUi(SearchUi searchUi) {

    }

    @Override
    protected SearchUiCallback createUiCallback(final SearchUi searchUi) {
        return new SearchUiCallback() {
            @Override
            public void getMovies(int pageindex, String keyword) {
                mBookModel.getMovies(mContext, pageindex, keyword, searchUi);
            }

            @Override
            public void onItemClick(NewsResponse book) {
            }
        };
    }
}
