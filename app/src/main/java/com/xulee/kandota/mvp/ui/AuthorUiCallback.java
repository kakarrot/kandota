package com.xulee.kandota.mvp.ui;

import com.xulee.kandota.entity.Author;

/**
 * Created by LX on 2016/1/27.
 */
public interface AuthorUiCallback {
    void onItemClick(Author author);

    void getAuthors(int pageIndex, String keyword);

}
