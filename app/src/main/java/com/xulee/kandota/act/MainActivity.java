package com.xulee.kandota.act;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.GridView;
import android.widget.Toast;

import com.liuguangqiang.android.mvp.Presenter;
import com.liuguangqiang.framework.utils.NetworkUtils;
import com.xulee.kandota.R;
import com.xulee.kandota.adapter.MainMenuAdapter;
import com.xulee.kandota.adapter.MainPagerAdapter;
import com.xulee.kandota.base.BaseActivity;
import com.xulee.kandota.entity.MainMenuItem;
import com.xulee.kandota.mvp.presenter.MainPresenter;
import com.xulee.kandota.mvp.ui.MainUi;
import com.xulee.kandota.mvp.ui.MainUiCallback;
import com.xulee.kandota.ui.SlowViewPager;
import com.xulee.kandota.utils.ImageLoaderUtils;
import com.xulee.kandota.utils.level.LevelUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

public class MainActivity extends BaseActivity implements MainUi {

    private static final int ACTION_SEARCH = 0;

    private static final int ACTION_SETTING = 1;

    private static final int ACTION_FEEDBACK = 2;

    private SlowViewPager viewPager;

    private MainPagerAdapter adapter;

    private boolean doubleBackToExitPressedOnce = false;

    @Bind(R.id.gv_menu)
    GridView gvMenu;

    private MainMenuAdapter menuAdapter;
    private List<MainMenuItem> menuData = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!NetworkUtils.isWifiEnabled(getApplicationContext())) {
            ImageLoaderUtils.setWifiEnable(false);
        }

        initViews();
        initActionBar();
        LevelUtils.dailyLogin(getApplicationContext());
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    public Presenter setPresenter() {
        return new MainPresenter(this, this);
    }

    @Override
    public void setUiCallback(MainUiCallback mainUiCallback) {
    }

    private void initActionBar() {
        hideActionBarBack();
        setTitle(R.string.app_name);
        setTitleBarRight(R.drawable.ic_action_setting, new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            exitApp();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, getString(R.string.double_click_to_quit), Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

    @Override
    public void showMenu(List<MainMenuItem> list) {
        menuData.addAll(list);
        menuAdapter.notifyDataSetChanged();
    }

    private int lastPosition = 0;

    private void selectedMenu(int position) {
        menuData.get(lastPosition).selected = false;
        menuData.get(position).selected = true;
        lastPosition = position;
        menuAdapter.notifyDataSetChanged();
    }

    private void initViews() {
        menuAdapter = new MainMenuAdapter(getApplicationContext(), menuData);
        gvMenu.setAdapter(menuAdapter);

        adapter = new MainPagerAdapter(getSupportFragmentManager());
        viewPager = (SlowViewPager) this.findViewById(R.id.viewpager_main);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(MainPagerAdapter.NUM - 1);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                selectedMenu(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @OnItemClick(R.id.gv_menu)
    public void onMenuItemClick(int position) {
        selectedMenu(position);
        viewPager.setCurrentItem(position);
    }
}