package com.channelsoft.ggsj.base.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

/**
 * Created by dengquan on 16-3-23.
 */
public class BaseActivity extends AppCompatActivity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
//        {
//            Window window = getWindow();
//            // Translucent status bar
//            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
//                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        }
    }

    /**
     * Toast方法
     * @param str
     */
    private void showToast(String str)
    {
        Toast.makeText(this,str,Toast.LENGTH_LONG).show();
    }


    private void hasNetWork()
    {
        return;
    }
}
