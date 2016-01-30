package com.xulee.kandota.mvp.ui;

import com.xulee.kandota.entity.Movie;

/**
 * Created by LX on 2016/1/27.
 */
public interface MovieUiCallback {
    void onItemClick(Movie movie);

    void getMovies(int pageIndex, String keyword);
}
