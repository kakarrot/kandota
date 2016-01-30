package com.liuguangqiang.framework.utils;

/**
 * StringUtils
 * <p/>
 * Created by Eric on 2014-5-19.
 */
public class StringUtils {

    /**
     * Return whether a String is Empty or null.
     *
     * @param str
     * @return
     */
    public static boolean isEmptyOrNull(String str) {
        if (str == null) return true;

        if (str.trim().equals("")) return true;

        return false;
    }

    private static final String format = "%s%s%s=%s";

    public static String appendQueryParams(String url, String key, String value) {
        String tag = url.contains("?") ? "&" : "?";
        return String.format(format, url, tag, key, value);
    }

}
