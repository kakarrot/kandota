package com.xulee.kandota.entity;


import com.xulee.kandota.entity.base.BaseResponse;

/**
 * Created by LX on 16/04/19.
 */
public class UserResponse extends BaseResponse {
    /**
     * uid : 42
     * username : 9876
     * nickname : (^-^)
     * credits : 1139
     * issign : 0
     * avator : http://yao.cutv.com/uc_server/avatar.php?uid=42&size=middle&random=1461024248
     * token : ce24d0c9ed4e9b1117ee4306c057cd70
     */

    public User data;

    public class User {
        public int uid;     //用户id
        public String username; //用户名/手机号
        public String nickname; //昵称
        public String credits;  //积分
        public String issign;   //是否签到
        public String avator;   //头像
        public String token;    //token
    }
}