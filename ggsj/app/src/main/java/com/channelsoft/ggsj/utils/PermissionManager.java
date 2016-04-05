package com.channelsoft.ggsj.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.channelsoft.ggsj.base.GlobalApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * 权限管理
 * Created by dengquan on 16-3-23.
 */
public class PermissionManager
{
    /**
     *适用于最小SDK  23(android m)
     * @param permission    对应的权限.格式:Manifest.permission.ACCOUNT_MANAGER
     * @return    true:权限已经分配    false: 需要弹出对话框来设置权限:
     * codeing :    requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
     */
    public static List<String> isThatPermissionGranted(Context context,String... permission)
    {
        List<String> list = new ArrayList<>();
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
        {
            return list;
        }
        for(String str : permission)
        {
            if (GlobalApplication.getInstance().checkSelfPermission(str)
                    != PackageManager.PERMISSION_GRANTED)
            {
                list.add(str);
            }
        }
        return  list;
    }


    /**
     *是否應該顯示對話框
     * @param permissions
     */
    public static List<String> shouldShowDialog(Activity context,String... permissions)
    {
        List<String> list = new ArrayList<>();
        if(permissions.length < 0  || Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
        {
            return  list;
        }
        else
        {
            for (String str : permissions)
            {
                if(!context.shouldShowRequestPermissionRationale(str))
                {
                    list.add(str);
                }
            }
        }
        return list;
    }


    public static class PermissionV4
    {
        /**
         *适用于最小SDK  23(android m)
         * @param permission    对应的权限.格式:Manifest.permission.ACCOUNT_MANAGER
         * @return    true:权限已经分配    false: 需要弹出对话框来设置权限:
         * codeing :    requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
         */
        public static List<String> isThatPermissionGranted(Context context,String... permission)
        {
            List<String> list = new ArrayList<>();
            if(permission.length <= 0)
            {
                return  list;
            }
            for(String str : permission)
            {
                if (ContextCompat.checkSelfPermission(context,str)
                        != PackageManager.PERMISSION_GRANTED)
                {
                    list.add(str);
                }
            }
            return list;
        }


        /**
         *是否應該顯示對話框
         * @param permissions
         */
        public static List<String> shouldShowDialog(Activity context,String... permissions)
        {
            List<String> list = new ArrayList<>();
            if(permissions.length < 0  || Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
            {
                return  list;
            }
            else
            {
                for (String str : permissions)
                {
                    if(!ActivityCompat.shouldShowRequestPermissionRationale(context,str))
                    {
                        list.add(str);
                    }
                }
            }
            return list;
        }

    }

}
