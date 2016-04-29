package com.xulee.kandota.mvp.ui;

import com.liuguangqiang.android.mvp.BaseUi;
import com.xulee.kandota.entity.UserResponse;

/**
 * Created by LX on 2016/4/28.
 */
public interface MeUi extends BaseUi<MeUiCallback> {
    void showUser(UserResponse.User user);
    void setSignEnable(boolean isEnable);
}
