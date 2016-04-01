package com.xulee.kandota.act;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.liuguangqiang.framework.utils.ToastUtils;
import com.xulee.kandota.R;
import com.xulee.kandota.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * 网页浏览器。
 *
 * @author Eric
 */
public class BrowserActivity extends BaseActivity {

    private static final int ACTION_SHARE = 0;

    public static final String EXTRA_TITLE = "EXTRA_TITLE";

    public static final String EXTRA_URL = "EXTRA_URL";

    @Bind(R.id.webview)
    WebView webview;

    @Bind(R.id.pb_loading)
    ProgressBar pbLoading;

    private String url;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);
        ButterKnife.bind(this);
        getIntentParams();
        initViews();
    }

    private void getIntentParams() {
        Intent intent = getIntent();
        Bundle extra = intent.getExtras();

        if (extra != null && extra.containsKey(EXTRA_TITLE)) {
            setTitle(extra.getString(EXTRA_TITLE));
        }

        if (extra != null && extra.containsKey(EXTRA_URL)) {
            url = extra.getString(EXTRA_URL);
        }
    }

    /**
     * 初始化视图。
     */
    private void initViews() {
        pbLoading = (ProgressBar) findViewById(R.id.pb_loading);
        pbLoading.setMax(100);
        webview = (WebView) findViewById(R.id.webview);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webview.getSettings().setDomStorageEnabled(true);
        webview.getSettings().setUseWideViewPort(true);
        webview.getSettings().setLoadWithOverviewMode(true);
        webview.setDownloadListener(new DownloadListener() {

            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition,
                                        String mimetype, long contentLength) {
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        webview.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                pbLoading.setProgress(newProgress);
                if (newProgress > 80) {
                    pbLoading.setVisibility(View.GONE);
                }

                super.onProgressChanged(view, newProgress);
            }
        });

        webview.loadUrl(url);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(ACTION_SHARE, 0, 0, R.string.action_share)
                .setIcon(R.drawable.ic_share)
                .setShowAsAction(
                        MenuItem.SHOW_AS_ACTION_ALWAYS
                                | MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW
                );
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case ACTION_SHARE:
                ToastUtils.show(BrowserActivity.this, R.string.action_share);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            back();
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        back();
    }

    private void back() {
        if (webview != null && webview.canGoBack()) {
            webview.goBack();
        } else {
            if (webview != null) {
                webview.stopLoading();
            }
            webview = null;
            finish();
        }
    }

}
