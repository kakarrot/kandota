package com.xulee.kandota.module;

import com.xulee.kandota.mvp.presenter.LegalPresenter;
import com.xulee.kandota.mvp.presenter.MainPresenter;
import com.xulee.kandota.mvp.presenter.MoviePresenter;
import com.xulee.kandota.mvp.presenter.AuthorPresenter;

import dagger.Module;

/**
 * Created by Eric on 15/5/12.
 */
@Module(
        injects = {
                MoviePresenter.class,
                MainPresenter.class,
                LegalPresenter.class,
                AuthorPresenter.class
        }
)
public class PresenterModule {
}
