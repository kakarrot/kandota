package com.xulee.kandota.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.liuguangqiang.framework.utils.StringUtils;
import com.liuguangqiang.framework.utils.ToastUtils;
import com.xulee.kandota.R;
import com.xulee.kandota.act.BrowserActivity;
import com.xulee.kandota.entity.NewsResponse;

/**
 * 跳转工具类
 */
public class SkipUtils {

    /**
     * 跳转到网页
     * @param context
     * @param title 标题
     * @param url 链接
     */
    public static void skipToBrowse(Context context, String title, String url){
        if(null == context){
            throw new NullPointerException("Context is null, can't start other Activity");
        }
        if(StringUtils.isEmptyOrNull(url)){
            ToastUtils.show(context, R.string.empty_url);
            return;
        }

        context.startActivity(new Intent(context, BrowserActivity.class)
                .putExtra(BrowserActivity.EXTRA_TITLE, title)
                .putExtra(BrowserActivity.EXTRA_URL, url));
        ((Activity) context).overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

}
