package com.xulee.kandota.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.xulee.kandota.R;

public class PageableListView extends ListView {

    private boolean isLoading = false;
    private boolean mLastItemVisible = false;
    private ScrollPageListener pageListener;
    private View pagerFooter;

    /**
     * 是否允许分页
     */
    private boolean enablePage = true;

    public PageableListView(Context context) {
        super(context);
        init();
    }

    public PageableListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void setIsLoading(boolean b) {
        this.isLoading = b;
    }

    public boolean isLoading() {
        return isLoading;
    }

    /**
     * 加载结束
     */
    public void loadStart() {
        setIsLoading(true);
    }

    /**
     * 加载完成
     */
    public void loadFinished() {
        setIsLoading(false);
    }

    public void setOnScrollPageListener(ScrollPageListener listener) {
        pageListener = listener;
    }

    public View createPagerFooterView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_pager_footer,
                null);
        return view;
    }

    @Override
    public void setAdapter(ListAdapter adapter) {
        super.setAdapter(adapter);
    }

    /**
     * 重置
     */
    public void reset() {
        enablePage = true;
        removePageFooterView();
    }

    public void setPageEnable(boolean b) {
        this.enablePage = b;
    }

    /**
     * 添加分页footer
     */
    public void addPageFooterView() {
        if (pagerFooter == null) {
            pagerFooter = createPagerFooterView(getContext());
            addFooterView(pagerFooter);
        }
    }

    /**
     * 移除分页footer
     */
    public void removePageFooterView() {
        if (pagerFooter != null) {
            removeFooterView(pagerFooter);
            pagerFooter = null;
        }
    }

    /**
     * 初始化。
     */
    public void init() {
        this.setOnScrollListener(new OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (!isLoading && mLastItemVisible
                        && scrollState == OnScrollListener.SCROLL_STATE_IDLE) {
                    if (pageListener != null && enablePage) {
                        pageListener.onPage();
                        addPageFooterView();
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
                                 int totalItemCount) {
                if (!isLoading && (totalItemCount > 0)
                        && (firstVisibleItem + visibleItemCount >= totalItemCount - 1))
                    mLastItemVisible = true;
                else
                    mLastItemVisible = false;
            }
        });

    }


    /**
     * 分页监听器。
     * <p/>
     * Created by John on 2014-7-18
     */
    public interface ScrollPageListener {
        public void onPage();
    }

}
