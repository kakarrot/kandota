package com.xulee.kandota.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.xulee.kandota.fragment.MeFragment;
import com.xulee.kandota.fragment.NewsFragment;


/**
 * 主页面适配器。
 */
public class MainPagerAdapter extends FragmentPagerAdapter {

    public static final int NUM = 4;

    private NewsFragment newsFragment;
    private NewsFragment Hudongfragment;
    private NewsFragment ServiceFragment;

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int arg0) {
        switch (arg0) {
            case 0:
                if (Hudongfragment == null) {
                    Hudongfragment = new NewsFragment();
                }
                return Hudongfragment;

            case 1:
                if (newsFragment == null) {
                    newsFragment = new NewsFragment();
                }
                return newsFragment;
            case 2:
                if (ServiceFragment == null) {
                    ServiceFragment = new NewsFragment();
                }
                return ServiceFragment;
            case 3:
                return new MeFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return NUM;
    }

}
