package com.xulee.kandota.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.liuguangqiang.framework.utils.PreferencesUtils;
import com.xulee.kandota.adapter.AuthorsAdapter;
import com.xulee.kandota.async.AsyncGetCacheNarrators;
import com.xulee.kandota.async.AsyncUtils;
import com.xulee.kandota.base.BaseListFragment;
import com.xulee.kandota.constant.Constants;
import com.xulee.kandota.entity.Author;
import com.xulee.kandota.entity.AuthorList;
import com.xulee.kandota.utils.ApiUtils;
import com.xulee.kandota.utils.http.JsonResponseHandler;

import java.util.List;

/**
 * 解说员列表
 * Created by LX on 2016/1/29.
 */
public class AuthorListFragment extends BaseListFragment<Author> {

    private final String CACHE_AUTHOR = "cache_author";

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initEmptyView();
        initViews();
        getCacheData();
    }

    private void initEmptyView() {

    }

    private void getCacheData() {
        new AsyncGetCacheNarrators(getActivity().getApplicationContext(), new AsyncGetCacheNarrators.CacheAuthorsHandler() {

            @Override
            public void onSuccess(List<Author> list) {
                adapter.add(list);
                requestData();
            }

            @Override
            public void onEmpty() {
                System.out.println("Rank cache is empty");
                requestData();
            }
        }).execute(CACHE_AUTHOR);
    }

    private void cacheRank(String json) {
        if (isAdded())
            PreferencesUtils.putString(getActivity().getApplicationContext(), Constants.PRE_SAVE_NAME, CACHE_AUTHOR, json);
    }


    @Override
    public void initViews() {
        super.initViews();
        adapter = new AuthorsAdapter(getActivity(), data);
        setAdapter();
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position < data.size()) {
//                    Bundle extra = new Bundle();
//                    extra.putParcelable(BookDetailActivity.EXTRA_BOOK, data.get(position));
//                    startActivity(BookDetailActivity.class, extra);
                }

            }
        });

    }

    @Override
    public void requestData() {
        super.requestData();
        getAuthorsList();
    }

    /**
     * 获取解说员。
     */
    private void getAuthorsList() {
        String url = ApiUtils.getAuthors();
        AsyncUtils.getAuthors(getActivity(), url, null, new JsonResponseHandler<AuthorList>(AuthorList.class) {

            @Override
            public void onOrigin(String json) {
                if (pageindex == 1)
                    cacheRank(json);
            }

            @Override
            public void onSuccess(AuthorList result) {
                if (result != null) {
                    requestSuccess(result.getData());
                }
            }

            @Override
            public void onFailure(String msg) {
                requestFailure();
            }

            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onFinish() {
                requestFinished();
            }
        });

    }

}
