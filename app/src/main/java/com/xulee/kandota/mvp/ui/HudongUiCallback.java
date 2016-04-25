package com.xulee.kandota.mvp.ui;

import com.xulee.kandota.entity.HudongResponse;

/**
 * Created by LX on 2016/1/27.
 */
public interface HudongUiCallback {

    void getHudong();

    void onBannerItemClick(HudongResponse.DataEntity dataEntity);

    void onThemeItemClick(HudongResponse.ThemeEntity themeEntity);
}
