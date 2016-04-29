package com.xulee.kandota.utils;

import com.xulee.kandota.utils.des.DES;

/**
 * DES 加解密工具类
 * Created by LX on 2016/4/28.
 */
public class DESUtils {

    /**
     * 获取解密后的数据
     * @return
     */
    public static String decrypt(String data){
        DES crypt = new DES(DES.key);
        String result = new String();
        try {
            result = crypt.decrypt(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 对请求字符串进行加密
     *
     * @param data
     * @return
     */
    public static String encrypt(String data) {
        DES des = new DES(DES.key);
        String encryptQParam = null;
        try {
            encryptQParam = des.encrypt(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        encryptQParam = encryptQParam.replace("+", "_cutv_");
        return encryptQParam;
    }
}
