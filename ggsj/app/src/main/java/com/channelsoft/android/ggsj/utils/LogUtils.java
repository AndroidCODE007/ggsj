package com.channelsoft.android.ggsj.utils;

import android.util.Log;

import com.channelsoft.android.ggsj.BuildConfig;


/**
 * Created by dengquan on 2015/8/6.
 * 自定义的打印Log的方法，可以控制Log的打印等级和不打印Log
 */
public class LogUtils {
    public static int VERBOSE = 1;
    public static int DEBUG = 2;
    public static int INFO = 3;
    public static int WARM = 4;
    public static int ERROR = 5;
    public static int NOTHING = 6;
    //定义一个等级，控制LOG的打印级别。如果小于INDEX则不打印。不过INDEX > 7 则所有的LOG全部不打印
    public static int LEVEL = VERBOSE;



    public static void v(String tag,String message)
    {
        if (BuildConfig.DEBUG) {
            Log.v(tag,message);
        }
    }

    public static void d(String tag,String message)
    {
        if(BuildConfig.DEBUG)
        {
            Log.d(tag,message);
        }
    }

    public static void i(String tag,String message)
    {
        if(BuildConfig.DEBUG)
        {
            Log.i(tag,message);
        }
    }

    public static void w(String tag,String messsage)
    {
        if(BuildConfig.DEBUG)
        {
            Log.w(tag, messsage);
        }
    }

    public static  void e(String tag,String message)
    {
        if(BuildConfig.DEBUG)
        {
            Log.e(tag, message);
        }
    }
}
