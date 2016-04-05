package com.channelsoft.ggsj.http;

import android.content.Context;
import android.net.NetworkInfo;

import com.channelsoft.ggsj.http.NetWorkHelper;

/**
 * 网络链接状态的判断
 * Created by dengquan on 16-3-23.
 */
public class HttpUtil
{


    public static boolean isNetworkAvailable(Context context)
    {
        return NetWorkHelper.isNetworkAvailable(context);
    }

    public static boolean isWifiDataEnabled(Context context)
    {
        return NetWorkHelper.isWifiDataEnabled(context);
    }

    public static boolean isMobileDataEnabled(Context context)
    {
        return NetWorkHelper.isMobileDataEnabled(context);
    }

}
