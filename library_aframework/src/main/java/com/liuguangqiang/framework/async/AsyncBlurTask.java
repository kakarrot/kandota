package com.liuguangqiang.framework.async;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;

import com.liuguangqiang.framework.utils.image.BlurUtils;

/**
 * A asynchronous task for blurring a bitmap.
 */
public class AsyncBlurTask extends AsyncTask<Bitmap, Integer, Bitmap> {

    private OnBlurListener onBlurListener;

    private Context context;

    private int radius = 10;

    public AsyncBlurTask(Context context, OnBlurListener listener) {
        onBlurListener = listener;
        this.context = context;
    }

    public AsyncBlurTask(Context context, OnBlurListener listener, int radius) {
        onBlurListener = listener;
        this.context = context;
        this.radius = radius;
    }

    @Override
    protected Bitmap doInBackground(Bitmap... params) {
        if (params == null) throw new IllegalArgumentException("Can't blur a null bitmap");

        return BlurUtils.blur(context, params[0], radius);
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        if (onBlurListener != null) onBlurListener.onSuccess(result);
    }

    public interface OnBlurListener {
        void onSuccess(Bitmap bm);
    }

}
