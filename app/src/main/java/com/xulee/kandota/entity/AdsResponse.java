package com.xulee.kandota.entity;

import com.xulee.kandota.entity.base.BaseResponse;

/**
 * Created by LX on 2016/3/14.
 */
public class AdsResponse extends BaseResponse{

    public AdsData ad;

    public class AdsData {
        public String image;
        public String duration;
    }
}
