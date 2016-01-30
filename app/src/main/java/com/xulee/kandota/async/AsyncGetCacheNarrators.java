package com.xulee.kandota.async;

import android.content.Context;
import android.os.AsyncTask;

import com.liuguangqiang.framework.utils.GsonUtils;
import com.liuguangqiang.framework.utils.PreferencesUtils;
import com.liuguangqiang.framework.utils.StringUtils;
import com.xulee.kandota.constant.Constants;
import com.xulee.kandota.entity.Author;
import com.xulee.kandota.entity.AuthorList;

import java.util.List;


/**
 * 获取缓存的解说员列表。
 * <p>
 * 比如: 推荐,排行
 * </p>
 * <p/>
 * Created by John on 2014年7月19日
 */
public class AsyncGetCacheNarrators extends AsyncTask<String, Integer, AuthorList> {

    private Context mContext;

    private CacheAuthorsHandler mHandler;

    public AsyncGetCacheNarrators(Context context, CacheAuthorsHandler handler) {
        this.mContext = context;
        this.mHandler = handler;
    }

    @Override
    protected AuthorList doInBackground(String... params) {
        if (params != null && params[0] != null) {
            String json = PreferencesUtils.getString(mContext, Constants.PRE_SAVE_NAME, params[0]);
            if (StringUtils.isEmptyOrNull(json))
                return null;
            else
                return GsonUtils.getModel(json, AuthorList.class);
        }
        return null;
    }

    @Override
    protected void onPostExecute(AuthorList result) {
        if (mHandler != null) {
            if (result != null)
                mHandler.onSuccess(result.getData());
            else
                mHandler.onEmpty();
        }
    }

    public interface CacheAuthorsHandler {

        public void onSuccess(List<Author> list);

        public void onEmpty();

    }

}
