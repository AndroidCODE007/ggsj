package com.channelsoft.android.ggsj.base;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.*;

import com.channelsoft.android.ggsj.utils.LogUtils;
import com.channelsoft.android.ggsj.utils.LoginManager;
import com.xiaomi.mipush.sdk.MiPushClient;

import java.util.List;

/**
 * Created by dengquan on 16-3-23.
 */
public class GlobalApplication extends Application
{
    public static GlobalApplication instance;

    //小米推送APPId和APPKey
    public static final String APP_ID = "2882303761517399502";
    public static final String APP_KEY = "5281739953502";

    public boolean isRegistToMiPush = false;

    public static boolean isShow = false;//控制弹窗，只弹一次

    @Override
    public void onCreate()
    {
        super.onCreate();
        instance = this;
    }

    public static Context getInstance()
    {
        if(instance == null)
        {
            instance = new GlobalApplication();
        }
        return instance;
    }

    private boolean shouldInit()
    {
        ActivityManager am = ((ActivityManager) getSystemService(Context.ACTIVITY_SERVICE));
        List<ActivityManager.RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
        String mainProcessName = getPackageName();
        int myPid = android.os.Process.myPid();
        for (ActivityManager.RunningAppProcessInfo info : processInfos)
        {
            if (info.pid == myPid && mainProcessName.equals(info.processName))
            {
                return true;
            }
        }
        return false;
    }

    public void registToMiPush(){
        if (shouldInit())
        {
            MiPushClient.registerPush(this, APP_ID, APP_KEY);
        }
    }
}
