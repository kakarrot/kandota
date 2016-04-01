package com.xulee.kandota.entity;

/**
 * Created by LX on 2016/3/14.
 */
public class AdsResponse {
    public String status;
    public String message;
    public AdsData ad;

    public class AdsData {
        public String image;
        public String duration;
    }
}
