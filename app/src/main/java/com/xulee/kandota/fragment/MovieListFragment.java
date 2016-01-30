package com.xulee.kandota.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.liuguangqiang.framework.utils.PreferencesUtils;
import com.xulee.kandota.act.BrowserActivity;
import com.xulee.kandota.adapter.MoviesAdapter;
import com.xulee.kandota.async.AsyncGetCacheMovies;
import com.xulee.kandota.async.AsyncUtils;
import com.xulee.kandota.base.BaseListFragment;
import com.xulee.kandota.constant.Constants;
import com.xulee.kandota.entity.Movie;
import com.xulee.kandota.entity.MovieList;
import com.xulee.kandota.utils.ApiUtils;
import com.xulee.kandota.utils.http.JsonResponseHandler;

import java.util.List;

/**
 * Created by LX on 2016/1/28.
 */
public class MovieListFragment extends BaseListFragment<Movie> {

    private final String CACHE_MOVIE = "cache_movie";

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
        new AsyncGetCacheMovies(getActivity().getApplicationContext(), new AsyncGetCacheMovies.CacheMoviesHandler() {

            @Override
            public void onSuccess(List<Movie> list) {
                adapter.add(list);
                requestData();
            }

            @Override
            public void onEmpty() {
                System.out.println("Rank cache is empty");
                requestData();
            }
        }).execute(CACHE_MOVIE);
    }

    private void cacheRank(String json) {
        if (isAdded())
            PreferencesUtils.putString(getActivity().getApplicationContext(), Constants.PRE_SAVE_NAME, CACHE_MOVIE, json);
    }


    @Override
    public void initViews() {
        super.initViews();
        adapter = new MoviesAdapter(getActivity(), data);
        setAdapter();
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position < data.size()) {
                    Bundle extra = new Bundle();
                    String url = getPlayUrl(data.get(position).getHref());
                    extra.putString(BrowserActivity.EXTRA_TITLE, url);
                    startActivity(BrowserActivity.class, extra);
                }
            }
        });

    }

    @Override
    public void requestData() {
        super.requestData();
        getMovieList();
    }

    public String getPlayUrl(String url){
        int str_start = url.indexOf("id_") + 3;
        int str_end = url.indexOf(".html?");
        return "http://player.youku.com/embed/" + url.substring(str_start, str_end);
    }
    /**
     * 获取最新Dota视频。
     */
    private void getMovieList() {
        String url = ApiUtils.getMovies();
        AsyncUtils.getMovies(getActivity(), url, null, new JsonResponseHandler<MovieList>(MovieList.class) {

            @Override
            public void onOrigin(String json) {
                if (pageindex == 1)
                    cacheRank(json);
            }

            @Override
            public void onSuccess(MovieList result) {
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
