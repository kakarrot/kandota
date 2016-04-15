package com.xulee.kandota.module;

import com.xulee.kandota.mvp.presenter.FeedbackPresenter;
import com.xulee.kandota.mvp.presenter.HudongPresenter;
import com.xulee.kandota.mvp.presenter.LegalPresenter;
import com.xulee.kandota.mvp.presenter.MainPresenter;
import com.xulee.kandota.mvp.presenter.MoviePresenter;
import com.xulee.kandota.mvp.presenter.SettingsPresenter;

import dagger.Module;

/**
 * Created by Eric on 15/5/12.
 */
@Module(
        injects = {
                MoviePresenter.class,
                MainPresenter.class,
                LegalPresenter.class,
                FeedbackPresenter.class,
                SettingsPresenter.class,
                HudongPresenter.class
        }
)
public class PresenterModule {
}
