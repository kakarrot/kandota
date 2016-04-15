package com.xulee.kandota.async;

import android.content.Context;
import android.os.AsyncTask;

import com.liuguangqiang.framework.utils.GsonUtils;
import com.liuguangqiang.framework.utils.PreferencesUtils;
import com.liuguangqiang.framework.utils.StringUtils;
import com.xulee.kandota.constant.Constants;
import com.xulee.kandota.entity.NewsResponse;


/**
 * 获取缓存的新闻
 */
public class AsyncGetCacheNews extends AsyncTask<String, Integer, NewsResponse> {

    private Context mContext;

    private CacheNewsHandler mHandler;

    public AsyncGetCacheNews(Context context, CacheNewsHandler handler) {
        this.mContext = context;
        this.mHandler = handler;
    }

    @Override
    protected NewsResponse doInBackground(String... params) {
        if (params != null && params[0] != null) {
            String json = PreferencesUtils.getString(mContext, Constants.PRE_SAVE_NAME, params[0]);
            if (StringUtils.isEmptyOrNull(json))
                return null;
            else
                return GsonUtils.getModel(json, NewsResponse.class);
        }
        return null;
    }

    @Override
    protected void onPostExecute(NewsResponse result) {
        if (mHandler != null) {
            if (result != null)
                mHandler.onSuccess(result);
            else
                mHandler.onEmpty();
        }
    }

    public interface CacheNewsHandler {

        public void onSuccess(NewsResponse newsResponse);

        public void onEmpty();

    }

}
