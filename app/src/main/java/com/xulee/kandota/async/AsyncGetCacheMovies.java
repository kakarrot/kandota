package com.xulee.kandota.async;

import android.content.Context;
import android.os.AsyncTask;

import com.liuguangqiang.framework.utils.GsonUtils;
import com.liuguangqiang.framework.utils.PreferencesUtils;
import com.liuguangqiang.framework.utils.StringUtils;
import com.xulee.kandota.constant.Constants;
import com.xulee.kandota.entity.Movie;
import com.xulee.kandota.entity.MovieList;

import java.util.List;


/**
 * 获取缓存的视频。
 * <p>
 * 比如: 推荐,排行
 * </p>
 * <p/>
 * Created by John on 2014年7月19日
 */
public class AsyncGetCacheMovies extends AsyncTask<String, Integer, MovieList> {

    private Context mContext;

    private CacheMoviesHandler mHandler;

    public AsyncGetCacheMovies(Context context, CacheMoviesHandler handler) {
        this.mContext = context;
        this.mHandler = handler;
    }

    @Override
    protected MovieList doInBackground(String... params) {
        if (params != null && params[0] != null) {
            String json = PreferencesUtils.getString(mContext, Constants.PRE_SAVE_NAME, params[0]);
            if (StringUtils.isEmptyOrNull(json))
                return null;
            else
                return GsonUtils.getModel(json, MovieList.class);
        }
        return null;
    }

    @Override
    protected void onPostExecute(MovieList result) {
        if (mHandler != null) {
            if (result != null)
                mHandler.onSuccess(result.getData());
            else
                mHandler.onEmpty();
        }
    }

    public interface CacheMoviesHandler {

        public void onSuccess(List<Movie> list);

        public void onEmpty();

    }

}
