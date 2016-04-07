package com.channelsoft.ggsj.utils;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.design.widget.TabLayout;

import com.channelsoft.ggsj.BuildConfig;
import com.channelsoft.ggsj.base.GlobalApplication;

/**
 * 软件版本工具
 * Created by dengquan on 16-4-7.
 */
public class VersionCodeUtil
{
    private static final String TAG = VersionCodeUtil.class.getSimpleName();
    /**
     * 获取当前软件版本号
     * @return
     */
    public static int getCurrentVersionCode()
    {
        PackageInfo info = null;
        PackageManager manager =
                GlobalApplication.getInstance().getApplicationContext().getPackageManager();
        try
        {
            info =  manager.getPackageInfo(GlobalApplication.getInstance().getPackageName(),0);
        }
        catch (PackageManager.NameNotFoundException e)
        {
            LogUtils.i(TAG,e.getMessage());
        }
        finally
        {
            if(info != null)
            {
                return info.versionCode;
            }
            else
            {
              return 0;
            }
        }
    }

    /**
     * 获取当前软件版本名称
     * @return
     */
    public static String getCurrentName()
    {
        PackageInfo info = null;
        PackageManager manager =
                GlobalApplication.getInstance().getApplicationContext().getPackageManager();
        try
        {
            info =  manager.getPackageInfo(GlobalApplication.getInstance().getPackageName(),0);
        }
        catch (PackageManager.NameNotFoundException e)
        {
            LogUtils.i(TAG,e.getMessage());
        }
        finally
        {
            if(info != null)
            {
                return info.versionName;
            }
            else
            {
                return "";
            }
        }
    }


    /**
     * 比较当前软件的版本和本地存储的版本号的大小（更新完APP以后并不更新本地版本号，为了出现引导页面）
     */
    public static boolean checkVersion()
    {
        if(SharedPreferencesUtil.getSharedPreferences().getVersionCode()  < getCurrentVersionCode())
        {
            return false;    //出现引导页面
        }
        else
        {
            return false;
        }
    }



}
