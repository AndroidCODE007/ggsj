package com.channelsoft.ggsj.http;

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
    }

    /**
     * 订单的地址
     */
    public class Order
    {
        public static final String GET_ORDERLIST = "/order/queryOrderListByGroupStatus.action";
    }

}
