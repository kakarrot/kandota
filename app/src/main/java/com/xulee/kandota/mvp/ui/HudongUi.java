package com.xulee.kandota.mvp.ui;

import com.liuguangqiang.android.mvp.BaseRequestUi;
import com.xulee.kandota.entity.HudongResponse;
import com.xulee.kandota.entity.NewsResponse;

import java.util.List;

/**
 * Created by LX on 2016/1/27.
 */
public interface HudongUi extends BaseRequestUi<HudongResponse, HudongUiCallback> {

    void showBanner(List<HudongResponse.DataEntity> dataEntityList);
    void showThemeList(List<HudongResponse.ThemeEntity> themeEntities);
}
