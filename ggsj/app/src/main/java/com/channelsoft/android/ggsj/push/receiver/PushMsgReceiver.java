package com.channelsoft.android.ggsj.push.receiver;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;


import com.channelsoft.android.ggsj.base.GlobalApplication;
import com.channelsoft.android.ggsj.login.activity.StafLoginActivity;
import com.channelsoft.android.ggsj.push.bean.AuthMiMsg;
import com.channelsoft.android.ggsj.push.pushmodel.IRegistMsgCentrePushModel;
import com.channelsoft.android.ggsj.push.pushmodel.RegistMsgCentrePushModelImpl;
import com.channelsoft.android.ggsj.push.service.PushNotifyService;
import com.channelsoft.android.ggsj.push.utils.PushMessageType;
import com.channelsoft.android.ggsj.utils.LogUtils;
import com.channelsoft.android.ggsj.utils.LoginManager;
import com.channelsoft.android.ggsj.utils.ScreenUtils;
import com.channelsoft.android.ggsj.utils.VersionCodeUtil;
import com.google.gson.Gson;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xiaomi.mipush.sdk.MiPushCommandMessage;
import com.xiaomi.mipush.sdk.MiPushMessage;
import com.xiaomi.mipush.sdk.PushMessageReceiver;

import java.util.List;

/**
 * Created by chenyg on 2016/5/3.
 */
public class PushMsgReceiver extends PushMessageReceiver
{

    private static final String TAG = PushMsgReceiver.class.getSimpleName();
    public static final String AUTHMIMSG = "AUTHMIMSG";
    private String mainActivity = "com.activity.MainActivity";
    private String regId;

    @Override
    public void onCommandResult(Context context, MiPushCommandMessage miPushCommandMessage)
    {
        LogUtils.i(TAG, "onCommandResult is called. " + miPushCommandMessage.toString());
        super.onCommandResult(context, miPushCommandMessage);
    }

    @Override
    public void onNotificationMessageArrived(Context context, MiPushMessage miPushMessage)
    {//通知栏消息到达
        LogUtils.i(TAG, "onNotificationMessageArrived is called. " + miPushMessage.toString());
        super.onNotificationMessageArrived(context, miPushMessage);
    }

    @Override
    public void onNotificationMessageClicked(Context context, MiPushMessage miPushMessage)
    {//点击通知栏消息
        LogUtils.i(TAG, "onNotificationMessageClicked is called. " + miPushMessage.toString());
        super.onNotificationMessageClicked(context, miPushMessage);
    }

    @Override
    public void onReceivePassThroughMessage(Context context, MiPushMessage miPushMessage)
    {//透传消息到达
        LogUtils.i(TAG, "onReceivePassThroughMessage is called. " + miPushMessage.toString());

        AuthMiMsg msg;
        String content = miPushMessage.getContent();//消息内容
        LogUtils.i(TAG, "消息json = " + content);
        String bizType;
        if (!TextUtils.isEmpty(content))
        {
            Gson gson = new Gson();
            msg = gson.fromJson(content, AuthMiMsg.class);
            if (!TextUtils.isEmpty(msg.getBizType()))
            {
                bizType = msg.getBizType();
            } else
            {
                return;
            }
            if (PushMessageType.AUTH_LOGIN.equals(bizType))
            {//授权登录消息

                LogUtils.i(TAG, "授权登录消息");
                sendBroadToStaffReceiver(msg);

            } else if (PushMessageType.VERIFY_COUPON_REMIND.equals(bizType))
            {//验券提醒消息

                LogUtils.i(TAG, "验券提醒消息");

            } else if (PushMessageType.CANCEL_AUTH_LOGIN.equals(bizType))
            {//老板端取消授权

                LogUtils.i(TAG, "老板端取消授权");

            } else if (PushMessageType.CHANGE_AUTH_RIGHT.equals(bizType))
            {//修改员工设备权限、设备名

                LogUtils.i(TAG, "修改员工设备权限、设备名");

            } else if (PushMessageType.CHANGE_TABLE_SWITCH.equals(bizType))
            {//老板设置桌位开关

                LogUtils.i(TAG, "老板设置桌位开关");

            } else if (PushMessageType.ORDER_MESSAGE.equals(bizType))
            {//用户到店，订单 —— 用户扫码入座

                LogUtils.i(TAG, "用户到店，订单 —— 用户扫码入座");
                doWhat(context, true, "有新订单", "您有未处理的新订单！", msg, miPushMessage.getNotifyType(), mainActivity);

            } else if (PushMessageType.ADD_ORDER_MESSAGE.equals(bizType))
            {//加菜订单

                LogUtils.i(TAG, "加菜订单");
                doWhat(context, true, "顾客加菜", msg.getDialogContent(), msg, miPushMessage.getNotifyType(), mainActivity);

            } else if (PushMessageType.USER_SEAT_MSG.equals(bizType))
            {//用户点击我已入座

                LogUtils.i(TAG, "用户点击我已入座");
                doWhat(context, true, "有新订单", "您有未处理的新订单！", msg, miPushMessage.getNotifyType(), mainActivity);

            } else if (PushMessageType.USER_PAY_SUCCESS_MSG.equals(bizType))
            {//用户付款成功

                LogUtils.i(TAG, "用户付款成功");
                doWhat(context, true, "收到餐费", msg.getDialogContent(), msg, miPushMessage.getNotifyType(), mainActivity);

            } else if (PushMessageType.USER_CANCEL_MAG.equals(bizType))
            {//用户取消订单

                LogUtils.i(TAG, "用户取消订单");

            } else if (PushMessageType.CHANGE_TEXT_FONT.equals(bizType))
            {//（修改）接收后到修改打印设置内容，包括字体 联数 顾客下单自动打印厨房单 小票

                LogUtils.i(TAG, "（修改）接收后到修改打印设置内容，包括字体 联数 顾客下单自动打印厨房单 小票");

            } else if (PushMessageType.MEMBER_TO_CHANGE_PRICE.equals(bizType))
            {//会员改价

                LogUtils.i(TAG, "会员改价");

            } else if (PushMessageType.MEMBER_TO_CANCEL_CHANGE_PRICE.equals(bizType))
            {//取消会员改价

                LogUtils.i(TAG, "取消会员改价");

            } else if (PushMessageType.PRINT_FROM_THIS_DEVICE.equals(bizType))
            {//其他设备用过本机打印

                LogUtils.i(TAG, "其他设备用过本机打印");

            } else if (PushMessageType.PRINT_DEVICE_CONNECT_STATE.equals(bizType))
            {//已选择打印机状态改变

                LogUtils.i(TAG, "已选择打印机状态改变");

            }
        } else
        {
            return;
        }

        super.onReceivePassThroughMessage(context, miPushMessage);
    }

    @Override
    public void onReceiveRegisterResult(Context context, MiPushCommandMessage miPushCommandMessage)
    {//获取注册结果
        super.onReceiveRegisterResult(context, miPushCommandMessage);
        LogUtils.i(TAG, "onReceiveRegisterResult is called. " + miPushCommandMessage.toString());

        String command = miPushCommandMessage.getCommand();
        if (command != null && command.equals("register"))
        {
            LogUtils.i(TAG, "向小米注册成功");

            MiPushClient.setAlias(context, LoginManager.getDeviceId(), null);
            LogUtils.i(TAG, "设置别名成功");


            regId = null;
            List<String> commandArgument = miPushCommandMessage.getCommandArguments();
            if (commandArgument != null && commandArgument.size() > 0)
            {
                regId = commandArgument.get(0);
            }

            LoginManager.saveRegId(regId);

        } else
        {
            //向小米注册失败，重新注册
            LogUtils.i(TAG, "向小米注册失败");
            GlobalApplication.instance.registToMiPush();
        }

    }

    /**
     * 订单消息---弹窗还是notification
     *
     * @param isNeedUpdateList    是否通知订单列表刷新
     * @param msg                 接收到的消息bean
     * @param notifyType          通知栏的消息提醒方式
     * @param startActivityAction 通知栏点进去需要跳转的action
     */
    public void doWhat(Context context, boolean isNeedUpdateList, String dialogTitle, String dialogContent
            , AuthMiMsg msg, int notifyType, String startActivityAction)
    {
        if (!TextUtils.isEmpty(LoginManager.getSessionId()) && !TextUtils.isEmpty(msg.getOrderId()))
        {//有Id已登录
            LogUtils.i(TAG, "有ID已登录");
//            if(!ScreenUtils.isApplicationBroughtToBackground(GlobalApplication.getInstance())
//                    && ScreenUtils.isNotLockAndNotCloseScreen(context)){//在前台、未锁屏、未息屏，弹窗
//                LogUtils.i(TAG,"makeDialog");
//                makeDialog(context, dialogTitle, dialogContent, msg);
//            }else {
            LogUtils.i(TAG, "else");
            //在后台、或锁屏、或息屏，弹通知栏
            if (!TextUtils.isEmpty(msg.getNotifyTitle()))
            {
                LogUtils.i(TAG, "makeNotification");
                makeNotification(context, msg.getNotifyTitle(), msg.getOrderId(), notifyType, startActivityAction);
            }
//            }
        }
    }

    /**
     * 启动service弹通知
     */
    private void makeNotification(Context context, String tickerText, String orderId, int notifyType, String startActivityAction)
    {
        Intent intent = new Intent(context, PushNotifyService.class);
        intent.putExtra("action", "notification");
        intent.putExtra("title", tickerText);
        intent.putExtra("notifyType", notifyType);
        intent.putExtra("orderId", orderId);
        intent.putExtra("startActivityAction", startActivityAction);
        context.startService(intent);
    }

    private void makeDialog(Context context, String title, String content, AuthMiMsg msg)
    {
        Intent intent = new Intent(context, PushNotifyService.class);
        intent.putExtra("action", "dialog");
        intent.putExtra("type", "1");
        intent.putExtra("title", title);
        intent.putExtra("content", content);
        if (PushMessageType.USER_CANCEL_MAG.equals(msg.getBizType()))
        {
            intent.putExtra("leftbtn", "我知道了");
            intent.putExtra("rightbtn", "查看详情");
        } else if (PushMessageType.USER_PAY_SUCCESS_MSG.equals(msg.getBizType()))
        {
            intent.putExtra("leftbtn", "知道了");
            intent.putExtra("rightbtn", "查看");
        } else
        {
            intent.putExtra("leftbtn", "稍候处理");
            intent.putExtra("rightbtn", "立即处理");
        }
        intent.putExtra("data", msg.getOrderId());
        context.startService(intent);
    }

    /**
     * 向员工登陆发送广播
     *
     * @param msg
     */
    private void sendBroadToStaffReceiver(AuthMiMsg msg)
    {
        Intent intent = new Intent(StafLoginActivity.STAFF_LOGIN_ACTION);
        intent.putExtra(AUTHMIMSG, msg);
        GlobalApplication.getInstance().sendBroadcast(intent);
        LogUtils.i(TAG, "send receiver to staff :" + msg.toString());
    }

}
