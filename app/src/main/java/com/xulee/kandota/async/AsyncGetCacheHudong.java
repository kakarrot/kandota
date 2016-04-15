package com.xulee.kandota.async;

import android.content.Context;
import android.os.AsyncTask;

import com.liuguangqiang.framework.utils.GsonUtils;
import com.liuguangqiang.framework.utils.PreferencesUtils;
import com.liuguangqiang.framework.utils.StringUtils;
import com.xulee.kandota.constant.Constants;
import com.xulee.kandota.entity.HudongResponse;
import com.xulee.kandota.entity.NewsResponse;


/**
 * 获取缓存的互动。
 */
public class AsyncGetCacheHudong extends AsyncTask<String, Integer, HudongResponse> {

    private Context mContext;

    private CacheHudongHandler mHandler;

    public AsyncGetCacheHudong(Context context, CacheHudongHandler handler) {
        this.mContext = context;
        this.mHandler = handler;
    }

    @Override
    protected HudongResponse doInBackground(String... params) {
        if (params != null && params[0] != null) {
            String json = PreferencesUtils.getString(mContext, Constants.PRE_SAVE_NAME, params[0]);
            if (StringUtils.isEmptyOrNull(json))
                return null;
            else
                return GsonUtils.getModel(json, HudongResponse.class);
        }
        return null;
    }

    @Override
    protected void onPostExecute(HudongResponse result) {
        if (mHandler != null) {
            if (result != null)
                mHandler.onSuccess(result);
            else
                mHandler.onEmpty();
        }
    }

    public interface CacheHudongHandler {

        public void onSuccess(HudongResponse newsResponse);

        public void onEmpty();

    }

}
