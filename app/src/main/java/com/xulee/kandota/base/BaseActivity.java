package com.xulee.kandota.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.WindowManager;

import com.liuguangqiang.android.mvp.Presenter;
import com.liuguangqiang.framework.utils.IntentUtils;
import com.liuguangqiang.framework.utils.SystemBarTintManager;
import com.xulee.kandota.R;
import com.xulee.kandota.constant.MyActions;
import com.xulee.kandota.login.LoginType;
import com.xulee.kandota.login.SinaWeiboLogin;
import com.xulee.kandota.login.ThirdLoginUtils;
import com.xulee.kandota.view.TitleBar;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * Activity 父类，子类必须在布局中定义TitleBar相同的id ，如果不需要，可以隐藏显示
 */
public class BaseActivity extends SwipeBackActivity implements Presenter.OnUiAttachedListener {

    private FinishAppReceiver receiver;
    private Presenter presenter;

    private TitleBar titleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        ButterKnife.bind(this);
        presenter = setPresenter();
        showActionBarBack();
        initReceiver();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setTranslucentStatus();
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

    public void setTranslucentStatus() {
//		// 透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//		// 透明导航栏
//		getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        // enable status bar tint
        tintManager.setStatusBarTintEnabled(true);
        // enable navigation bar tint
//		tintManager.setNavigationBarTintEnabled(false);
        tintManager.setTintResource(R.color.theme_color); // 需要的话，可以自定义系统状态栏背景
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
    public void startActivity(Intent intent) {
        super.startActivity(intent);
    }

    public void startActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
//        overridePendingTransition(R.anim.push_right_in, R.anim.push_left_out);
    }

    public void startActivity(Class<?> cls, Bundle extra) {
        Intent intent = new Intent(this, cls);
        intent.putExtras(extra);
        startActivity(intent);
//        overridePendingTransition(R.anim.push_right_in, R.anim.push_left_out);
    }

    /**
     * 显示ActionBar中的返回按钮。
     */
    public void showActionBarBack() {
        titleBar = (TitleBar) findViewById(R.id.titlebar);
        if (titleBar != null) {
            titleBar.showLeft();
            titleBar.setLeftAction(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        }
    }

    public void setTitle(int stringRes) {
        titleBar = (TitleBar) findViewById(R.id.titlebar);
        if (null != titleBar) {
            titleBar.setTitle(stringRes);
        }
    }

    public void setTitle(String stringRes) {
        titleBar = (TitleBar) findViewById(R.id.titlebar);
        if (null != titleBar) {
            titleBar.setTitle(stringRes);
        }
    }


    public void setTitleBarRight(int imgRes, View.OnClickListener listener) {
        titleBar = (TitleBar) findViewById(R.id.titlebar);
        if (null != titleBar) {
            titleBar.setRightIconAndAction(imgRes, listener);
            titleBar.showRight();
        }
    }

    /**
     * 隐藏ActionBar中的返回按钮。
     */
    public void hideActionBarBack() {
        titleBar = (TitleBar) findViewById(R.id.titlebar);
        if (titleBar != null)
            titleBar.hideLeft();
    }

    /**
     * 隐藏 titlebar
     */
    public void hideActionBar() {
        titleBar = (TitleBar) findViewById(R.id.titlebar);
        if (titleBar != null) {
            titleBar.setVisibility(View.GONE);
        }
    }

    public void setContent(Fragment fragment) {
        addFragment(R.id.fragment_container, fragment);
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
        getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.push_right_in, R.anim.push_left_out);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();

    }

    public void replaceFragment(int resId, Fragment fragment) {
        getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.push_right_in, R.anim.push_left_out);
        getSupportFragmentManager().beginTransaction().replace(resId, fragment).commit();
    }

}

