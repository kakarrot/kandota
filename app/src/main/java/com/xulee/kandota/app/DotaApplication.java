package com.xulee.kandota.app;

import android.app.Activity;
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
import com.youku.player.YoukuPlayerBaseConfiguration;

import dagger.ObjectGraph;

/**
 * Created by LX on 2016/1/28.
 */
public class DotaApplication extends Application {

    private ObjectGraph mObjectGraph;

    private static DotaApplication app;
    public static YoukuPlayerBaseConfiguration configuration;

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
        initYouKuConifg();
    }

    private void initYouKuConifg() {
        configuration = new YoukuPlayerBaseConfiguration(this) {


            /**
             * 通过覆写该方法，返回“正在缓存视频信息的界面”，
             * 则在状态栏点击下载信息时可以自动跳转到所设定的界面.
             * 用户需要定义自己的缓存界面
             */
            @Override
            public Class<? extends Activity> getCachingActivityClass() {
//                return CachingActivity.class;
                return null;
            }

            /**
             * 通过覆写该方法，返回“已经缓存视频信息的界面”，
             * 则在状态栏点击下载信息时可以自动跳转到所设定的界面.
             * 用户需要定义自己的已缓存界面
             */

            @Override
            public Class<? extends Activity> getCachedActivityClass() {
//                return CachedActivity.class;
                return null;
            }

            /**
             * 配置视频的缓存路径，格式举例： /appname/videocache/
             * 如果返回空，则视频默认缓存路径为： /应用程序包名/videocache/
             *
             */
            @Override
            public String configDownloadPath() {
//                return "/kandota/videocache/";			//举例
                return null;
            }
        };
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
