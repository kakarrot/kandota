package com.xulee.kandota.base;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.liuguangqiang.framework.adapter.MyBaseAdapter;
import com.xulee.kandota.utils.StyleUtils;
import com.xulee.kandota.R;
import com.xulee.kandota.constant.Constants;
import com.xulee.kandota.ui.PageableListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eric on 14-9-20.
 */
public class BaseListFragment<T> extends BaseFragment {

    private boolean pageable = true;

    public PageableListView mListView;

    public SwipeRefreshLayout mSwipeRefreshLayout;

    public List<T> data = new ArrayList<T>();

    public MyBaseAdapter<T> adapter;

    public int pageindex = 1;

    private boolean enableFooterPage = false;

    //EmptyView
    private LinearLayout layoutEmpty;
    private TextView tvEmpty;
    private Button btnRetry;
    private ImageView ivEmpty;

    public ProgressBar pbLoading;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_base, container, false);
        return rootView;
    }

    public void initViews() {
        mSwipeRefreshLayout = (SwipeRefreshLayout) getView().findViewById(R.id.swipe_container);
        StyleUtils.setColorSchemeResources(mSwipeRefreshLayout);
        mListView = (PageableListView) getView().findViewById(R.id.listview_pull_to_refresh);
        mListView.setOnScrollPageListener(new PageableListView.ScrollPageListener() {
            @Override
            public void onPage() {
                pageindex++;
                requestData();
            }
        });

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pageindex = 1;
                mListView.reset();
                requestData();
            }
        });

        initDefault();
    }

    public void initDefault() {
        setRefreshable(true);
        setPageable(true);
        setEnableEmptyView();
    }

    private void showEmptyView() {
        if (layoutEmpty == null) return;
        if (data.isEmpty()) {
            layoutEmpty.setVisibility(View.VISIBLE);
        } else {
            layoutEmpty.setVisibility(View.GONE);
        }
    }

    private void hideEmptyView() {
        if (layoutEmpty != null)
            layoutEmpty.setVisibility(View.GONE);
    }

    public void setEnableEmptyView() {
        layoutEmpty = (LinearLayout) getView().findViewById(R.id.layout_empty);
        layoutEmpty.setVisibility(View.GONE);
        tvEmpty = (TextView) getView().findViewById(R.id.tv_empty);
        tvEmpty.setText(R.string.empty_defualt);
        btnRetry = (Button) getView().findViewById(R.id.btn_empty);
        btnRetry.setText(R.string.str_retry);
        btnRetry.setVisibility(View.GONE);
        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestData();
            }
        });
        ivEmpty = (ImageView) getView().findViewById(R.id.iv_empty);
    }

    public void setEmptyIcon(int resId) {
        if (ivEmpty != null) {
            ivEmpty.setBackgroundResource(resId);
            ivEmpty.setVisibility(View.VISIBLE);
        }
    }

    public void setEnableRetry() {
        if (btnRetry != null) {
            btnRetry.setText(View.VISIBLE);
        }
    }

    public void setEmptyMessage(int resId) {
        if (tvEmpty != null) {
            tvEmpty.setText(resId);
            tvEmpty.setVisibility(View.VISIBLE);
        }
    }

    public void setAdapter() {
        if (mListView != null && adapter != null) {
            mListView.addPageFooterView();
            mListView.setAdapter(adapter);
            mListView.removePageFooterView();
        }
    }

    public void setEnableProgressBar() {
        pbLoading = (ProgressBar) getView().findViewById(R.id.pb_loading);
        if (pbLoading != null)
            pbLoading.setVisibility(View.GONE);
    }

    public void setRefreshable(boolean refreshable) {
        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.setEnabled(refreshable);
        }
    }

    public void setPageable(boolean pageable) {
        this.pageable = pageable;
        mListView.setPageEnable(pageable);
        if (!pageable) {
            mListView.removePageFooterView();
        }
    }

    public void requestData() {
        if (mListView != null && mListView.isLoading()) return;

        if (mListView != null)
            mListView.loadStart();

        if (pbLoading != null && data.isEmpty() && pageindex == 1) {
            mListView.removePageFooterView();
            pbLoading.setVisibility(View.VISIBLE);
        }

        hideEmptyView();
    }

    public void requestSuccess(List<T> newData) {
        if (pageable) {
            if (newData.size() < Constants.DEFAULT_PAGE_SIZE) {
                mListView.removePageFooterView();
                mListView.setPageEnable(false);
            } else {
                mListView.addPageFooterView();
                mListView.setPageEnable(true);
            }
        }

        if (pageindex == 1)
            data.clear();
        if (adapter != null)
            adapter.add(newData);
    }

    public void requestFailure() {
        if (mListView != null) {
            mListView.removePageFooterView();
        }
    }

    public void requestFinished() {
        if (mListView != null) {
            mListView.loadFinished();
        }

        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.setRefreshing(false);
        }

        if (data.isEmpty()) showEmptyView();

        if (pbLoading != null) pbLoading.setVisibility(View.GONE);
    }

}
