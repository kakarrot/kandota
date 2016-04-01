package com.xulee.kandota.mvp.presenter;

import android.app.Activity;

import com.liuguangqiang.android.mvp.Presenter;
import com.xulee.kandota.app.MyApplication;
import com.xulee.kandota.mvp.model.MovieModel;
import com.xulee.kandota.mvp.ui.MovieUi;
import com.xulee.kandota.mvp.ui.MovieUiCallback;

import javax.inject.Inject;

/**
 * Created by LX on 2016/1/27.
 */
public class MoviePresenter extends Presenter<MovieUi, MovieUiCallback> {

    private Activity mContext;

    @Inject
    MovieModel movieModel;

    public MoviePresenter(Activity context, MovieUi ui) {
        super(ui);
        this.mContext = context;
        MyApplication.from(context).inject(this);
    }

    @Override
    protected void populateUi(MovieUi ui) {

    }

    @Override
    protected MovieUiCallback createUiCallback(MovieUi ui) {
        return null;
    }
}
