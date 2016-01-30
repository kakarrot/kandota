package com.xulee.kandota.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.liuguangqiang.framework.adapter.MyBaseAdapter;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.xulee.kandota.R;
import com.xulee.kandota.entity.Movie;
import com.xulee.kandota.utils.ImageLoaderUtils;

/**
 * Created by LX on 2016/1/28.
 */
public class MoviesAdapter extends MyBaseAdapter<Movie> {

    private DisplayImageOptions mOptions;

    public MoviesAdapter(Context context, List<Movie> data) {
        this.data = data;
        this.context = context;
        mOptions = ImageLoaderUtils.createOptions(R.drawable.icon_place_holder);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_movie, null);
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.item_tv_title);
            viewHolder.tvDesc = (TextView) convertView.findViewById(R.id.item_tv_desc);
            viewHolder.tvAuthor = (TextView) convertView.findViewById(R.id.item_tv_author);
            viewHolder.ivCover = (ImageView) convertView.findViewById(R.id.item_iv_cover);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Movie model = data.get(position);
        if (model != null) {
            viewHolder.tvTitle.setText(model.getTitle().trim());
            viewHolder.tvDesc.setText(model.getChannel().trim());
            viewHolder.tvAuthor.setText(model.getAuthor().trim());
            ImageLoaderUtils.display(model.getImg(), viewHolder.ivCover, mOptions);
        }
        return convertView;
    }

    private class ViewHolder {
        public TextView tvTitle;
        public TextView tvAuthor;
        public TextView tvDesc;
        public ImageView ivCover;
    }

}
