package com.xulee.kandota.ui.dialogs;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.liuguangqiang.framework.adapter.MyBaseAdapter;
import com.xulee.kandota.R;


public class DialogSimpleAdapter extends MyBaseAdapter<String> {

    private ViewHolder viewholder;

    public DialogSimpleAdapter(Context c, List<String> list) {
        this.context = c;
        this.data = list;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            viewholder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_dialog_simple, null);
            viewholder.tvTitle = (TextView) convertView.findViewById(R.id.item_tv_title);
            convertView.setTag(viewholder);
        } else {
            viewholder = (ViewHolder) convertView.getTag();
        }
        viewholder.tvTitle.setText(data.get(position));
        return convertView;
    }

    private static class ViewHolder {
        TextView tvTitle;
    }
}
