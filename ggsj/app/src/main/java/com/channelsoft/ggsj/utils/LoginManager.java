package com.channelsoft.ggsj.utils;

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


    public static void saveSessionId(String sessionId)
    {
        SharedPreferencesUtil.getSharedPreferences().saveSessionId(sessionId);
    }

    public static String getSessionId()
    {
        return  SharedPreferencesUtil.getSharedPreferences().getSessionId();
    }


    public static void saveEntId(String entId)
    {
        SharedPreferencesUtil.getSharedPreferences().saveEntId(entId);
    }

    public static String getEntId()
    {
        return  SharedPreferencesUtil.getSharedPreferences().getEntId();
    }

    public static void saveHelpDeskUrl(String helpDeskUrl)
    {
        SharedPreferencesUtil.getSharedPreferences().saveHelpDeskUrl(helpDeskUrl);
    }

    public static String getHelpDesjUrl()
    {
        return  SharedPreferencesUtil.getSharedPreferences().getHelpDeskUrl();
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
