package com.xulee.kandota.fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewConfiguration;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.xulee.kandota.R;
import com.xulee.kandota.act.ServiceActivity;
import com.xulee.kandota.base.BaseFragment;
import com.xulee.kandota.view.ProgressWebView;

import butterknife.Bind;

/**
 * WEB页面
 */
public class WebViewFragment extends BaseFragment {

    public static final String EXTRA_URL = "url";

    @Bind(R.id.layout_container)
    FrameLayout webViewContainer;

    private ProgressWebView webView;

    private boolean isOnPause = false;

    private boolean isOpenSelf = false;

    private String mUrl;

    public static WebViewFragment newInstance(String url) {
        WebViewFragment f = new WebViewFragment();
        Bundle b = new Bundle();
        b.putString(EXTRA_URL, url);
        f.setArguments(b);
        return f;
    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_webview;
    }

    // 硬件加速
    private void hardwareAccelerate() {
        if (getPhoneSDKInt() >= 14) {
            getActivity().getWindow().setFlags(0x1000000, 0x1000000);
        }
    }

    public int getPhoneSDKInt() {
        int version = 0;
        try {
            version = Integer.valueOf(android.os.Build.VERSION.SDK);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return version;
    }

    /**
     * 设置内部链接是本身打开，还是跳转到其它Activity打开
     *
     * @param bool
     */
    public void setOpenSelf(boolean bool) {
        isOpenSelf = bool;
    }

    @Override
    public void initViews() {

        if (null == getActivity()) {
            return;
        }

        hardwareAccelerate();

        webView = new ProgressWebView(getActivity().getApplicationContext());

        webView.clearCache(true);
        webViewContainer.addView(webView, 0);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.setDownloadListener(new DownloadListener() {

            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition,
                                        String mimetype, long contentLength) {
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                view.loadUrl(url);
                if (url.startsWith("mailto:") || url.startsWith("geo:") || url.startsWith("tel:")) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                    return true;
                }

                if (!mUrl.equals(url) && !isOpenSelf) {
                    Intent intent = new Intent(getActivity(), ServiceActivity.class);
                    intent.putExtra(ServiceActivity.EXTRA_URL, url);
                    intent.putExtra(ServiceActivity.EXTRA_TITLE, "");
                    startActivity(intent);
                    return true;
                } else {
                    view.loadUrl(url);
                }
                return super.shouldOverrideUrlLoading(view, url);
            }
        });

        mUrl = getArguments().getString(EXTRA_URL);
        webView.loadUrl(mUrl);
    }

    /**
     * webview 回退
     */
    public void back() {
        if (webView != null && webView.canGoBack()) {
            webView.goBack();
        } else {
            if (webView != null) {
                webView.stopLoading();
            }
            webView = null;
            getActivity().finish();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            if (isOnPause) {
                if (webView != null) {
                    webView.getClass().getMethod("onResume").invoke(webView, (Object[]) null);
                }
                isOnPause = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        try {
            if (webView != null) {
                webView.getClass().getMethod("onPause").invoke(webView, (Object[]) null);
                isOnPause = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (webView != null) {
            webView.getSettings().setBuiltInZoomControls(true);
            webView.stopLoading();
            webView.removeAllViews();
            webView.setVisibility(View.GONE);
            long delayTime = ViewConfiguration.getZoomControlsTimeout();
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    webView.destroy();
                    webView = null;
                }
            }, delayTime);
        }
    }

}
