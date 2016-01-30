package com.liuguangqiang.framework.utils;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Vibrator;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * CommonUtils
 * <p/>
 * Created by Eric on 2014-5-19.
 */
public class CommonUtils {

    @Deprecated
    public static void dismissSoftKeyborad(Activity context) {
        if (context == null) return;
        ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE))
                .hideSoftInputFromWindow(context.getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public static void hideSoftKeyborad(Activity context) {
        if (context == null) return;
        ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE))
                .hideSoftInputFromWindow(context.getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public static void showSoftKeyborad(Activity context, EditText et) {
        if (context == null) return;
        InputMethodManager inputMethodManager =
                (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(et, InputMethodManager.SHOW_FORCED);
    }

    public static void toggleSoftKeyborad(Activity context) {
        if (context == null) return;
        InputMethodManager inputMethodManager =
                (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public static void vibrate(Context context, long milliseconds) {
        Vibrator vib = (Vibrator) context.getSystemService(Service.VIBRATOR_SERVICE);
        vib.vibrate(milliseconds);
        if (vib != null) {
            vib.cancel();
        }
    }

    public static void vibrate(Context context, long[] pattern, int repeat) {
        Vibrator vib = (Vibrator) context
                .getSystemService(Service.VIBRATOR_SERVICE);
        vib.vibrate(pattern, repeat);
        if (vib != null) {
            vib.cancel();
        }
    }

    public static void playNotificationVoice(Context context, int resId) {
        MediaPlayer mediaPlayer;
        try {
            final AudioManager audioManager = (AudioManager) context
                    .getSystemService(Context.AUDIO_SERVICE);
            if (audioManager.getStreamVolume(AudioManager.STREAM_NOTIFICATION) != 0) {
                mediaPlayer = MediaPlayer.create(context, resId);
                if (mediaPlayer != null) {
                    mediaPlayer.stop();
                }
                mediaPlayer
                        .setAudioStreamType(AudioManager.STREAM_NOTIFICATION);
                mediaPlayer.setLooping(false);
                mediaPlayer.setOnCompletionListener(new OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        if (mp != null && mp.isPlaying()) {
                            mp.stop();
                        }
                        mp.release();
                    }
                });
                mediaPlayer.prepare();
                mediaPlayer.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
