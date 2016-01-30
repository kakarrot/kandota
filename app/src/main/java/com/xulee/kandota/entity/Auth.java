package com.xulee.kandota.entity;


import com.google.gson.annotations.SerializedName;

/**
 * Created by Eric on 14/11/25.
 */
public class Auth {

    @SerializedName("user_id")
    public String userId;

    @SerializedName("auth_token")
    public String authToken;

}
