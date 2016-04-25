package com.xulee.kandota.module;

import com.xulee.kandota.mvp.presenter.FeedbackPresenter;
import com.xulee.kandota.mvp.presenter.HudongPresenter;
import com.xulee.kandota.mvp.presenter.LegalPresenter;
import com.xulee.kandota.mvp.presenter.LoginPrePresenter;
import com.xulee.kandota.mvp.presenter.LoginPresenter;
import com.xulee.kandota.mvp.presenter.MainPresenter;
import com.xulee.kandota.mvp.presenter.SettingsPresenter;
import com.xulee.kandota.mvp.presenter.ShakePresenter;

import dagger.Module;

/**
 * Created by Eric on 15/5/12.
 */
@Module(
        injects = {
                MainPresenter.class,
                LegalPresenter.class,
                FeedbackPresenter.class,
                SettingsPresenter.class,
                HudongPresenter.class,
                ShakePresenter.class,
                LoginPrePresenter.class,
                LoginPresenter.class
        }
)
public class PresenterModule {
}
