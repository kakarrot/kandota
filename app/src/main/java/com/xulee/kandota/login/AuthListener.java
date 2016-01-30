package com.xulee.kandota.login;


public interface AuthListener {

    void onStart();

    void onFailure();

    void authSuccess(String uid, String username, String avatarUrl, LoginType loginType);

}
