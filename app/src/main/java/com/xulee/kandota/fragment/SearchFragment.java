package com.xulee.kandota.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.liuguangqiang.android.mvp.Presenter;
import com.xulee.kandota.R;
import com.xulee.kandota.base.BaseListFragment;
import com.xulee.kandota.entity.NewsResponse;
import com.xulee.kandota.mvp.presenter.SearchPresenter;
import com.xulee.kandota.mvp.ui.SearchUi;
import com.xulee.kandota.mvp.ui.SearchUiCallback;

/**
 * Created by LX on 2016/1/28.
 */
public class SearchFragment extends BaseListFragment<NewsResponse> implements SearchUi
{

    private String keyword;

    private SearchUiCallback mCallback;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViews();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_base, container, false);
        return rootView;
    }

    @Override
    public void setUiCallback(SearchUiCallback searchUiCallback) {
        mCallback = searchUiCallback;
    }

    @Override
    public Presenter setPresenter() {
        return new SearchPresenter(getActivity(), this);
    }

    @Override
    public void initViews() {
        super.initViews();
//        adapter = new MoviesAdapter(getActivity().getApplicationContext(), data);
        setEnableProgressBar();
        setEmptyIcon(R.drawable.ic_empty_search);
        setEmptyMessage(R.string.empty_search);
        setAdapter();
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position >= 0 && position < data.size()) {
                    mCallback.onItemClick(data.get(position));
                }
            }
        });
    }

    @Override
    public void requestData() {
        super.requestData();
        mCallback.getMovies(pageindex, keyword);
    }

    public void search(String title) {
        keyword = title;
        pageindex = 1;
        data.clear();
        adapter.notifyDataSetChanged();
        requestData();
    }

}