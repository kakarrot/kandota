package com.xulee.kandota.module;


import com.xulee.kandota.mvp.ui.LegalUi;
import com.xulee.kandota.utils.ApiUtils;
import com.xulee.kandota.utils.http.JHttpClient;
import com.xulee.kandota.utils.http.StringResponseHandler;

import javax.inject.Inject;

/**
 * Created by Eric on 15/5/12.
 */
public class LegalModel {

    @Inject
    public LegalModel() {
    }

    public void getLegal(final LegalUi ui) {
        String url = ApiUtils.getStatement();
        JHttpClient.get(url, null, new StringResponseHandler() {
            @Override
            public void onSuccess(String result) {
                if (result != null)
                    ui.setLegal(result);
            }
        });
    }

}
