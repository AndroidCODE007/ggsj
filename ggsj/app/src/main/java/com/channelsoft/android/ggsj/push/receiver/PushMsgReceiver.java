package com.channelsoft.android.ggsj.push.receiver;

import android.content.Context;
import android.os.Build;


import com.channelsoft.android.ggsj.base.GlobalApplication;
import com.channelsoft.android.ggsj.push.pushmodel.IRegistMsgCentrePushModel;
import com.channelsoft.android.ggsj.push.pushmodel.RegistMsgCentrePushModelImpl;
import com.channelsoft.android.ggsj.utils.LogUtils;
import com.channelsoft.android.ggsj.utils.LoginManager;
import com.channelsoft.android.ggsj.utils.VersionCodeUtil;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xiaomi.mipush.sdk.MiPushCommandMessage;
import com.xiaomi.mipush.sdk.MiPushMessage;
import com.xiaomi.mipush.sdk.PushMessageReceiver;

import java.util.List;

/**
 * Created by chenyg on 2016/5/3.
 */
public class PushMsgReceiver extends PushMessageReceiver implements RegistMsgCentrePushModelImpl.OnRegistPush{

    private static final String TAG = PushMsgReceiver.class.getSimpleName();
    private IRegistMsgCentrePushModel model;
    private String phoneNumber;
    private String deviceOsVersion;
    private String appVersion;
    private String regId;
    private String deviceModel;

    @Override
    public void onCommandResult(Context context, MiPushCommandMessage miPushCommandMessage) {
        LogUtils.i(TAG, "onCommandResult is called. " + miPushCommandMessage.toString());
        super.onCommandResult(context, miPushCommandMessage);
    }

    @Override
    public void onNotificationMessageArrived(Context context, MiPushMessage miPushMessage) {//通知栏消息到达
        LogUtils.i(TAG, "onNotificationMessageArrived is called. " + miPushMessage.toString());
        super.onNotificationMessageArrived(context, miPushMessage);
    }

    @Override
    public void onNotificationMessageClicked(Context context, MiPushMessage miPushMessage) {//点击通知栏消息
        LogUtils.i(TAG, "onNotificationMessageClicked is called. " + miPushMessage.toString());
        super.onNotificationMessageClicked(context, miPushMessage);
    }

    @Override
    public void onReceivePassThroughMessage(Context context, MiPushMessage miPushMessage) {//透传消息到达
        LogUtils.i(TAG, "onReceivePassThroughMessage is called. " + miPushMessage.toString());
        super.onReceivePassThroughMessage(context, miPushMessage);
    }

    @Override
    public void onReceiveRegisterResult(Context context, MiPushCommandMessage miPushCommandMessage) {//获取注册结果
        super.onReceiveRegisterResult(context, miPushCommandMessage);
        LogUtils.i(TAG, "onReceiveRegisterResult is called. " + miPushCommandMessage.toString());

        String command = miPushCommandMessage.getCommand();
        if(command != null && command.equals("register")){
            LogUtils.i(TAG, "向小米注册成功");
            phoneNumber = LoginManager.getPhoneNumber();
            if(phoneNumber.length() > 0){
                LogUtils.i(TAG, "别名设置成功，Alias =" + phoneNumber);
                MiPushClient.setAlias(context, phoneNumber, null);

                regId = null;
                List<String> commandArgument = miPushCommandMessage.getCommandArguments();
                if(commandArgument != null && commandArgument.size() > 0){
                    regId = commandArgument.get(0);
                }

                deviceModel = Build.MODEL;
                deviceOsVersion = Build.VERSION.RELEASE;
                appVersion = VersionCodeUtil.getCurrentName();

                model = new RegistMsgCentrePushModelImpl(this);
                model.registMsgCentre(regId,deviceOsVersion,deviceModel,appVersion,phoneNumber);

            }
        }else {
            //向小米注册失败，重新注册
            LogUtils.i(TAG, "向小米注册失败");
            GlobalApplication.instance.registToMiPush();
        }

    }

    @Override
    public void onRegistPushSuccess() {
        LogUtils.i(TAG, "向消息中心注册成功");
    }

    @Override
    public void onRegistPushError() {
        LogUtils.i(TAG, "向消息中心注册失败");
        //向消息中心注册失败则重新注册
//        if(model == null){
//            model = new RegistMsgCentrePushModelImpl(this);
//        }
//        model.registMsgCentre(regId, deviceOsVersion, deviceModel, appVersion, phoneNumber);
    }
}
