package com.liuguangqiang.framework.utils;

/**
 * Created by Eric on 15/5/12.
 */
public class CheckUtils {

    private CheckUtils() {
    }

    public static void checkArgument(boolean expression, Object errorMsg) {
        if (!expression)
            throw new IllegalArgumentException(String.valueOf(errorMsg));
    }

    public static <T> T checkNotNull(T refrence) {
        if (refrence == null) {
            throw new NullPointerException();
        }
        return refrence;
    }

    public static <T> T checkNotNull(T refrence, Object errorMsg) {
        if (refrence == null) {
            throw new NullPointerException(String.valueOf(errorMsg));
        }
        return refrence;
    }

}
