package com.channelsoft.ggsj.http;

import android.app.usage.NetworkStatsManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;

/**
 * Created by dengquan on 16-3-23.
 */
public class NetWorkHelper
{
    /**
     * 判断当前网络状态是否可用
     * @param context
     * @return
     */
    public static boolean isNetworkAvailable(Context context)
    {
        ConnectivityManager manager =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(manager == null)
        {
            return false;
        }
        else
        {
            NetworkInfo info = manager.getActiveNetworkInfo();
            if(info != null)
            {
                if(info.isAvailable())
                {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判断当前的Wifi是否可用
     * @param context
     * @return
     */
    public static boolean isWifiDataEnabled(Context context)
    {
        ConnectivityManager manager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo  info = manager.getActiveNetworkInfo();
        if(info != null && info.getType() == ConnectivityManager.TYPE_WIFI)
        {
            return true;
        }
        return false;
    }


    /**
     * 判断手机数据流浪包是否可用
     * @param context
     * @return
     */
    public static boolean isMobileDataEnabled(Context context)
    {
        ConnectivityManager manager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if(info != null && info.getType() == ConnectivityManager.TYPE_MOBILE)
        {
            return true;
        }
        return  false;
    }


}
