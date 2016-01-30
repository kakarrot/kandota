package com.xulee.kandota.app;

import android.app.Application;
import android.content.Context;

import com.liuguangqiang.framework.utils.DeviceId;
import com.liuguangqiang.framework.utils.Logs;
import com.liuguangqiang.framework.utils.MetaDataUtils;
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
public class DotaApplication extends Application {

    private ObjectGraph mObjectGraph;

    private static DotaApplication app;
    public static DotaApplication from(Context context) {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.app = this;
//        disableCheckUpdate();
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

    /**
     * 禁用更新功能
     * <p>GooglePlay禁止开发者在应用中检查版本更新</p>
     */
    private void disableCheckUpdate() {
        String channel = MetaDataUtils.getMetaData(this, "UMENG_CHANNEL");
        Logs.i("channel:" + channel);
        Constants.DISABLE_UPDATE = channel.equals("googleplay");
    }

}
