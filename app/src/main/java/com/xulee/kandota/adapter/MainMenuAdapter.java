package com.xulee.kandota.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.liuguangqiang.framework.adapter.MyBaseAdapter;
import com.xulee.kandota.R;
import com.xulee.kandota.entity.MainMenuItem;

import java.util.List;

public class MainMenuAdapter extends MyBaseAdapter<MainMenuItem> {

    private ViewHolder viewHolder;

    public MainMenuAdapter(Context context, List<MainMenuItem> data) {
        this.data = data;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_main_menu, null);
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.item_tv_title);
            viewHolder.ivIcon = (ImageView) convertView.findViewById(R.id.item_iv_icon);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        MainMenuItem model = data.get(position);
        if (model != null) {
            viewHolder.tvTitle.setText(model.title);
            if (model.selected) {
                viewHolder.tvTitle.setTextColor(context.getResources().getColor(R.color.tab_color));
                viewHolder.ivIcon.setBackgroundResource(model.iconSelected);
            } else {
                viewHolder.tvTitle.setTextColor(context.getResources().getColor(R.color.tv_desc_color));
                viewHolder.ivIcon.setBackgroundResource(model.icon);
            }
        }
        return convertView;
    }

    public class ViewHolder {
        public TextView tvTitle;
        public ImageView ivIcon;
    }

}
