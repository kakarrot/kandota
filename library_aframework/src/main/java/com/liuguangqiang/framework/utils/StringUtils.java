package com.liuguangqiang.framework.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9])|(17[0-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    public static boolean isChar(String mobiles) {
        Pattern p = Pattern.compile("[a-zA-Z]");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    public static boolean isNUM(String mobiles) {
        Pattern p = Pattern.compile("[1-9]");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    public static boolean isZeroString(String str) {
        if (str == null || "".equals(str)) {
            return true;
        }
        Pattern p = Pattern.compile("[0]+");
        Matcher m = p.matcher(str);
        return m.matches();
    }

    public static boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        int sz = str.length();
        for (int i = 0; i < sz; i++) {
            if (Character.isDigit(str.charAt(i)) == false && str.charAt(i) != '.') {
                return false;
            }
        }
        return true;
    }

    private static final String format = "%s%s%s=%s";

    public static String appendQueryParams(String url, String key, String value) {
        String tag = url.contains("?") ? "&" : "?";
        return String.format(format, url, tag, key, value);
    }

}
