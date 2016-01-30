package com.xulee.kandota.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.xulee.kandota.R;


/**
 * ListView-滚动分页加载。
 * <p/>
 * Created by Eric on 2014-7-18
 */
public class ScrollPageListView extends ListView {

    private boolean isLoading = false;
    private boolean mLastItemVisible = false;
    private ScrollPageListener pageListener;
    private View pagerFooter;

    /**
     * 是否允许分页
     */
    private boolean enablePage = true;

    /**
     * 总给需要加载的数据的总数
     */
    private int total = 0;

    private boolean isShowFooter = true;

    public void setIsShowFooter(boolean isShow) {
        isShowFooter = isShow;
    }

    public ScrollPageListView(Context context) {
        super(context);
        init();
    }

    public ScrollPageListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    /**
     * 设置数据的总数
     *
     * @param t
     */
    public void setTotal(int t) {
        this.total = t;
    }

    private void setIsLoading(boolean b) {
        this.isLoading = b;
    }

    /**
     * 加载结束
     */
    public void loadStart() {
        setIsLoading(true);
    }

    /**
     * 加载完成
     *
     * @param currentSize 当前显示的数量。
     */
    public void loadFinished(int currentSize) {
        setIsLoading(false);
        if (currentSize == total) {
            removePageFooterView();
            enablePage = false;
        }
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
        if (isShowFooter)
            addPageFooterView();
        super.setAdapter(adapter);
    }

    /**
     * 重置
     */
    public void reset() {
        enablePage = true;
        addPageFooterView();
    }

    /**
     * 添加分页footer
     */
    private void addPageFooterView() {
        if (pagerFooter == null) {
            pagerFooter = createPagerFooterView(getContext());
            addFooterView(pagerFooter);
        }
    }

    /**
     * 移除分页footer
     */
    private void removePageFooterView() {
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
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
                                 int totalItemCount) {
                if (!isLoading && (totalItemCount > 0)
                        && (firstVisibleItem + visibleItemCount >= totalItemCount))
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
