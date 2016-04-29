package com.xulee.kandota.utils;

import android.content.Context;

import com.liuguangqiang.framework.utils.Logs;
import com.xulee.kandota.constant.Constants;
import com.xulee.kandota.utils.des.DES;

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

    //获取互动标识符
    public final static String URL_GET_HUDONG_TAG = "http://yao.cutv.com/checkup.php?pos=tv";

    //摇积分
    public final static String URL_SHAKE_SCORE = "http://yaoapi.cutv.com/shake.php?";

    //现场摇
    public final static String URL_SHAKE_LIVE = "http://yao.cutv.com/hd/api.php/shake";

    //摇奖品
    public final static String URL_SHAKE_PRIZE = "http://yao.cutv.com/hd/api.php/shakegg";

    //获取图片验证码
    public final static String URL_GET_VERIFY_CODE = "http://yao.cutv.com/uc_yaoyiyao/synchronous.php?action=verify_code";

    //获取动态短信验证码（登录）
    public final static String URL_GET_SMS_CODE_DYNAMIC = "http://yao.cutv.com/uc_yaoyiyao/synchronous.php?action=smscode_dynamic";

    //获取动态短信验证码（注册）
    public final static String URL_REGISTER_GET_SMS_CODE = "http://yao.cutv.com/uc_yaoyiyao/synchronous.php?action=smscode_v4";

    //登录(短信验证码登录)
    public final static String URL_LOGIN = "http://yao.cutv.com/uc_yaoyiyao/synchronous.php?action=verification_dynamic";

    //登录（用户名密码登录，注册后需要用到）
    public final static String URL_LOGIN_OLD = "http://yao.cutv.com/uc_yaoyiyao/synchronous.php?action=verification_new2";

    //注册
    public final static String URL_REGISTER = "http://yao.cutv.com/uc_yaoyiyao/synchronous.php?action=register_new_v4";

    //获取个人信息
    public final static String URL_GET_USER = URL_BASE_YAO + "user_info";

    //签到
    public final static String URL_SIGN = URL_BASE_YAO + "api_get_sign_in";

    /**
     * 获取开机广告params
     *
     * @return
     */
    public static String getLanuchAdsParams() {
        String qParam = "source=yaoyiyao&cflag=" + Constants.CFLAG;
        return DESUtils.encrypt(qParam);
    }

    /**
     * 获取线上版本params
     *
     * @return
     */
    public static String getVersionParams() {
        String qParam = "device=android&source=" + Constants.CFLAG + "&time_str="
                + Long.toString(System.currentTimeMillis());
        return DESUtils.encrypt(qParam);
    }

    /**
     * 获取资讯页面数据
     *
     * @param fid
     * @param page
     * @return
     */
    public static String getNewsUrl(String fid, int page) {
        return String.format(URL_GET_NEWS, fid, page);
    }

    /**
     * 获取互动页面数据params
     *
     * @return
     */
    public static String getHudongParams() {
        String qParam = "source=yaoyiyao&device=android&v=1&cflag=" + Constants.CFLAG + "&time_str="
                + Long.toString(System.currentTimeMillis());
        Logs.i("加密前：" + qParam);
        return DESUtils.encrypt(qParam);
    }


    /**
     * 获取互动标识符params
     *
     * @return
     */
    public static String getHudongTagParams() {
        String qParam = "pos=tv";
        return DESUtils.encrypt(qParam);
    }

    /**
     * 摇积分params
     *
     * @param context
     * @return
     */
    public static String getShakeScoreParams(Context context) {
        String qParam = "uid=" + Integer.toString(LoginManager.getUser().uid)
                + "&source=yaoyiyao&cflag=" + Constants.CFLAG + "&time_str="
                + Long.toString(System.currentTimeMillis());

        return DESUtils.encrypt(qParam);
    }


    /**
     * 图片验证码params
     *
     * @return
     */
    public static String getVerifyCodeParams() {
        return Constants.IMEI;
    }


    /**
     * 获取动态验证码（登录）params
     *
     * @param mobile     手机号
     * @param verifyCode 图片验证码
     * @return
     */
    public static String getSmsCodeDynamicParams(String mobile, String verifyCode) {
        String qParams = "&imei=" + Constants.IMEI + "&mobile=" + mobile + "&vcode=" + verifyCode + "&cflag="
                + Constants.CFLAG + "&time_str=" + System.currentTimeMillis();
        return DESUtils.encrypt(qParams);
    }

    /**
     * 获取动态验证码（注册）params
     *
     * @param mobile     手机号
     * @param verifyCode 图片验证码
     * @return
     */
    public static String getRegisterSmsCodeParams(String mobile, String verifyCode) {
        String qParams = "&imei=" + Constants.IMEI + "&source=yaoyiyao&mobile=" + mobile + "&cflag="
                + Constants.CFLAG + "&time_str=" + Long.toString(System.currentTimeMillis()) + "&vcode=" + verifyCode;
        return DESUtils.encrypt(qParams);
    }

    /**
     * 获取登录params
     *
     * @param mobile
     * @param smsCode
     * @return
     */
    public static String getLoginParams(String mobile, String smsCode) {
        String qParams = "&imei=" + Constants.IMEI + "&mobile=" + mobile
                + "&smscode=" + smsCode + "&cflag=" + Constants.CFLAG
                + "&time_str=" + System.currentTimeMillis();
        return DESUtils.encrypt(qParams);
    }

    public static String getLoginOldParams(String username, String password) {
        String qParams = "username=" + username + "&password=" + password + "&cflag="
                + Constants.CFLAG + "&time_str=" + Long.toString(System.currentTimeMillis());
        return DESUtils.encrypt(qParams);
    }

    /**
     * 获取注册params
     *
     * @param mobile     手机号
     * @param smsCode    短信验证码
     * @param inviteCode 邀请码
     * @return
     */
    public static String getRegisterParams(String mobile, String smsCode, String inviteCode) {
        String qParams = "&imei=" + Constants.IMEI + "&source=yaoyiyao" + "&step=1&cflag="
                + Constants.CFLAG + "&time_str=" + Long.toString(System.currentTimeMillis())
                + "&smscode=" + smsCode + "&invitation_code=" + inviteCode + "&mobile=" + mobile;
        return DESUtils.encrypt(qParams);
    }

    /**
     * 获取用户信息params
     *
     * @return
     */
    public static String getUserParams(String uid) {
        String qParams = "uid=" + uid + "&source=yaoyiyao&cflag="
                + Constants.CFLAG + "&time_str=" + Long.toString(System.currentTimeMillis());
        return DESUtils.encrypt(qParams);
    }

    /**
     * 获取签到parmas
     * @return
     */
    public static String getSignParmas() {
        String qParams = "&source=yaoyiyao&cflag=" + Constants.CFLAG + "&time_str="
                + Long.toString(System.currentTimeMillis()) + "&username=" + LoginManager.getUser().username;
        return DESUtils.encrypt(qParams);
    }

}
