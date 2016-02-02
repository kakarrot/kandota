package com.xulee.kandota.act.youku;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.xulee.kandota.R;
import com.xulee.kandota.adapter.CachedVideoAdapter;
import com.xulee.kandota.base.BaseActivity;
import com.youku.service.download.DownloadInfo;
import com.youku.service.download.DownloadManager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 简单展示已经缓存的视频，用户可定制自己的界面
 */
public class CachedActivity extends BaseActivity {
    //展示视频信息的ListView
    @Bind(R.id.list)
    ListView lv;

    //数据Adapter
    private CachedVideoAdapter adapter;

    //通过DownloadManager获取下载视频列表
    private DownloadManager downloadManager;

    //记录当前已经下载的视频列表
    private ArrayList<DownloadInfo> downloadedList_show = new ArrayList<DownloadInfo>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cached);
        ButterKnife.bind(this);
        init();
    }

    private void init(){
        adapter = new CachedVideoAdapter(this, R.layout.item_movie, downloadedList_show);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(listener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //获取已经下载的视频数据
        initData();
        //同志数据更新
        adapter.replaceAll(downloadedList_show);
    }

    /**
     * 获取已下载视频信息
     */
    private void initData() {
        downloadedList_show.clear();

        //通过DownloadManager类的getDownloadedData()接口函数获取已经下载的视频信息
        downloadManager = DownloadManager.getInstance();
        Iterator iter = downloadManager.getDownloadedData().entrySet().iterator();

        while (iter.hasNext()) {
            Entry entry = (Entry) iter.next();
            //视频信息实体类用DownloadInfo表示
            DownloadInfo info = (DownloadInfo) entry.getValue();
            downloadedList_show.add(info);
        }
    }

    @Override
    protected void onDestroy() {
        downloadedList_show.clear();
        finish();
        super.onDestroy();
    }

    /**
     * 通过单击已经下载过的视频进行播放
     */
    private OnItemClickListener listener = new OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            //获取点击item表示视频信息
            DownloadInfo info = downloadedList_show.get(position);

            //跳转到播放界面进行播放，用户可以修改为自己的播放界面
            Bundle extra = new Bundle();
            //点击缓存视频播放时需要传递给播放界面的两个参数
            extra.putBoolean("isFromLocal", true);
            extra.putString("video_id", info.videoid);
            startActivity(PlayerActivity.class, extra);
        }
    };
}
