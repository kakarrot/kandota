package com.xulee.kandota.entity;

import com.xulee.kandota.entity.base.BaseResponse;

/**
 * Created by LX on 2016/4/28.
 */
public class SignResponse extends BaseResponse{

    public SignData data;

    public class SignData {
        public String total_credits;
        public String add_credits;
    }
}
