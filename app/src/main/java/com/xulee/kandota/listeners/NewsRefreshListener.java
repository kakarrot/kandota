package com.xulee.kandota.listeners;

import com.xulee.kandota.entity.NewsResponse;

/**
 * Created by LX on 2016/3/24.
 */
public interface NewsRefreshListener {
    void onNewsRefresh(NewsResponse response);
}
