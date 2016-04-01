package com.xulee.kandota.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.liuguangqiang.framework.utils.PreferencesUtils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.xulee.kandota.R;

/**
 * ImageLoaderUtils
 * <p/>
 * Created by Eric on 2014-7-7
 */
public class ImageLoaderUtils {

    /**
     * 加载器状态。1:开启；0:关闭
     */
    private static int LOADER_STATUS = 1;

    private static int STATUS_OPENED = 1;

    private static int STATUS_CLOSED = 0;

    private static final String SAVE_NAME = "LoaderUtils";

    private static final String SAVE_KEY = "LoaderStatus";

    private static boolean wifiEnable = true;

    /**
     * 初始化。
     *
     * @param context
     */
    public static void init(Context context) {
        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(context));
        LOADER_STATUS = PreferencesUtils.getInt(context, SAVE_NAME, SAVE_KEY, STATUS_OPENED);
    }

    /**
     * 设置wifi是否可用。
     *
     * @param b
     */
    public static void setWifiEnable(boolean b) {
        wifiEnable = b;
    }

    /**
     * 加载器是否打开。
     *
     * @return
     */
    public static boolean isOpen() {
        return LOADER_STATUS == STATUS_OPENED;
    }


    /**
     * 显示图片。
     *
     * @param url
     * @param iv
     * @param mOptions
     */
    public static void display(String url, ImageView iv, DisplayImageOptions mOptions) {
        if (wifiEnable || LOADER_STATUS == STATUS_OPENED) {
            if (ImageLoader.getInstance().isInited())
                ImageLoader.getInstance().displayImage(url, iv, mOptions);
        }
    }

    public static void display(String url, ImageView iv, DisplayImageOptions mOptions, ImageLoadingListener listener) {
        if (wifiEnable || LOADER_STATUS == STATUS_OPENED) {
            if (ImageLoader.getInstance().isInited())
                ImageLoader.getInstance().displayImage(url, iv, mOptions, listener);
        }
    }

    public static void display(String url, ImageView iv) {
        if (wifiEnable || LOADER_STATUS == STATUS_OPENED) {
            if (ImageLoader.getInstance().isInited())
                ImageLoader.getInstance().displayImage(url, iv, createOptions(R.drawable.icon_place_holder));
        }
    }

    /**
     * 关闭图片加载
     */
    public static void closeLoader(Context context) {
        LOADER_STATUS = STATUS_CLOSED;
        PreferencesUtils.putInt(context, SAVE_NAME, SAVE_KEY, STATUS_CLOSED);
    }

    /**
     * 开启图片加载
     */
    public static void openLoader(Context context) {
        LOADER_STATUS = STATUS_OPENED;
        PreferencesUtils.putInt(context, SAVE_NAME, SAVE_KEY, STATUS_OPENED);
    }

    public static DisplayImageOptions createOptions(int imgLoading) {
        return new DisplayImageOptions.Builder().bitmapConfig(Bitmap.Config.RGB_565)
                .showImageOnLoading(imgLoading).cacheInMemory(true).cacheOnDisk(true).build();
    }

    public static DisplayImageOptions createOptions(int imgLoading, int radiusPixels) {
        return new DisplayImageOptions.Builder().bitmapConfig(Bitmap.Config.RGB_565)
                .showImageOnLoading(imgLoading).imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)
                .cacheInMemory(true).displayer(new RoundedBitmapDisplayer(radiusPixels))
                .cacheOnDisk(true).build();
    }

    public static void pause(){
        if (wifiEnable || LOADER_STATUS == STATUS_OPENED) {
            if (ImageLoader.getInstance().isInited())
                ImageLoader.getInstance().pause();
        }
    }

    public static void resume(){
        if (wifiEnable || LOADER_STATUS == STATUS_OPENED) {
            if (ImageLoader.getInstance().isInited())
                ImageLoader.getInstance().resume();
        }
    }

}
