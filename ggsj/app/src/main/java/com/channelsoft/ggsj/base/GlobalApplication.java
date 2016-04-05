package com.channelsoft.ggsj.base;

import android.app.Application;
import android.content.Context;

/**
 * Created by dengquan on 16-3-23.
 */
public class GlobalApplication extends Application
{
    public static GlobalApplication instance;
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
}
