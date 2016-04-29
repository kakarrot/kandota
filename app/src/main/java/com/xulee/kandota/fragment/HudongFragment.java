package com.xulee.kandota.fragment;

import android.widget.AbsListView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.flyco.banner.widget.Banner.base.BaseBanner;
import com.liuguangqiang.android.mvp.Presenter;
import com.xulee.kandota.R;
import com.xulee.kandota.adapter.base.BaseAdapterHelper;
import com.xulee.kandota.adapter.base.QuickAdapter;
import com.xulee.kandota.base.BaseFragment;
import com.xulee.kandota.constant.Constants;
import com.xulee.kandota.entity.BannerItem;
import com.xulee.kandota.entity.HudongResponse;
import com.xulee.kandota.mvp.presenter.HudongPresenter;
import com.xulee.kandota.mvp.ui.HudongUi;
import com.xulee.kandota.mvp.ui.HudongUiCallback;
import com.xulee.kandota.view.banner.SimpleImageBanner;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnItemClick;


public class HudongFragment extends BaseFragment implements HudongUi {

    @Bind(R.id.sib_the_most_comlex_usage)
    SimpleImageBanner banner; //轮播图

    @Bind(R.id.gv_hudong)
    GridView gv_hudong; //互动元素

    private HudongUiCallback callback;

    private QuickAdapter adapter;

    private ArrayList<BannerItem> bannerList;
    private List<HudongResponse.ThemeEntity> themeEntityList;

    @Override
    protected int getContentView() {
        return R.layout.fragment_hudong;
    }

    @Override
    public void initViews() {
        adapter = new QuickAdapter<HudongResponse.ThemeEntity>(getActivity(), R.layout.item_hudong_theme) {
            @Override
            protected void convert(BaseAdapterHelper helper, HudongResponse.ThemeEntity item) {
                int itemWidth = Constants.WIDTH / 2;
                int itemHeight = (int) (itemWidth * 166 * 1.0f / 360);
                ImageView iv = helper.getView(R.id.iv_hudong_theme);
                iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
                iv.setLayoutParams(new AbsListView.LayoutParams(itemWidth, itemHeight));
                helper.setImageUrl(R.id.iv_hudong_theme, item.img);
            }
        };
        gv_hudong.setAdapter(adapter);
    }

    @Override
    public void setUiCallback(HudongUiCallback callback) {
        this.callback = callback;
    }

    @Override
    public Presenter setPresenter() {
        return new HudongPresenter(getActivity(), this);
    }

    @Override
    public void showBanner(final List<HudongResponse.DataEntity> dataEntityList) {
        if(null == bannerList){
            bannerList = new ArrayList<>();
        }
        bannerList.clear();
        for (HudongResponse.DataEntity dataEntity : dataEntityList) {
            BannerItem item = new BannerItem();
            item.imgUrl = dataEntity.img;
            item.title = dataEntity.title;
            item.url = dataEntity.link_content;
            bannerList.add(item);
        }
        banner.setSource(bannerList);
        banner.setOnItemClickL(new BaseBanner.OnItemClickL() {
            @Override
            public void onItemClick(int position) {
                callback.onBannerItemClick(dataEntityList.get(position));
            }
        });
        if (bannerList.size() > 0) {
            banner.startScroll();
        }
    }

    @Override
    public void showThemeList(List<HudongResponse.ThemeEntity> themeEntities) {
        if (null == themeEntityList) {
            themeEntityList = new ArrayList<>();
        }
        themeEntityList.clear();
        themeEntityList.addAll(themeEntities);
        adapter.clear();
        adapter.addAll(themeEntities);
        adapter.notifyDataSetChanged();
    }


    @OnItemClick(R.id.gv_hudong)
    void onThemeItemClick(int position) {
        callback.onThemeItemClick(themeEntityList.get(position));
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
        if (null != banner && null != bannerList && bannerList.size() > 0) {
            banner.startScroll();
        }
    }
}
