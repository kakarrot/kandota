package com.xulee.kandota.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.culiu.mhvp.core.InnerScrollerContainer;
import com.culiu.mhvp.core.OuterPagerAdapter;
import com.culiu.mhvp.core.OuterScroller;
import com.xulee.kandota.fragment.InnerNewsFragment;
import com.xulee.kandota.listeners.NewsRefreshListener;

/**
 * @author Xavier-S
 * @date 2015.10.08 20:33
 */
public class NewsPagerAdapter extends FragmentPagerAdapter implements OuterPagerAdapter{

    /****    OuterPagerAdapter methods   ****/
    private OuterScroller mOuterScroller;

    private NewsRefreshListener listener;
    @Override
    public void setOuterScroller(OuterScroller outerScroller) {
        mOuterScroller = outerScroller;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        // TODO: Make sure to put codes below in your PagerAdapter's instantiateItem()
        // cuz Fragment has some weird life cycle.
        InnerScrollerContainer fragment =
                (InnerScrollerContainer) super.instantiateItem(container, position);

        if (null != mOuterScroller) {
            fragment.setOuterScroller(mOuterScroller, position);
        }
        return fragment;
    }
    /****  OuterPagerAdapter methods End   ****/


    /************************ Test data *********************/
    public NewsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setNewsRefreshListener(NewsRefreshListener listener){
        this.listener = listener;
    }

    protected CharSequence[] mTitles = {"快讯", "潮汕", "玩食", "曝料"};

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Override
    public final Fragment getItem(int position) {
        InnerNewsFragment fragment = getNewsFragment(position);
        fragment.setOnNewsRefreshListener(listener);
        return fragment;
    }


    private InnerNewsFragment getNewsFragment(int position) {
        return InnerNewsFragment.newInstance(position);
    }
}
