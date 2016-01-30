package com.xulee.kandota.utils;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.liuguangqiang.framework.utils.PreferencesUtils;
import com.xulee.kandota.R;
import com.xulee.kandota.constant.Constants;

/**
 * 阅读主题工具类。
 * <p/>
 * Created by Eric on 2014年7月2日
 */
public class ThemeUtils {

    /**
     * 是否为夜间模式
     */
    public static boolean isNightModel = false;

//    /**
//     * 夜间模式 value:{@value}
//     */
//    public static final int NIGHT = 4;

    /**
     * 普通 value:{@value}
     */
    public static final int NORMAL = 0;

    /**
     * 黄色 value:{@value}
     */
    public static final int YELLOW = 1;

    /**
     * 绿色 value:{@value}
     */
    public static final int GREEN = 2;

    /**
     * 皮革 value:{@value}
     */
    public static final int LEATHER = 3;

    private static final String SAVE_KEY = "reader_theme";

    private static final String SAVE_NIGHT_KEY = "reader_theme_night";

    /**
     * 当前主题，默认为：普通。
     */
    private static int currentTheme = NORMAL;

    /**
     * 设置阅读器的主题。
     */
    public static void setReaderTheme(int theme, View view) {
        switch (theme) {
            case NORMAL:
                view.setBackgroundResource(R.drawable.theme_white_bg);
                break;
            case YELLOW:
                view.setBackgroundResource(R.drawable.theme_yellow_bg);
                break;
            case GREEN:
                view.setBackgroundResource(R.drawable.theme_green_bg);
                break;
            case LEATHER:
//                view.setBackgroundResource(R.drawable.theme_leather_bg);
                break;
        }
    }

    /**
     * 初始化主题。
     *
     * @param context
     */
    public static void initTheme(Context context) {
        int theme = getTheme(context);
        if (theme > -1) {
            currentTheme = theme;
        }
        isNightModel = isNightodel(context);
    }

    /**
     * 获取当前的主题。
     * context
     *
     * @param
     * @return
     */
    public static int getCurrentTheme() {
        return currentTheme;
    }

    /**
     * 设置阅读主题。
     * <p/>
     *
     * @param theme
     */
    public static void setTheme(Context context, int theme) {
        currentTheme = theme;
        PreferencesUtils.putInt(context, Constants.PRE_SAVE_NAME, SAVE_KEY, theme);
    }

    /**
     * 获取主题。
     *
     * @param context
     */
    private static int getTheme(Context context) {
        return PreferencesUtils.getInt(context, Constants.PRE_SAVE_NAME, SAVE_KEY);
    }

    /**
     * 设置夜间模式
     *
     * @param context
     * @param isNight true：夜间模式，false：正常模式
     */
    public static void setNightModle(Context context, boolean isNight) {
        isNightModel = isNight;
        int value = 0;
        if (isNightModel) value = 1;
        PreferencesUtils.putInt(context, Constants.PRE_SAVE_NAME, SAVE_NIGHT_KEY, value);
    }

    /**
     * 是否为夜间模式
     *
     * @param context
     * @return
     */
    private static boolean isNightodel(Context context) {
        return PreferencesUtils.getInt(context, Constants.PRE_SAVE_NAME, SAVE_NIGHT_KEY, 0) == 1;
    }

    /**
     * 返回是否为夜间模式
     *
     * @return
     */
    public static boolean getIsNightModel() {
        return isNightModel;
    }

    /**
     * 设置主题背，用于区分夜间模式和普通模式
     *
     * @param view
     */
    public static void setThemeBg(View view) {
        if (view == null) return;
        if (isNightModel) {
            view.setBackgroundColor(view.getContext().getResources().getColor(R.color.main_night_bg_color));
        } else {
            view.setBackgroundColor(view.getContext().getResources().getColor(R.color.main_bg_color));
        }
    }

    /**
     * 设置底部菜单背景
     *
     * @param view
     */
    public static void setBottomMenuBg(View view) {
        if (view == null) return;
        if (isNightModel) {
            view.setBackgroundColor(view.getContext().getResources().getColor(R.color.main_night_bg_color));
        } else {
            view.setBackgroundColor(view.getContext().getResources().getColor(R.color.main_bg_color));
        }
    }

    /**
     * 设置字体颜色，用于区分夜间模式和普通模式
     *
     * @param tv
     */
    public static void setTextColor(TextView tv) {
        if (tv == null) return;
        if (isNightModel)
            tv.setTextColor(tv.getResources().getColor(R.color.tv_title_night_color));
        else tv.setTextColor(tv.getResources().getColor(R.color.tv_content_color_normal));
    }

    public static void setThemeSecondBg(View view) {
        if (view == null) return;
        if (isNightModel) {
            view.setBackgroundColor(view.getContext().getResources().getColor(R.color.main_night_second_bg_color));
        } else {
            view.setBackgroundColor(view.getContext().getResources().getColor(R.color.main_second_bg_color));
        }
    }

    public static void setThemeLine(View view) {
        if (view == null) return;
        if (isNightModel) {
            view.setBackgroundColor(view.getContext().getResources().getColor(R.color.main_night_shadow_color));
        } else {
            view.setBackgroundColor(view.getContext().getResources().getColor(R.color.main_shadow_color));
        }
    }

}
