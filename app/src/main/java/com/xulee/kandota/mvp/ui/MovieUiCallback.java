package com.xulee.kandota.mvp.ui;

import com.xulee.kandota.entity.NewsResponse;

/**
 * Created by LX on 2016/1/27.
 */
public interface MovieUiCallback {
    void onItemClick(NewsResponse movie);

    void getMovies(int pageIndex, String keyword);
}
