package com.xulee.kandota.mvp.presenter;

import android.app.Activity;

import com.liuguangqiang.android.mvp.Presenter;
import com.liuguangqiang.framework.utils.Logs;
import com.nostra13.universalimageloader.utils.L;
import com.xulee.kandota.act.BrowserActivity;
import com.xulee.kandota.app.MyApplication;
import com.xulee.kandota.entity.HudongResponse;
import com.xulee.kandota.mvp.model.HudongModel;
import com.xulee.kandota.mvp.ui.HudongUi;
import com.xulee.kandota.mvp.ui.HudongUiCallback;
import com.xulee.kandota.utils.SkipUtils;

import javax.inject.Inject;

/**
 * created by LX on 2016/1/27.
 */
public class HudongPresenter extends Presenter<HudongUi, HudongUiCallback> {

    private Activity mContext;

    @Inject
    HudongModel hudongModel;

    public HudongPresenter(Activity context, HudongUi ui) {
        super(ui);
        this.mContext = context;
        MyApplication.from().inject(this);
    }

    @Override
    protected void populateUi(HudongUi ui) {
        hudongModel.getHudongCache(mContext, ui);
    }

    @Override
    protected HudongUiCallback createUiCallback(final HudongUi ui) {
        return new HudongUiCallback() {
            @Override
            public void getHudong() {
                Logs.i("开始获取数据");
                hudongModel.getHudongCache(mContext, ui);
            }

            @Override
            public void onBannerItemClick(HudongResponse.DataEntity dataEntity) {
                SkipUtils.skipToBrowse(mContext, dataEntity.title, dataEntity.link_content);
            }

            @Override
            public void onThemeItemClick(HudongResponse.ThemeEntity themeEntity) {
                SkipUtils.skipToActivity(mContext, themeEntity.func);
            }
        };
    }
}
