package com.xulee.kandota.async;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;

import com.liuguangqiang.framework.utils.AppUtils;
import com.liuguangqiang.framework.utils.ToastUtils;
import com.xulee.kandota.R;
import com.xulee.kandota.constant.Constants;
import com.xulee.kandota.entity.Configures;
import com.xulee.kandota.listeners.OnPositiveClickListener;
import com.xulee.kandota.ui.dialogs.MyDialog;
import com.xulee.kandota.utils.ApiUtils;
import com.xulee.kandota.utils.DialogUtils;
import com.xulee.kandota.utils.http.JHttpClient;
import com.xulee.kandota.utils.http.JsonResponseHandler;

/**
 * 检查版本更新。
 * <p/>
 * Created by applemac on 14-8-3.
 */
public class AsyncCheckUpdate {

    private Activity mAct;


    private boolean onlyCheck = false;

    private AsyncCheckUpdate() {
    }

    public AsyncCheckUpdate(Activity acitivity) {
        this.mAct = acitivity;
    }

    /**
     * 设置只检查模式，不提醒，当前已经是最新版本
     */
    public void setOnlyCheck() {
        this.onlyCheck = false;
    }

    public void checkUpdate() {
        if (mAct == null) return;
        String url = ApiUtils.getVersionUrl();
        JHttpClient.get(null, url, null, new JsonResponseHandler<Configures>(Configures.class, false) {

            @Override
            public void onStart() {
                if (!onlyCheck)
                    DialogUtils.showLoading(mAct);
            }

            @Override
            public void onFinish() {
                if (!onlyCheck)
                    DialogUtils.hideLoading();
            }

            @Override
            public void onSuccess(Configures result) {

                if (result != null && result.data != null) {
                    if (checkIsLatestVersion(AppUtils.getVersionName(mAct.getApplicationContext()), result.data.vercode)) {
                        if (!onlyCheck)
                            ToastUtils.show(mAct.getApplicationContext(), mAct.getString(R.string.update_current_is_latest));
                    } else {
                        showUpdateDailog(result);
                    }
                }
            }
        });
    }

    private MyDialog logoutDialog;

    private void showUpdateDailog(final Configures result) {
        String title = mAct.getString(R.string.update_title);
        String message = String.format(mAct.getString(R.string.update_message), result.data.vercode);
        String sure = mAct.getString(R.string.update_sure);
        String cancel = mAct.getString(R.string.update_cancel);
        if (logoutDialog == null) {
            logoutDialog = new MyDialog(mAct, title);
            logoutDialog.setMessage(message);
            logoutDialog.setCancelable(false);
            logoutDialog.setPositiveButton(sure, cancel, new OnPositiveClickListener() {
                @Override
                public void onClick() {
                    downloadLatest(mAct.getApplicationContext(), result.data.downurl,
                            result.data.vercode);
                }
            });
        }
        logoutDialog.show();
    }


    public void downloadLatest(Context context, String url, String ver) {
        DownloadManager downloadManager =
                (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE
                | DownloadManager.Request.NETWORK_WIFI);
        request.setVisibleInDownloadsUi(false);
        request.setMimeType("application/vnd.android.package-archive");
        request.setTitle(context.getString(R.string.app_name) + " v" + ver);
        Constants.UPDATE_DOWNLOAD_ID = downloadManager.enqueue(request);
    }

    public boolean checkIsLatestVersion(String localVersion, String serverVersion) {

        if (localVersion == null || localVersion.length() == 0) {
            return false;
        }

        if (localVersion.equals(serverVersion)) {
            return true;
        }
        String[] arrayLocal = localVersion.replace(".", "-").split("-");
        String[] arrayServer = serverVersion.replace(".", "-").split("-");
        if (arrayLocal.length == 0 || arrayServer.length == 0) {
            return false;
        }
        int count = Math.max(arrayLocal.length, arrayServer.length);
        int local = 0;
        int server = 0;
        for (int i = 0; i < count; i++) {
            local = 0;
            server = 0;
            if (i < arrayLocal.length) {
                local = Integer.parseInt(arrayLocal[i]);
            }

            if (i < arrayServer.length) {
                server = Integer.parseInt(arrayServer[i]);
            }

            if (local > server) {
                return true;
            }

            if (local < server) {
                return false;
            }
        }

        return false;
    }

}
