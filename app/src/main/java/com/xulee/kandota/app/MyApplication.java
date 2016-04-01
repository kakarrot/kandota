package com.xulee.kandota.app;

import android.app.Application;
import android.content.Context;

import com.liuguangqiang.framework.utils.DeviceId;
import com.xulee.kandota.constant.Constants;
import com.xulee.kandota.module.AppModule;
import com.xulee.kandota.module.PresenterModule;
import com.xulee.kandota.utils.ImageLoaderUtils;
import com.xulee.kandota.utils.LoginManager;
import com.xulee.kandota.utils.OfflineUtils;
import com.xulee.kandota.utils.ThemeUtils;

import dagger.ObjectGraph;

/**
 * Created by LX on 2016/1/28.
 */
public class MyApplication extends Application {

    private ObjectGraph mObjectGraph;

    private static MyApplication app;

    public static MyApplication from(Context context) {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.app = this;
//        initJPush();
        ImageLoaderUtils.init(getApplicationContext());
        ThemeUtils.initTheme(getApplicationContext());
        LoginManager.init(getApplicationContext());
        initOpenUDID();
        createFolder();
        createObjectsGraph();
    }

    private void createObjectsGraph() {
        mObjectGraph = ObjectGraph.create(
                AppModule.class,
                PresenterModule.class
        );
        mObjectGraph.inject(this);
    }

//    private void initJPush() {
//        JPushInterface.setDebugMode(false);
//        JPushInterface.init(this);
//    }

    private void initOpenUDID() {
        Constants.OEPN_UD_ID = DeviceId.getInstance(this).getDeviceId();
    }

    private void createFolder() {
        OfflineUtils.createOfflineFolder();
    }

    public void inject(Object object) {
        mObjectGraph.inject(object);
    }

}
