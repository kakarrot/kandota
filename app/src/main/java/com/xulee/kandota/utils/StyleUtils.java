package com.xulee.kandota.utils;

import android.support.v4.widget.SwipeRefreshLayout;

import com.xulee.kandota.R;

/**
 * Created by Eric on 15/1/6.
 */
public class StyleUtils {

    public static void setColorSchemeResources(SwipeRefreshLayout swipeRefreshLayout) {
        swipeRefreshLayout.setColorSchemeResources(R.color.main_dress_color,
                android.R.color.holo_orange_light, R.color.green,
                android.R.color.holo_blue_light);
    }

}
