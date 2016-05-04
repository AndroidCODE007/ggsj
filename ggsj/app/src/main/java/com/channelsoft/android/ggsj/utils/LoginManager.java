package com.channelsoft.android.ggsj.utils;

import android.text.TextUtils;

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
}
