package com.xulee.kandota.mvp.ui;

import com.liuguangqiang.android.mvp.BaseUi;
import com.xulee.kandota.entity.MainMenuItem;

import java.util.List;

/**
 * Created by Eric on 15/5/9.
 */
public interface MainUi extends BaseUi<MainUiCallback> {

    void showMenu(List<MainMenuItem> list);

}
