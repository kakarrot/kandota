package com.xulee.kandota.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;

import com.xulee.kandota.utils.ImageLoaderUtils;


/**
 * Wifi状态监听.
 * <p/>
 * Created by Eric on 2014-7-22
 */
public class WifiStatusChangeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(WifiManager.WIFI_STATE_CHANGED_ACTION)) {
            int state = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, 0);
            System.out.println("state:" + state);
            switch (state) {
                case WifiManager.WIFI_STATE_ENABLED:
                    System.out.println("wifi已经打开");
                    ImageLoaderUtils.setWifiEnable(true);
                    break;
                case WifiManager.WIFI_STATE_DISABLED:
                    System.out.println("wifi已经关闭");
                    ImageLoaderUtils.setWifiEnable(false);
                    break;
                case WifiManager.WIFI_STATE_DISABLING:
                    System.out.println("正在关闭wifi");
                    ImageLoaderUtils.setWifiEnable(false);
                    break;
                default:
                    break;
            }
        }
    }
}
