package com.xulee.kandota.mvp.model;

import android.content.Context;

import com.liuguangqiang.android.mvp.BaseRequestUi;
import com.xulee.kandota.async.AsyncUtils;
import com.xulee.kandota.entity.AuthorList;
import com.xulee.kandota.utils.ApiUtils;
import com.xulee.kandota.utils.http.JsonResponseHandler;

import javax.inject.Inject;

/**
 * Created by LX on 2016/1/27.
 */
public class AuthorModel {

    @Inject
    public AuthorModel() {
    }

    public void getAuthors(){
    }

    public void getAuthors(Context context, int pageindex, BaseRequestUi ui) {
        getAuthors(context, pageindex, null, ui);
    }

    public void getAuthors(Context context, int pageindex, String keyword, final BaseRequestUi ui) {
        String url = ApiUtils.getMovies();
//        RequestParams params = new RequestParams();
//        params.put("page", pageindex);
//        if (!TextUtils.isEmpty(keyword))
//            params.put("keyword", keyword);

        AsyncUtils.getAuthors(context, url, null, new JsonResponseHandler<AuthorList>(AuthorList.class) {

            @Override
            public void onSuccess(AuthorList result) {
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
