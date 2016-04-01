package com.xulee.kandota.act;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;

import com.liuguangqiang.framework.utils.AppUtils;
import com.liuguangqiang.framework.utils.StringUtils;
import com.loopj.android.http.RequestParams;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.xulee.kandota.R;
import com.xulee.kandota.async.AsyncUtils;
import com.xulee.kandota.base.BaseActivity;
import com.xulee.kandota.constant.Constants;
import com.xulee.kandota.entity.AdsResponse;
import com.xulee.kandota.entity.Configures;
import com.xulee.kandota.utils.ApiUtils;
import com.xulee.kandota.utils.ImageLoaderUtils;
import com.xulee.kandota.utils.http.JHttpClient;
import com.xulee.kandota.utils.http.JsonResponseHandler;


public class SplashActivity extends BaseActivity {

    private static final int mDuration = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        patchAd();
    }

    private void patchAd() {
        AsyncUtils.getAds(this, new JsonResponseHandler<AdsResponse>(AdsResponse.class){
            @Override
            public void onSuccess(AdsResponse result) {
                super.onSuccess(result);
                if(null != result && "ok".equals(result.status) && !StringUtils.isEmptyOrNull(result.ad.image)){
                    showAds(result.ad.image);
                }
                skipToMain();
            }

            @Override
            public void onFailure(String msg) {
                super.onFailure(msg);
                skipToMain();
            }
        });

    }

    private void showAds(String imgUrl){
        ImageView iv_ads = (ImageView) this.findViewById(R.id.iv_lanucher_ads);
        ImageLoaderUtils.display(imgUrl, iv_ads, ImageLoaderUtils.createOptions(R.drawable.splash_bg));
    }

    public boolean canJumpImmediately = false;

    @Override
    protected void onPause() {
        super.onPause();
        canJumpImmediately = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (canJumpImmediately) {
            jumpWhenCanClick();
        }
        canJumpImmediately = true;
    }

    private void jumpWhenCanClick() {
        if (canJumpImmediately) {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            finish();
        } else {
            canJumpImmediately = true;
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            getScreenSize();
        }
    }

    public void getScreenSize() {
        Display dis = getWindowManager().getDefaultDisplay();
        Point outSize = new Point(0, 0);
        dis.getSize(outSize);
        if (outSize != null) {
            Constants.WIDTH = outSize.x;
            Constants.HEIGHT = outSize.y;
        }
    }

    private void skipToMain() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
            }
        }, mDuration);
    }

}
