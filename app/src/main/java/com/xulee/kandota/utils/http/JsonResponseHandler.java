package com.xulee.kandota.utils.http;

import com.liuguangqiang.framework.utils.GsonUtils;

/**
 * JsonResponseHandler
 * <p/>
 * Created by Eric on 2014-7
 */
public class JsonResponseHandler<T> extends BaseResponseHandler {

    private Class<T> classOf;

    /**
     * 是否从根节点解析。默认是true，如果为false，则从result节点开始解析。
     */
    private boolean isRootNode = true;

    public JsonResponseHandler(Class<T> classOf) {
        this.classOf = classOf;
    }

    public JsonResponseHandler(Class<T> classOf, boolean isRootNode) {
        this.classOf = classOf;
        this.isRootNode = isRootNode;
    }



    @Override
    public void onSuccess(String json) {
        if (isRootNode) {
            onSuccess(GsonUtils.getModel(json, classOf));
        } else {
            String result = GsonUtils.getResult(json, "result");
            onSuccess(GsonUtils.getModel(result, classOf));
        }

        if (json != null) onOrigin(json);
    }

    public void onSuccess(T result) {
    }

    public void onOrigin(String json) {
    }

}
