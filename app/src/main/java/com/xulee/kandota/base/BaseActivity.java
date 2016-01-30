package com.xulee.kandota.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.MenuItem;

import com.liuguangqiang.android.mvp.Presenter;
import com.liuguangqiang.framework.utils.IntentUtils;
import com.xulee.kandota.R;
import com.xulee.kandota.constant.MyActions;
import com.xulee.kandota.login.LoginType;
import com.xulee.kandota.login.SinaWeiboLogin;
import com.xulee.kandota.login.ThirdLoginUtils;

public class BaseActivity extends FragmentActivity implements Presenter.OnUiAttachedListener {

    private FinishAppReceiver receiver;
    private Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        presenter = setPresenter();
        showActionBarBack();
        initReceiver();
    }

    protected int getContentView() {
        return R.layout.activity_base;
    }

    public Presenter setPresenter() {
        return null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (receiver != null) {
            unregisterReceiver(receiver);
            receiver = null;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (presenter != null && !presenter.isAttachedUi()) {
            presenter.setOnUiAttachedListener(this);
            presenter.attach();
        }

//        MobclickAgent.onResume(this);
//        JPushInterface.onResume(this);
    }

    @Override
    public void onAttachedUi() {
    }

    @Override
    protected void onPause() {
        super.onPause();
//        MobclickAgent.onPause(this);
//        JPushInterface.onPause(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void finish() {
        super.finish();
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
    }

    public void startActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }

    public void startActivity(Class<?> cls, Bundle extra) {
        Intent intent = new Intent(this, cls);
        intent.putExtras(extra);
        startActivity(intent);
    }

    private void initActionBar() {
        if (getActionBar() != null && getActionBar().isShowing()) {
            getActionBar().setDisplayUseLogoEnabled(false);
            getActionBar().setDisplayShowHomeEnabled(false);
        }
    }

    /**
     * 显示ActionBar中的返回按钮。
     */
    public void showActionBarBack() {
        if (getActionBar() != null && getActionBar().isShowing()) {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    /**
     * 隐藏ActionBar中的返回按钮。
     */
    public void hideActionBarBack() {
        if (getActionBar() != null && getActionBar().isShowing())
            getActionBar().setDisplayHomeAsUpEnabled(false);
    }

    public void setContent(Fragment fragment) {
        addFragment(R.id.layout_content, fragment);
    }

    public void addFragment(int resid, Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().add(resid, fragment).commit();
        }
    }

    private void initReceiver() {
        receiver = new FinishAppReceiver();
        IntentFilter filter = new IntentFilter(MyActions.ACTION_QUIT_APP);
        registerReceiver(receiver, filter);
    }

    /**
     * 退出.
     */
    public void exitApp() {
        IntentUtils.sendBroadcast(getApplicationContext(), MyActions.ACTION_QUIT_APP);
    }

    public class FinishAppReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(MyActions.ACTION_QUIT_APP)) {
                finish();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (ThirdLoginUtils.loginType != null && ThirdLoginUtils.loginType == LoginType.weibo
                && SinaWeiboLogin.mSsoHandler != null && data != null) {
            SinaWeiboLogin.mSsoHandler.authorizeCallBack(requestCode, resultCode, data);
        }
    }

    public void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(android.R.id.content, fragment).commit();
    }

    public void replaceFragment(int resId, Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(resId, fragment).commit();
    }

}

