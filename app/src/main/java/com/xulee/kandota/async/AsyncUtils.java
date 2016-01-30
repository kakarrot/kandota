package com.xulee.kandota.async;

import android.content.Context;

import com.liuguangqiang.framework.utils.Logs;
import com.liuguangqiang.framework.utils.StringUtils;
import com.liuguangqiang.framework.utils.ToastUtils;
import com.loopj.android.http.RequestParams;
import com.xulee.kandota.constant.Constants;
import com.xulee.kandota.entity.MovieList;
import com.xulee.kandota.entity.AuthorList;
import com.xulee.kandota.utils.ApiUtils;
import com.xulee.kandota.utils.http.JHttpClient;
import com.xulee.kandota.utils.http.JsonResponseHandler;
import com.xulee.kandota.utils.http.StringResponseHandler;

public class AsyncUtils {

    /**
     * 获取视频
     * @param context
     * @param url
     * @param params
     * @param handler
     */
    public static void getMovies(Context context, String url, RequestParams params,
                                JsonResponseHandler<MovieList> handler) {
        if (params != null) {
            params.put("limit", "" + Constants.DEFAULT_PAGE_SIZE);
        }
        JHttpClient.get(context, url, params, handler);
    }

    public static void getAuthors(Context context, String url, RequestParams params,
                                  JsonResponseHandler<AuthorList> handler) {
        if (params != null) {
            params.put("limit", "" + Constants.DEFAULT_PAGE_SIZE);
        }
        JHttpClient.get(context, url, params, handler);
    }

    /**
     * 赞
     *
     * @param movieId
     */
    public static void postVoteup(final Context context, String movieId) {
        String url = ApiUtils.postBookVoteUp(movieId);
        JHttpClient.post(url, null, new StringResponseHandler() {

            @Override
            public void onStart() {
                Logs.i("postViewVoteup-onStart", "onStart");
            }

            @Override
            public void onSuccess(String result) {
                if (StringUtils.isEmptyOrNull(result)) result = "success with empty result.";
                Logs.i("postViewVoteup-onSuccess", result);
                ToastUtils.show(context, "点赞成功");
            }

            @Override
            public void onFailure(String msg) {
                if (StringUtils.isEmptyOrNull(msg)) msg = "Failure with unknow message.";
                Logs.i("postViewVoteup-onFailure", msg);
                ToastUtils.show(context, "点赞失败");
            }
        });
    }

}
