package com.channelsoft.android.ggsj.utils;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import com.channelsoft.android.ggsj.base.GlobalApplication;

import java.util.UUID;

/**
 * 控制用户登陆信息的类
 * Created by dengquan on 16-3-23.
 */
public class LoginManager
{
    public static void saveToken(String token)
    {
        SharedPreferencesUtil.getSharedPreferences().saveToken(token);
    }

    public static String getToken()
    {
        return  SharedPreferencesUtil.getSharedPreferences().getToken();
    }

    public static void savePhoneNumber(String phoneNumber){
        SharedPreferencesUtil.getSharedPreferences().savePhoneNumber(phoneNumber);
    }

    public static String getPhoneNumber(){
        return SharedPreferencesUtil.getSharedPreferences().getPhoneNumber();
    }

    public static boolean isLogin()
    {
        if(TextUtils.isEmpty(SharedPreferencesUtil.getSharedPreferences().getToken()))
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    /**
     * 获取IMEI号，如果获取不到IMEI号，就拿uuid代替
     * @return
     */
    public static String getDeviceId(){
        TelephonyManager telephonyManager = (TelephonyManager) GlobalApplication.getInstance().getSystemService(Context.TELEPHONY_SERVICE);
        String deviceId = telephonyManager.getDeviceId();
        if(TextUtils.isEmpty(deviceId)){
            deviceId = UUID.randomUUID().toString().replace("-", "");
        }
        return deviceId;
    }
}
