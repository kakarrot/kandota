package com.xulee.kandota.mvp.model;

import android.content.Context;
import android.text.TextUtils;

import com.liuguangqiang.android.mvp.BaseRequestUi;
import com.loopj.android.http.RequestParams;
import com.xulee.kandota.async.AsyncUtils;
import com.xulee.kandota.entity.MovieList;
import com.xulee.kandota.utils.ApiUtils;
import com.xulee.kandota.utils.http.JsonResponseHandler;

import javax.inject.Inject;
/**
 * Created by LX on 2016/1/27.
 */
public class MovieModel {

    @Inject
    public MovieModel() {
    }

    public void getMovies(){
    }

    public void getMovies(Context context, int pageindex, BaseRequestUi ui) {
        getMovies(context, pageindex, null, ui);
    }

    public void getMovies(Context context, int pageindex, String keyword, final BaseRequestUi ui) {
        String url = ApiUtils.getMovies();
//        RequestParams params = new RequestParams();
//        params.put("page", pageindex);
//        if (!TextUtils.isEmpty(keyword))
//            params.put("keyword", keyword);

        AsyncUtils.getMovies(context, url, null, new JsonResponseHandler<MovieList>(MovieList.class) {

            @Override
            public void onSuccess(MovieList result) {
                if (result != null && result.getData() != null) {
                    ui.requestSuccess(result.getData());
                }
            }

            @Override
            public void onFinish() {
                ui.requestFinished();
            }
        });
    }
}
