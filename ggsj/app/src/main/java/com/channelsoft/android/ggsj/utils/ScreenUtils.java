package com.channelsoft.android.ggsj.utils;


import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.PowerManager;
import android.text.TextUtils;

import com.channelsoft.android.ggsj.base.GlobalApplication;

import java.lang.reflect.Field;
import java.util.List;

/**
 * 屏幕系统参数
 * Created by dengquan on 16-4-19.
 */
public class ScreenUtils
{
    private static final String TAG = ScreenUtils.class.getSimpleName();

    public static  float getDensity()
    {
        return GlobalApplication.getInstance().getResources().getDisplayMetrics().density;
    }

    public static  float getScreenHeight()
    {
        return GlobalApplication.getInstance().getResources().getDisplayMetrics().heightPixels;
    }

    public static float getScreenWidth()
    {
        return GlobalApplication.getInstance().getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 获取状态栏的高度
     *
     * @return
     */
    public static float getStatusBarHeight()
    {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0;
        try
        {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            LogUtils.i(TAG,"status bar height :"+GlobalApplication.getInstance().getResources().getDimensionPixelSize(x));
            return GlobalApplication.getInstance().getResources().getDimensionPixelSize(x);
        } catch (Exception e1)
        {
            LogUtils.d(TAG, "get status bar height fail");
            e1.printStackTrace();
            return 75;
        }
    }

    /**
     * 判断当前应用程序处于前台还是后台
     */
    public static boolean isApplicationBroughtToBackground(final Context context)
    {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
        if(!tasks.isEmpty())
        {
            ComponentName topActivity = tasks.get(0).topActivity;
            if(!topActivity.getPackageName().equals(context.getPackageName()))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断当前应用是否处于后台
     *
     * @param context
     * @return
     */
    public static boolean isBackground(Context context)
    {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        for(ActivityManager.RunningAppProcessInfo appProcess : appProcesses)
        {
            if(appProcess.processName.equals(context.getPackageName()))
            {
                if(appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_BACKGROUND)
                {
                    LogUtils.i(TAG, appProcess.processName);
                    return true;
                }
                else
                {
                    LogUtils.i(TAG, appProcess.processName);
                    return false;
                }
            }
        }
        return false;
    }

    public static boolean isNotLockAndNotCloseScreen(Context context)
    {
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        KeyguardManager mKeyguardManager = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
        return pm.isScreenOn() && !mKeyguardManager.inKeyguardRestrictedInputMode();
    }

    /**
     * 是否亮屏
     *
     * @return
     */
    public static boolean isScreenOn()
    {
        PowerManager pm = (PowerManager) GlobalApplication.getInstance().getSystemService(Context.POWER_SERVICE);
        return pm.isScreenOn();
    }

    /**
     * 亮屏一段时间
     *
     * @param timeOut
     */
    public static void openScreenTime(long timeOut)
    {
        PowerManager pm = (PowerManager) GlobalApplication.getInstance().getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wakeLock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK
                | PowerManager.ACQUIRE_CAUSES_WAKEUP
                | PowerManager.ON_AFTER_RELEASE, TAG);
        wakeLock.acquire(timeOut);
    }

    /**
     * 判断某个界面是否在前台
     *
     * @param context
     * @param className
     *            某个界面名称
     */
    public static boolean isForeground(Context context, String className)
    {
        if (context == null || TextUtils.isEmpty(className))
        {
            return false;
        }
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(1);
        if (list != null && list.size() > 0)
        {
            ComponentName cpn = list.get(0).topActivity;
            LogUtils.d(TAG, "cpn.getClassName()=" + cpn.getClassName());
            if (cpn.getClassName().contains(className))
            {
                return true;
            }
        }

        return false;
    }
}
