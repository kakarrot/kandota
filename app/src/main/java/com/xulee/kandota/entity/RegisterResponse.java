package com.xulee.kandota.entity;

import com.liuguangqiang.framework.utils.GsonUtils;
import com.xulee.kandota.entity.base.BaseResponse;
import com.xulee.kandota.utils.DESUtils;

/**
 * 注册返回信息
 * Created by LX on 2016/4/28.
 */
public class RegisterResponse extends BaseResponse {

    public RegisterData data;

    public class RegisterData {
        public String userinfo; //加密过后的字符串

        //获取解密过后的String
        public String getDecryptUserInfo() {
            return DESUtils.decrypt(userinfo);
        }

        //获取注册后的用户信息
        public RegisterUserInfo getRegisterUserInfo() {
            String jsonStr = getDecryptUserInfo();
            RegisterUserInfo userInfo = GsonUtils.getModel(jsonStr, RegisterUserInfo.class);
            return userInfo;
        }

        public class RegisterUserInfo {
            public int uid;
            public String username;
            public String password;
        }
    }

}
