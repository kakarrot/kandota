package com.xulee.kandota.fragment;

import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.culiu.mhvp.core.MagicHeaderUtils;
import com.culiu.mhvp.core.MagicHeaderViewPager;
import com.culiu.mhvp.core.tabs.com.astuetz.PagerSlidingTabStrip;
import com.xulee.kandota.R;
import com.xulee.kandota.adapter.NewsPagerAdapter;
import com.xulee.kandota.base.BaseFragment;
import com.xulee.kandota.entity.BannerItem;
import com.xulee.kandota.entity.NewsResponse;
import com.xulee.kandota.listeners.NewsRefreshListener;
import com.xulee.kandota.utils.SkipUtils;
import com.xulee.kandota.view.banner.SimpleImageBanner;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 资讯
 * Created by LX on 2016/1/28.
 */
public class NewsFragment extends BaseFragment implements NewsRefreshListener {

    @Bind(R.id.ll_news)
    LinearLayout mhvpParent;

    private MagicHeaderViewPager mMagicHeaderViewPager;

    private NewsPagerAdapter mPagerAdapter;

    private SimpleImageBanner banner; //轮播图
    private List<View> viewList; //轮播图UI集

    private NewsResponse mResponse;

    @Override
    protected int getContentView() {
        return R.layout.fragment_news;
    }

    @Override
    protected void initViews() {
        /* TODO: Initialize MagicHeaderViewPager. Override initTabsArea() to initialize tabs or stable area. */
        mMagicHeaderViewPager = new MagicHeaderViewPager(getActivity()) {
            @Override
            protected void initTabsArea(LinearLayout container) {
                //You can customize your tabStrip or stable area here
                ViewGroup tabsArea = (ViewGroup) LayoutInflater.from(getActivity()).inflate(R.layout.layout_news_tabs, null);

                // TODO: Set height of stable area manually, then it can be calculated.
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        MagicHeaderUtils.dp2px(getActivity(), 35));
                container.addView(tabsArea, lp);

                // some style
                PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) tabsArea.findViewById(R.id.tabs);

                DisplayMetrics dm = getResources().getDisplayMetrics();
                // 设置Tab是自动填充满屏幕的
                tabs.setShouldExpand(true);
                // 设置Tab的分割线是透明的
                tabs.setDividerColorResource(R.color.gray_light);
                // 设置Tab底部线的高度
                tabs.setUnderlineHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1, dm));
                tabs.setUnderlineColor(Color.TRANSPARENT);
                // 设置Tab Indicator的高度
                tabs.setIndicatorHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, dm));
                // 设置Tab标题文字的大小
                tabs.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 14, dm));
                // 设置Tab Indicator的颜色
                tabs.setIndicatorColorResource(R.color.theme_color);
                // 设置正常Tab文字的颜色 (这是我自定义的一个方法)
                tabs.setTextColorResource(R.color.gray_middle);
                tabs.setTabBackground(Color.TRANSPARENT);
                // TODO: These two methods must be called to let magicHeaderViewPager know who is stable area and tabs.
                setTabsArea(tabsArea);
                setPagerSlidingTabStrip(tabs);
            }
        };
        // Note: Cuz tabs or stable area of each ViewPager may not
        // the same. So it's abstract for developers to override.

        //TODO: Just add MagicHeaderViewPager into your Layout. MATCH_PARENT-MATCH_PARENT is recommended.
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );
        mhvpParent.addView(mMagicHeaderViewPager, lp);

        // TODO: Use an OuterPagerAdapter as FragmentPagerAdapter
        mPagerAdapter = new NewsPagerAdapter(getChildFragmentManager());
        mPagerAdapter.setNewsRefreshListener(this);
        //  TODO: Use this method instead of those of PagerSlidingTabStrip or ViewPager.
        mMagicHeaderViewPager.setPagerAdapter(mPagerAdapter);

        // call this if needed
        mMagicHeaderViewPager.setTabOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        // Then you can do anything like before:)

        // add your custom Header content
        setHeader();
    }

    public void setHeader() {
        View header = LayoutInflater.from(getActivity()).inflate(R.layout.layout_news_header, null);
        banner = (SimpleImageBanner) header.findViewById(R.id.sib_the_most_comlex_usage);

        banner.setOnItemClickL(new SimpleImageBanner.OnItemClickL() {
            @Override
            public void onItemClick(int position) {
                if (null == mResponse || null == mResponse.recommend_news) {
                    return;
                }
                NewsResponse.RecommendNews recommendNews = mResponse.recommend_news.get(position);
                SkipUtils.skipToBrowse(getActivity(), recommendNews.title, recommendNews.link_content);
            }
        });
        mMagicHeaderViewPager.addHeaderView(header);
    }

    ArrayList<BannerItem> list;

    @Override
    public void onNewsRefresh(NewsResponse response) {
        if (null == response || null == response.recommend_news || response.recommend_news.size() == 0) {
            return;
        }
        this.mResponse = response;
        list = new ArrayList<>();
        for (int i = 0; i < response.recommend_news.size(); i++) {
            NewsResponse.RecommendNews recommendNews = response.recommend_news.get(i);
            BannerItem item = new BannerItem();
            item.imgUrl = recommendNews.img;
            item.title = recommendNews.title;
            item.url = recommendNews.link_content;
            list.add(item);
        }
        banner.setSource(list);
        if (list.size() > 0) {
            banner.startScroll();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (null != banner) {
            banner.pauseScroll();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (null != banner && null != list && list.size() > 0) {
            banner.startScroll();
        }
    }
}
