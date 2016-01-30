package com.xulee.kandota.utils;

import android.content.Context;

import com.google.gson.Gson;
import com.liuguangqiang.framework.utils.IntentUtils;
import com.liuguangqiang.framework.utils.PreferencesUtils;
import com.liuguangqiang.framework.utils.StringUtils;
import com.xulee.kandota.constant.MyActions;
import com.xulee.kandota.entity.Auth;
import com.xulee.kandota.entity.User;

/**
 * Created by Eric on 14/11/22.
 */
public class LoginManager {

    private static final String LOGIN = "login";

    private static final String KEY_AUTH = "auth";

    private static final String KEY_USER = "user";

    private static User mUser;

    private static Auth mAuth;

    public static boolean isLogin() {
        return mUser != null;
    }

    public static boolean isLogout = false;

    public static void logout(Context context) {
        mUser = null;
        mAuth = null;
        isLogout = true;
        saveUser(context, null);
        saveAuth(context, null);
        IntentUtils.sendBroadcast(context, MyActions.UPDATE_BOOK_SHELF);
    }

    public static void init(Context context) {
        mUser = getUser(context);
        mAuth = getAuth(context);
    }

    public static void saveAuth(Context context, Auth auth) {
        if (auth != null) {
            mAuth = auth;
            Gson gson = new Gson();
            String json = gson.toJson(auth);
            PreferencesUtils.putString(context, LOGIN, KEY_AUTH, json);
        } else {
            PreferencesUtils.putString(context, LOGIN, KEY_AUTH, "");
        }
    }

    public static Auth getAuth(Context context) {
        Auth auth = null;
        String authJson = PreferencesUtils.getString(context, LOGIN, KEY_AUTH);
        if (!StringUtils.isEmptyOrNull(authJson)) {
            Gson gson = new Gson();
            auth = gson.fromJson(authJson, Auth.class);
        }
        return auth;
    }

    public static void saveUser(Context context, User user) {
        if (user != null) {
            mUser = user;
            Gson gson = new Gson();
            String json = gson.toJson(user);
            PreferencesUtils.putString(context, LOGIN, KEY_USER, json);
            IntentUtils.sendBroadcast(context, MyActions.UPDATE_BOOK_SHELF);
        } else {
            PreferencesUtils.putString(context, LOGIN, KEY_USER, "");
        }
    }

    public static User getUser(Context context) {
        User user = null;
        String userJson = PreferencesUtils.getString(context, LOGIN, KEY_USER);
        if (!StringUtils.isEmptyOrNull(userJson)) {
            Gson gson = new Gson();
            user = gson.fromJson(userJson, User.class);
        }
        return user;
    }

    public static User getUser() {
        return mUser;
    }

    public static Auth getAuth() {
        return mAuth;
    }


}
