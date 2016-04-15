package com.xulee.kandota.act;


import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.liuguangqiang.framework.utils.ToastUtils;
import com.xulee.kandota.R;
import com.xulee.kandota.base.BaseActivity;
import com.xulee.kandota.fragment.WebViewFragment;


/**
 * 网页浏览器。
 *
 * @author Eric
 */
public class BrowserActivity extends BaseActivity {

    private static final int ACTION_SHARE = 0;

    public static final String EXTRA_TITLE = "EXTRA_TITLE";

    public static final String EXTRA_URL = "EXTRA_URL";

    private WebViewFragment webViewFragment;

    private String url;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getIntentParams();
        initViews();
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_browser;
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
        webViewFragment = WebViewFragment.newInstance(url);
        replaceFragment(R.id.fragment_container, webViewFragment);
        showActionBarBack();
        setTitleBarRight(R.drawable.ic_share, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.show(BrowserActivity.this, R.string.action_share);
            }
        });
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
