package com.xulee.kandota.utils.http;

import com.liuguangqiang.framework.utils.Logs;

/**
 * BaseResponseHandler
 * <p/>
 * Created by Eric
 */
public abstract class BaseResponseHandler {

    public abstract void onSuccess(String result);

    public void onFailure(String msg) {
        if (msg != null)
            Logs.i("BaseResponseHandler", msg);
    }

    public void onFailure(int statusCode, String msg) {

    }

    public void onFinish() {
    }

    public void onStart() {
    }

}
