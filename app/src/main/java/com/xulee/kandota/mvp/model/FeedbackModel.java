/*
 * Copyright 2015 qiwenge
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.xulee.kandota.mvp.model;

import android.content.Context;

import com.liuguangqiang.framework.utils.ToastUtils;
import com.loopj.android.http.RequestParams;
import com.xulee.kandota.mvp.ui.FeedbackUi;
import com.xulee.kandota.utils.ApiUtils;
import com.xulee.kandota.utils.http.BaseResponseHandler;
import com.xulee.kandota.utils.http.JHttpClient;

import javax.inject.Inject;

/**
 * Created by Eric on 15/5/25.
 */
public class FeedbackModel {

    @Inject
    public FeedbackModel() {
    }

    public void feedback(final Context context, String content, final FeedbackUi ui) {
        String url = "";
        RequestParams params = new RequestParams();
        params.put("content", content);
        JHttpClient.post(url, params, new BaseResponseHandler() {

            @Override
            public void onStart() {
                ui.showLoading();
            }

            @Override
            public void onSuccess(String result) {
                ToastUtils.show(context, "感谢你的反馈!");
                ui.onSuccess();
            }

            @Override
            public void onFailure(String msg) {
                ToastUtils.show(context, "发送失败");
            }

            @Override
            public void onFinish() {
                ui.hideLoading();
            }
        });
    }

}
