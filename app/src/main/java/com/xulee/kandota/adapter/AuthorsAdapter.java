package com.xulee.kandota.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.liuguangqiang.framework.adapter.MyBaseAdapter;
import com.liuguangqiang.framework.utils.DisplayUtils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.xulee.kandota.R;
import com.xulee.kandota.entity.Author;
import com.xulee.kandota.utils.ImageLoaderUtils;

import java.util.List;

/**
 * Created by LX on 2016/1/28.
 */
public class AuthorsAdapter extends MyBaseAdapter<Author> {

    private DisplayImageOptions mOptions;

    public AuthorsAdapter(Context context, List<Author> data) {
        this.data = data;
        this.context = context;
        mOptions = ImageLoaderUtils.createOptions(R.drawable.ic_default_avatar);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_author, null);
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.item_tv_title);
            viewHolder.ivCover = (ImageView) convertView.findViewById(R.id.item_iv_cover);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Author model = data.get(position);
        if (model != null) {
            if(null != model.getName()) viewHolder.tvTitle.setText(model.getName().trim());
            if(null != viewHolder.ivCover)
                ImageLoaderUtils.display(model.getAvtar(), viewHolder.ivCover, mOptions);
        }
        return convertView;
    }

    private class ViewHolder {
        public TextView tvTitle;
        public ImageView ivCover;
    }

}
