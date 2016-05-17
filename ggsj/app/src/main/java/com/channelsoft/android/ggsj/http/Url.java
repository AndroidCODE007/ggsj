package com.channelsoft.android.ggsj.http;

import com.channelsoft.android.ggsj.BuildConfig;

/**
 * Created by dengquan on 16-3-23.
 */
public class Url
{
//    bj {
//    buildConfigField 'String','IP','"http://test.qncloud.cn/"'
//    buildConfigField 'String','MICRO_URL','"http://v.qncloud.cn/"'
//}
    public static final String HOST = "http://order2.qncloud.cn";

    public static final String MICRO_HOST = "http://order2.m.qncloud.cn";

    public static final String ACTION = "1";

    /**
     * 登录的地址
     */
    public class Login
    {
        /**
         * 店长登录获取验证码
         */
        public static final String GENERATE_CODE = "/sdm/vcode/generateCode.action";

        /**
         * 咕咕商家登录SDM校验验证码
         */
        public static final String VERIFY_CODE = "/sdm/vcode/verifyCode.action";


        /**
         *重新登陆的接口。
         */
        public static final String RE_LOGIN = "/ggsj/login.action";


        /**
         * 选择完商户之后的登陆
         */
        public static final String BOSS_LOGIN_HELPDESK = "/ggsj/login.action";

        /**
         * 老板设备扫码获取设备信息
         */
        public static final String GET_AUTH_DEVICE_INFO = "/ggsj/authDeviceInfo.action";

    }

    /**
     * 订单的地址
     */
    public class Order
    {
        public static final String GET_ORDERLIST = "/order/queryOrderListByGroupStatus.action";
    }

    /**
     *消息中心注册咕咕商家信息接口
     */
    public class RegistMsgCentre {
        public static final String REGIST_MSG_CENTRE = "/csnc/registerGGSJ.action";
    }

    /**
     * 更新APP
     */
    public class Update
    {
        public static final String CHECKVERSION_ACTION = "/npsV2/apkversion/checkUpdate";            // 检查版本更新接口
    }

}
