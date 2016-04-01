package com.xulee.kandota.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.culiu.mhvp.core.InnerListView;
import com.culiu.mhvp.core.InnerScroller;
import com.culiu.mhvp.integrated.ptr.PullToRefreshInnerListView;
import com.culiu.mhvp.integrated.ptr.pulltorefresh.PullToRefreshBase;
import com.liuguangqiang.framework.utils.NetworkUtils;
import com.liuguangqiang.framework.utils.PreferencesUtils;
import com.liuguangqiang.framework.utils.StringUtils;
import com.liuguangqiang.framework.utils.ToastUtils;
import com.xulee.kandota.R;
import com.xulee.kandota.async.AsyncGetCacheNews;
import com.xulee.kandota.async.AsyncUtils;
import com.xulee.kandota.constant.Constants;
import com.xulee.kandota.entity.NewsResponse;
import com.xulee.kandota.listeners.NewsRefreshListener;
import com.xulee.kandota.utils.ImageLoaderUtils;
import com.xulee.kandota.utils.SkipUtils;
import com.xulee.kandota.utils.http.JsonResponseHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * 新闻列表
 * author: lX
 */
public class InnerNewsFragment extends AbsListFragment {

    private final String CACHE_NEWS = "cache_news";
    public static final String KEY_ID = "ID";
    private static String[] fids = {"17256", "17257", "17258", "17259"};
    private boolean pauseOnScroll = true;
    private boolean pauseOnFling = true;
    /*************
     * InnerScrollerContainer interface
     **************/
    protected PullToRefreshInnerListView mPullToRefreshListView;
    protected InnerListView mListView;

    protected InnerNewsFragment fragment;

    private NewsRefreshListener listener;
    private int mPage = 1;
    private int pos = 0;

    private boolean isLoading = false;

    /**
     * TODO: make sure through this method, can get your InnerScroller(InnerListView) in your fragment.
     *
     * @return
     */
    @Override
    public InnerScroller getInnerScroller() {
        return mListView;
    }

    public void setOnNewsRefreshListener(NewsRefreshListener listener) {
        this.listener = listener;
    }

    /**
     * @param pos
     * @return
     */
    public static InnerNewsFragment newInstance(int pos) {
        InnerNewsFragment f = new InnerNewsFragment();
        Bundle b = new Bundle();
        b.putInt(KEY_ID, pos);
        f.setArguments(b);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        pos = getArguments().getInt(KEY_ID);
        if (mListView != null && viewThis != null) {
            if (viewThis.getParent() != null) {
                ((ViewGroup) viewThis.getParent()).removeView(viewThis);
            }
            return viewThis;
        }
        viewThis = inflater.inflate(R.layout.fragment_pulltorefresh_list, null);

        // TODO: Be sure have put PullToRefreshInnerListView or InnerListView in your layout and its height match parent or align parent's top.
        mPullToRefreshListView = (PullToRefreshInnerListView) viewThis.findViewById(R.id.pull_refresh_inner_listview);
        mPullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        // Set refreshing scale. You may change it or delete it if needed.
//        mPullToRefreshListView.setScaleRefreshing(0.568f);

        if (mPullToRefreshListView != null) {
            mPullToRefreshListView.setOnRefreshListener(this);
        }

        mListView = mPullToRefreshListView.getRefreshableView();

        // TODO: Every time listView initialized, don't forget to call registerToOuter.
        mListView.register2Outer(mOuterScroller, mIndex);

        // Demo: Use color to mark special areas.
        View emptyView = LayoutInflater.from(getActivity()).inflate(R.layout.layout_empty_2, null);
        mListView.setCustomEmptyView(emptyView);
        mListView.setCustomEmptyViewHeight(ViewGroup.LayoutParams.MATCH_PARENT, -1000);
        // Demo: color its auto completion area
        mListView.setContentAutoCompletionColor(Color.TRANSPARENT);

        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int scrollState) {
                switch (scrollState) {
                    case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                        ImageLoaderUtils.resume();
                        break;
                    case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
                        if (pauseOnScroll) {
                            ImageLoaderUtils.pause();
                        }
                        break;
                    case AbsListView.OnScrollListener.SCROLL_STATE_FLING:
                        if (pauseOnFling) {
                            ImageLoaderUtils.pause();
                        }
                        break;
                }
            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {

            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (!NetworkUtils.isAvailable(getActivity())) {
                    ToastUtils.show(getActivity(), R.string.error_network_unavailable_format);
                    return;
                }
                SkipUtils.skipToBrowse(getActivity(), mListItems.get(i - 2).title, mListItems.get(i - 2).link_content);
            }
        });

        // Two ways to load data: 1. initAdapter();  then notifyDataSetChanged(); 2. requestData(); then initAdapter();
        // demonstrate the 2nd way
        initAdapter();
        getCacheData();

        return viewThis;
    }

    /*************
     * InnerScrollerContainer interface End
     **************/

    protected List<NewsResponse.News.NewsData> mListItems;

    protected BaseAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void getCacheData() {
        new AsyncGetCacheNews(getActivity().getApplicationContext(), new AsyncGetCacheNews.CacheNewsHandler() {
            @Override
            public void onSuccess(NewsResponse result) {
                if (null == result || null == result.news.get(pos) || null == result.news.get(pos).data) {
                    requestData();
                    return;
                }
                if (result.news.get(pos).data.size() > 0) {
                    mListItems.clear();
                    if (null != listener) {
                        listener.onNewsRefresh(result);
                    }
                } else {

                }
                mListItems.addAll(result.news.get(pos).data);
                mAdapter.notifyDataSetChanged();
                requestData();
            }

            @Override
            public void onEmpty() {
                requestData();
            }
        }).execute(CACHE_NEWS + fids[pos]);
    }

    public void onResponse() {
        if (isLoading || getActivity() == null || getActivity().isFinishing()) {
            return;
        }
        isLoading = true;
        if (mListItems == null) {
            mListItems = new ArrayList<>();
        }

        AsyncUtils.getNews(getActivity(), fids[pos], mPage, new JsonResponseHandler<NewsResponse>(NewsResponse.class) {
            @Override
            public void onSuccess(NewsResponse result) {
                super.onSuccess(result);
                if (null == result || null == result.news.get(pos) || null == result.news.get(pos).data) {
                    return;
                }

                if (mPage == 1) {
                    if (result.news.get(pos).data.size() > 0) {
                        mListItems.clear();
                        if (null != listener) {
                            listener.onNewsRefresh(result);
                        }
                    } else {

                    }
                }
                mListItems.addAll(result.news.get(pos).data);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onSuccess(String json) {
                super.onSuccess(json);
                if (mPage == 1 && !StringUtils.isEmptyOrNull(json)) {
                    cacheNews(json);
                }
            }

            @Override
            public void onFinish() {
                super.onFinish();
                afterLoading();
            }
        });
    }

    public void afterLoading() {
        isLoading = false;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Return to normal state
                getInnerScroller().onRefresh(false);
                if (getInnerScroller().getReceiveView() instanceof PullToRefreshBase) {
                    ((PullToRefreshBase) getInnerScroller().getReceiveView()).onRefreshComplete();
                }
            }
        }, 100);
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        // Update the whole state, and forbidden touch
        getInnerScroller().onRefresh(true);
        mPage = 1;
        requestData();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        getInnerScroller().onRefresh(true);
        ++mPage;
        requestData();
    }

    protected void initAdapter() {
        if (mListItems == null) {
            mListItems = new ArrayList<>();
        }

        mAdapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return mListItems.size();
            }

            @Override
            public NewsResponse.News.NewsData getItem(int position) {
                return mListItems.get(position);
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                ViewHolder viewHolder = null;
                if (convertView == null) {
                    convertView = LayoutInflater.from(getActivity()).inflate(R.layout.item_news, null);
                    viewHolder = new ViewHolder(convertView);
                    convertView.setTag(viewHolder);
                } else {
                    viewHolder = (ViewHolder) convertView.getTag();
                }
                ImageLoaderUtils.display(mListItems.get(position).img, viewHolder.iv_img);
                viewHolder.tv_title.setText(getItem(position).title);
                viewHolder.tv_time.setText(getItem(position).date);
                return convertView;
            }

            class ViewHolder {
                ImageView iv_img;
                TextView tv_title;
                TextView tv_time;

                public ViewHolder(View v) {
                    iv_img = (ImageView) v.findViewById(R.id.item_iv_cover);
                    tv_title = (TextView) v.findViewById(R.id.item_tv_title);
                    tv_time = (TextView) v.findViewById(R.id.item_tv_time);
                }
            }
        };
        mListView.setAdapter(mAdapter);
    }

    private void cacheNews(String json) {
        if (isAdded())
            PreferencesUtils.putString(getActivity().getApplicationContext(), Constants.PRE_SAVE_NAME, CACHE_NEWS + fids[pos], json);
    }

    protected View viewThis;
}
