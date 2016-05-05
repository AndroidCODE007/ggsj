package com.channelsoft.android.ggsj.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.channelsoft.android.ggsj.base.GlobalApplication;

/**
 * Created by dengquan on 16-4-7.
 */
public class SharedPreferencesUtil
{
    private static final String TAG = SharedPreferencesUtil.class.getSimpleName();
    private static SharedPreferencesUtil preferences;
    public static final String TOKEN = "TOKEN";
    private SharedPreferences sharedPreferences =
            GlobalApplication.getInstance().getSharedPreferences(TOKEN_MSG, Context.MODE_PRIVATE);;
    private static final String TOKEN_MSG = "token_msg";
    private static final String VERSION_CODE = "version_code";
    private static final String VERSION_NAME = "version_name";
    private static final String PHONE_NUMBER = "phone_number";
    private static final String ENT_ID = "ent_id";
    private SharedPreferencesUtil()
    {

    }


    public static SharedPreferencesUtil getSharedPreferences()
    {
        if (preferences != null)
        {
            return preferences;
        } else
        {
            return preferences = new SharedPreferencesUtil();
        }
    }

    public void saveToken(String token)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TOKEN,token);
        editor.commit();
    }

    public void savePhoneNumber(String phoneNumber){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PHONE_NUMBER,phoneNumber);
        editor.commit();
    }


    public String getToken()
    {
        if(sharedPreferences == null)
        {
            sharedPreferences = GlobalApplication.getInstance().getSharedPreferences(TOKEN_MSG, Context.MODE_PRIVATE);
        }
        LogUtils.i(TAG, sharedPreferences.getString(TOKEN, ""));
        return  sharedPreferences.getString(TOKEN,"");
    }

    public String getPhoneNumber(){
        if(sharedPreferences == null){
            sharedPreferences = GlobalApplication.getInstance().getSharedPreferences(TOKEN_MSG,Context.MODE_PRIVATE);
        }
        return sharedPreferences.getString(PHONE_NUMBER,"0");
    }

    public void saveVersionCode(int code)
    {
        if(sharedPreferences == null)
        {
            sharedPreferences = GlobalApplication.getInstance().getSharedPreferences(TOKEN_MSG, Context.MODE_PRIVATE);
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(VERSION_CODE,code);
        editor.commit();
    }

    public int getVersionCode()
    {
        if(sharedPreferences == null)
        {
            sharedPreferences = GlobalApplication.getInstance().getSharedPreferences(TOKEN_MSG, Context.MODE_PRIVATE);
        }
        return  sharedPreferences.getInt(VERSION_CODE,0);
    }

}
