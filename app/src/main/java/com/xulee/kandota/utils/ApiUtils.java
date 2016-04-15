package com.xulee.kandota.utils;

import com.liuguangqiang.framework.utils.Logs;
import com.xulee.kandota.constant.Constants;
import com.xulee.kandota.utils.des.DES;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * API工具类，生产不同的API地址。
 */
public class ApiUtils {

    public static final String URL_BASE_YAO = "http://yao.cutv.com/plugin.php?id=cutv_shake:";
    //开机广告
    public static final String URL_GET_ADS = URL_BASE_YAO + "api_get_front_in_ad";

    //获取线上最新版本信息
    public static final String URL_GET_VERSION = URL_BASE_YAO + "api_get_version";

    //获取资讯页面数据
    public final static String URL_GET_NEWS = "http://sttv.img.cutv.com/getcontentlist_%s_%s.json";

    //获取互动页面数据
    public final static String URL_GET_HUDONG = URL_BASE_YAO + "api_get_rediantuijian";

    //获取互动标识符 //http://yao.cutv.com/checkup.php?pos=tv
    public final static String URL_GET_HUDONG_TAG = "http://yao.cutv.com/checkup.php?pos=tv";

    /**
     * 获取开机广告
     * @return
     */
    public static String getLanuchAdsUrl(){
        String qParam = "source=yaoyiyao&cflag=" + Constants.CFLAG;
        return getEncryptUrl(qParam);
    }

    /**
     * 获取线上版本
     * @return
     */
    public static String getVersionUrl(){
        String qParam = "device=android&source=" + Constants.CFLAG + "&time_str="
                + Long.toString(System.currentTimeMillis());
        return getEncryptUrl(qParam);
    }

    /**
     * 获取资讯页面数据
     * @param fid
     * @param page
     * @return
     */
    public static String getNewsUrl(String fid, int page){
        return String.format(URL_GET_NEWS, fid, page);
    }

    /**
     * 获取互动页面数据Url
     * @return
     */
    public static String getHudongUrl(){
        String qParam = "source=yaoyiyao&device=android&v=1&cflag=" + Constants.CFLAG + "&time_str="
                + Long.toString(System.currentTimeMillis());
        Logs.i("加密前：" + qParam);
        return getEncryptUrl(qParam);
    }


    /**
     * 获取互动标识符Url
     * @return
     */
    public static String getHudongTagUrl(){
        String qParam = "pos=tv";
        return getEncryptUrl(qParam);
    }

    /**
     * 对请求字符串进行加密
     * @param param
     * @return
     */
    public static String getEncryptUrl(String param){
        DES des = new DES(DES.key);
        String encryptQParam = null;
        try {
            encryptQParam = des.encrypt(param);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        encryptQParam = encryptQParam.replace("+", "_cutv_");
        return encryptQParam;
    }

}
