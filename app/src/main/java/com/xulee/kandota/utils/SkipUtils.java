package com.xulee.kandota.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.liuguangqiang.framework.utils.StringUtils;
import com.liuguangqiang.framework.utils.ToastUtils;
import com.xulee.kandota.R;
import com.xulee.kandota.act.BrowserActivity;
import com.xulee.kandota.act.LoginActivity;
import com.xulee.kandota.act.RegisterActivity;
import com.xulee.kandota.act.ShakeActivity;
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
        //android.R.anim.fade_in, android.R.anim.fade_out
//        ((Activity) context).overridePendingTransition(R.anim.push_right_in, R.anim.push_left_out);
    }

    public static void skipToLogin(Context context){
        if(null == context){
            throw new NullPointerException("Context is null, can't start other Activity");
        }
        context.startActivity(new Intent(context, LoginActivity.class));
//        ((Activity) context).overridePendingTransition(R.anim.push_right_in, R.anim.push_left_out);
    }

    public static void skipToRegister(Context context){
        if(null == context){
            throw new NullPointerException("Context is null, can't start other Activity");
        }
        context.startActivity(new Intent(context, RegisterActivity.class));
    }

    /*
	 * 1 摇积分 2爱心商城 3刮刮乐 4大转盘 5个人中心 6节目导视 7主持人 8调查投票 9意见反馈 10节目评点 11新闻报料 12活动圈
	 * 13礼品展厅 14信息公告 15电视 16服务 17团购 18九宫格 19反诈骗 20黄金剧场 21鞠说好看 22 第一团购帮 23机票酒店预订
	 * 24兑奖区 25家乡频道 26现场摇27摇奖品 28麦秀 29违章速办 30辣妈萌娃秀 31热点新闻 32潮人天下33公益34旅游
	 */

    public static void skipToActivity(Context context,final int id){
        switch (id){
            case 1:
                Bundle b = new Bundle();
                b.putInt(ShakeActivity.TYPE_SHAKE, ShakeActivity.TYPE_SHAKE_SCORE);
                startActivity(context, ShakeActivity.class, b);
                break;
            case 3:
                break;
            case 4:
                break;
            case 8:
                break;
            case 9:
                break;
            case 11:
                break;
            case 12:
                break;
            case 14:
                break;
            case 24:
                break;
            case 26:
                Bundle b26 = new Bundle();
                b26.putInt(ShakeActivity.TYPE_SHAKE, ShakeActivity.TYPE_SHAKE_LIVE);
                startActivity(context, ShakeActivity.class, b26);
                break;
            case 27:
                Bundle b27 = new Bundle();
                b27.putInt(ShakeActivity.TYPE_SHAKE, ShakeActivity.TYPE_SHAKE_PRIZE);
                startActivity(context, ShakeActivity.class, b27);
                break;
            case 28:
                break;
            default:
                ToastUtils.show(context, "功能未添加~，请联系开发人员");
                break;
        }
    }

    public static void startActivity(Context context, Class<?> cls){
        if(null == context){
            throw new NullPointerException("Context is null, can't start other Activity");
        }
        Intent it = new Intent(context, cls);
        context.startActivity(it);
//        ((Activity) context).overridePendingTransition(R.anim.push_right_in, R.anim.push_left_out);
    }

    public static void startActivity(Context context, Class<?> cls, Bundle bundle){
        if(null == context){
            throw new NullPointerException("Context is null, can't start other Activity");
        }
        Intent it = new Intent(context, cls);
        it.putExtras(bundle);
        context.startActivity(it);
//        ((Activity) context).overridePendingTransition(R.anim.push_right_in, R.anim.push_left_out);
    }
}
