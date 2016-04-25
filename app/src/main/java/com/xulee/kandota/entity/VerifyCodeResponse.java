package com.xulee.kandota.entity;

import com.xulee.kandota.entity.base.BaseResponse;
import com.xulee.kandota.utils.des.DES;

/**
 * 获取验证码返回数据（图片验证码/短信验证码通用）
 * Created by LX on 2016/4/20.
 */
public class VerifyCodeResponse extends BaseResponse {

    /**
     * 图片验证码数据：
     * {
     "status": "ok",
     "message": "request ok",
     "data": "VpQMjih4l9E="
     }
     */

    /**
     * 短信验证码数据：
     * {
     "status": "ok",
     "message": "已发送",
     "data": ""
     }
     */
    public String data;


    /**
     * 获取解密后的数据（图片验证码）
     * @return
     */
    public String getDecryptData(){
        DES crypt = new DES(DES.key);
        String result = new String();
        try {
            result = crypt.decrypt(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
