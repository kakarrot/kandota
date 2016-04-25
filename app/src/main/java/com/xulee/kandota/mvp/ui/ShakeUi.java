package com.xulee.kandota.mvp.ui;

import com.liuguangqiang.android.mvp.BaseUi;

/**
 * Created by LX on 2016/4/18.
 */
public interface ShakeUi extends BaseUi<ShakeUiCallback>{
    void showShake(int shakeType);
    int getShakeType();
}
