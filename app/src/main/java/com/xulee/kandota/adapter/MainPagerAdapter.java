package com.xulee.kandota.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.xulee.kandota.fragment.AuthorListFragment;
import com.xulee.kandota.fragment.MeFragment;
import com.xulee.kandota.fragment.MovieListFragment;


/**
 * 主页面适配器。
 */
public class MainPagerAdapter extends FragmentPagerAdapter {

    public static final int NUM = 3;

    private AuthorListFragment authorListFragment;
    private MovieListFragment movieListFragment;

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int arg0) {
        switch (arg0) {
            case 0:
                if (movieListFragment == null) {
                    movieListFragment = new MovieListFragment();
                }
                return movieListFragment;

            case 1:
                if (authorListFragment == null) {
                    authorListFragment = new AuthorListFragment();
                }
                return authorListFragment;
            case 2:
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
