package com.xulee.kandota.entity;

import com.xulee.kandota.entity.base.BaseResponse;

/**
 * 登录/注册返回数据
 * Created by LX on 2016/4/21.
 */
public class LoginResponse extends BaseResponse {


    /**
     * uid : 42
     * username : 9876
     * mobile : 13456789876
     * nickname : (^-^)
     */

    public DataEntity data;
    /**
     * data : {"uid":"42","username":"9876","mobile":"13456789876","nickname":"(^-^)"}
     * access_token : 1c7f73d0a0a60e7ceecf1b7e74408ae9
     */

    public String access_token;

    public static class DataEntity {
        public String uid;
        public String username;
        public String mobile;
        public String nickname;
    }
}
