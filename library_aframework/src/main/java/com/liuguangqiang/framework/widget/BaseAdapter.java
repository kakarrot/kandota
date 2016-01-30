package com.liuguangqiang.framework.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * MyBaseAdapter
 * <p/>
 * Created by Eric
 */
public abstract class BaseAdapter<T> extends android.widget.BaseAdapter {

    public Context context;

    public List<T> data;

    public final LayoutInflater layoutInflater;

    public BaseAdapter(Context c, List<T> data) {
        this.layoutInflater = LayoutInflater.from(c);
        this.context = c;
        this.data = data;
    }

    @Override
    public int getCount() {
        return (data == null || data.isEmpty()) ? 0 : data.size();
    }

    @Override
    public Object getItem(int position) {
        return (data == null || data.isEmpty()) ? null : data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    public void clear() {
        if (data != null && !data.isEmpty()) {
            data.clear();
            notifyDataSetChanged();
        }
    }

    public void add(T t) {
        if (data != null) {
            data.add(t);
            notifyDataSetChanged();
        }
    }

    public void add(List<T> list) {
        if (data != null) {
            data.addAll(list);
            notifyDataSetChanged();
        }
    }

    public void addToTop(T t) {
        if (data != null) {
            data.add(0, t);
            notifyDataSetChanged();
        }
    }

    public void addToTop(List<T> list) {
        if (data != null) {
            data.addAll(0, list);
            notifyDataSetChanged();
        }
    }

    public void remove(int position) {
        if (data != null && !data.isEmpty()) {
            data.remove(position);
            notifyDataSetChanged();
        }
    }

    public void remove(List<T> list) {
        if (data != null && !data.isEmpty()) {
            data.removeAll(list);
            notifyDataSetChanged();
        }
    }

}
