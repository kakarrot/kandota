package com.xulee.kandota.ui;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.animation.Interpolator;

import java.lang.reflect.Field;

/**
 * 缓慢ViewPager.
 * <p/>
 * 用反射修改Scroller，让ViewPager滑动更慢。降低调用setCurrentItem后的滑动速度。
 * <p/>
 * Created by Eric on 2014-7-17.
 */
public class SlowViewPager extends ViewPager {

    private SlowScroller mScroller;

    public SlowViewPager(Context context) {
        super(context);
        init();
    }

    public SlowViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        try {
            Class<?> viewpager = ViewPager.class;
            Field scroller = viewpager.getDeclaredField("mScroller");
            scroller.setAccessible(true);
            Field interpolator = viewpager.getDeclaredField("sInterpolator");
            interpolator.setAccessible(true);
            mScroller = new SlowScroller(getContext(), (Interpolator) interpolator.get(null));
            scroller.set(this, mScroller);
        } catch (Exception e) {
        }
    }

}
