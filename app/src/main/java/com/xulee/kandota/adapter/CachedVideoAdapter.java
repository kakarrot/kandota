package com.xulee.kandota.adapter;

import android.content.Context;
import com.xulee.kandota.R;
import com.xulee.kandota.adapter.base.BaseAdapterHelper;
import com.xulee.kandota.adapter.base.QuickAdapter;
import com.youku.service.download.DownloadInfo;
import java.util.List;

/**
 * Created by LX on 2016/2/2.
 */
public class CachedVideoAdapter extends QuickAdapter<DownloadInfo> {
    public CachedVideoAdapter(Context context, int layoutResId, List<DownloadInfo> data) {
        super(context, layoutResId, data);
    }

    @Override
    protected void convert(BaseAdapterHelper helper, DownloadInfo item) {
        helper.setText(R.id.item_tv_title, item.title)
                .setText(R.id.item_tv_author, item.cats)
                .setImageUrl(R.id.item_iv_cover, item.imgUrl);
    }
}
