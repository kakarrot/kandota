package com.xulee.kandota.mvp.model;

import android.content.Context;

import com.liuguangqiang.android.mvp.BaseRequestUi;

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
//        String url = ApiUtils.getVideosListUrl(keyword, pageindex);
////        RequestParams params = new RequestParams();
////        params.put("page", pageindex);
////        if (!TextUtils.isEmpty(keyword))
////            params.put("keyword", keyword);
//
//        AsyncUtils.getNews(context, url, null, new JsonResponseHandler<NewsList>(NewsList.class) {
//
//            @Override
//            public void onSuccess(NewsList result) {
//                if (result != null && result.getData() != null) {
//                    ui.requestSuccess(result.getData());
//                }
//            }
//
//            @Override
//            public void onFinish() {
//                ui.requestFinished();
//            }
//        });
    }
}
