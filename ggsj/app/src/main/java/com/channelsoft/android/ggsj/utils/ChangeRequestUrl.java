package com.channelsoft.android.ggsj.utils;

import android.util.Log;

import com.android.volley.Request;

import java.lang.reflect.Field;

/**
 * 通过反射机制改变请求的Url。
 * Created by dengquan on 16-5-9.
 */
public class ChangeRequestUrl
{
    private static final String TAG = ChangeRequestUrl.class.getSimpleName();
    /**
     * 改变请求Url的方法
     * @param request
     * @return
     */
    public static Request changRequestUrl(Request request)
    {
        try
        {
            String host = request.getUrl().split(";")[0];
            LogUtils.i(TAG,"before url :"+request.getUrl());
            Class<?> mClass = request.getClass();
            Class<?> superClass = mClass.getSuperclass();
            Class<?> parent = superClass.getSuperclass();
            LogUtils.i(TAG,"super class :"+superClass.getName()+"   "+parent.toString());
            Field field = parent.getDeclaredField("mUrl");
            field.setAccessible(true);
            LogUtils.i(TAG,"field  :"+field.toString()+" field get :"+field.get(request));
            field.set(request,host + ";jsessionid=" + LoginManager.getSessionId());
            LogUtils.i(TAG,"after url :"+request.getUrl());
        }
        catch (NoSuchFieldException ex)
        {
            LogUtils.i(TAG,"no such field exception :"+ex.getMessage());
        }
        catch (IllegalAccessException ex)
        {
            LogUtils.i(TAG,"illeagl access exception :"+ex.getMessage());
        }
        catch (IllegalArgumentException ex)
        {
            ex.printStackTrace();
            LogUtils.i(TAG,"illegal argument exception :"+ex.getMessage());
        }

        return request;
    }
}
