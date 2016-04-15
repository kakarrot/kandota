package com.xulee.kandota.act;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;

import com.xulee.kandota.R;
import com.xulee.kandota.base.BaseActivity;
import com.xulee.kandota.fragment.WebViewFragment;

import butterknife.OnClick;

public class ServiceActivity extends BaseActivity {

    public static final String EXTRA_TITLE = "EXTRA_TITLE";

    public static final String EXTRA_URL = "EXTRA_URL";

    private WebViewFragment webViewFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String url = getIntent().getStringExtra(EXTRA_URL);
//        String title = getIntent().getStringExtra(EXTRA_TITLE);
        if (null == url || TextUtils.isEmpty(url)) {
            return;
        }
        hideActionBar();
        webViewFragment = WebViewFragment.newInstance(url);
        webViewFragment.setOpenSelf(true);
        replaceFragment(R.id.fragment_container, webViewFragment);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_service;
    }

    @OnClick(R.id.btn_back_home)
    void backHome() {
        finish();
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
        webViewFragment.back();
    }
}
