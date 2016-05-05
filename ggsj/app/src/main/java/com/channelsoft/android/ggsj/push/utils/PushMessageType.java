package com.channelsoft.android.ggsj.push.utils;

/**
 * Created by lenovo on 2016/5/5.
 */
public class PushMessageType {
    public static final String AUTH_LOGIN = "1101";//授权登录消息
    public static final String VERIFY_COUPON_REMIND = "1102";//验券提醒消息
    public static final String CANCEL_AUTH_LOGIN = "1103";//老板端取消授权
    public static final String CHANGE_AUTH_RIGHT = "1104";//修改员工设备权限 和 设备名
    public static final String CHANGE_TABLE_SWITCH = "1105"; //老板设置桌位开关
    public static final String ORDER_MESSAGE = "1106";//用户到店，订单 -- 用户扫码入座
    public static final String ADD_ORDER_MESSAGE = "1107";//加菜订单
    public static final String USER_SEAT_MSG = "1108";//用户点击我已入座
    public static final String USER_PAY_SUCCESS_MSG = "1109";//用户付款成功
    public static final String USER_CANCEL_MAG = "1110";//用户取消订单
    public static final String CHANGE_TEXT_FONT = "1111";//（修改）接收后到修改打印设置内容，包括字体 联数 顾客下单自动打印厨房单 小票
    public static final String MEMBER_TO_CHANGE_PRICE = "1112";//会员改价
    public static final String MEMBER_TO_CANCEL_CHANGE_PRICE = "1113";//取消会员改价
    public static final String PRINT_FROM_THIS_DEVICE = "1114";//其他设备通过本机打印
    public static final String PRINT_DEVICE_CONNECT_STATE = "1115";//已选择打印机状态改变
}
