package com.channelsoft.ggsj.utils;

import com.channelsoft.ggsj.base.GlobalApplication;

import java.lang.reflect.Field;

/**
 * 屏幕系统参数
 * Created by dengquan on 16-4-19.
 */
public class ScreenUtils
{
    private static final String TAG = ScreenUtils.class.getSimpleName();

    public float getDensity()
    {
        return GlobalApplication.getInstance().getResources().getDisplayMetrics().density;
    }

    public float getScreenHeight()
    {
        return GlobalApplication.getInstance().getResources().getDisplayMetrics().heightPixels;
    }

    public float getScreenWidth()
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
}
