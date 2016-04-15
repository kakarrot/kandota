package com.xulee.kandota.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xulee.kandota.R;

/**
 * 自定义标题栏
 * Created by LX on 2016/4/13.
 */
public class TitleBar extends LinearLayout{

    private ImageView iv_left;
    private TextView tv_title;
    private ImageView iv_right;

    public TitleBar(Context context) {
        super(context);
        init(context);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.view_titlebar, this, true);

        iv_left = (ImageView) findViewById(R.id.iv_left);
        tv_title = (TextView) findViewById(R.id.tv_title);
        iv_right = (ImageView) findViewById(R.id.iv_right);
    }


    /**
     * 设置标题
     * @param stringRes
     */
    public void setTitle(@NonNull int stringRes){
        tv_title.setText(stringRes);
    }


    public void setTitle(@NonNull String titleStr){
        tv_title.setText(titleStr);
    }

    /**
     * 设置左边图标
     * @param imgRes
     */
    public void setLeftIcon(@NonNull int imgRes) {
        iv_left.setImageResource(imgRes);
    }

    /**
     * 设置左点击事件
     * @param listener
     */
    public void setLeftAction(@NonNull OnClickListener listener){
        iv_left.setOnClickListener(listener);
    }

    /**
     * 设置左图标及点击事件
     * @param imgRes
     * @param listener
     */
    public void setLeftIconAndAction(@NonNull int imgRes,@NonNull OnClickListener listener){
        setLeftIcon(imgRes);
        setLeftAction(listener);
    }

    /**
     * 设置右图标
     * @param imgRes
     */
    public void setRightIcon(@NonNull int imgRes){
        iv_right.setImageResource(imgRes);
    }

    /**
     * 设置右点击事件
     * @param listener
     */
    public void setRightAction(@NonNull OnClickListener listener){
        iv_right.setOnClickListener(listener);
    }

    /**
     * 设置右图标及点击事件
     * @param imgRes
     * @param listener
     */
    public void setRightIconAndAction(@NonNull int imgRes,@NonNull OnClickListener listener){
        setRightIcon(imgRes);
        setRightAction(listener);
    }

    /**
     *隐藏左图标
     */
    public void hideLeft(){
        iv_left.setVisibility(View.INVISIBLE);
    }

    /**
     *显示左图标
     */
    public void showLeft(){
        iv_left.setVisibility(View.VISIBLE);
    }

    /**
     * 隐藏右图标
     */
    public void hideRight(){
        iv_right.setVisibility(View.INVISIBLE);
    }

    /**
     * 显示右图标
     */
    public void showRight(){
        iv_right.setVisibility(View.INVISIBLE);
    }
}
