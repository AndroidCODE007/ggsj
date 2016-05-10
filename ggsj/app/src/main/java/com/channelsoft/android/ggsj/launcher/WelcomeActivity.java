package com.channelsoft.android.ggsj.launcher;

import android.content.Intent;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.channelsoft.android.ggsj.R;
import com.channelsoft.android.ggsj.databinding.ActivityWelcomeBinding;
import com.channelsoft.android.ggsj.home.activity.MainActivity;
import com.channelsoft.android.ggsj.login.activity.BossLoginActivity;
import com.channelsoft.android.ggsj.login.activity.StafLoginActivity;
import com.channelsoft.android.ggsj.utils.LoginManager;
import com.channelsoft.android.ggsj.utils.VersionCodeUtil;
import com.channelsoft.android.ggsj.databinding.*;


/**
 * 欢迎页面也是开机引导页面
 */
public class WelcomeActivity extends AppCompatActivity
{
    private static final String TAG = WelcomeActivity.class.getSimpleName();
    private ActivityWelcomeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_welcome);
        if(VersionCodeUtil.checkVersion())  //引导
        {
            binding.vsGuide.getViewStub().setVisibility(View.VISIBLE);
        }
        else  //欢迎页面
        {
            if(LoginManager.isLogin())
            {
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
            }
            else
            {
                startActivity(new Intent(WelcomeActivity.this,StafLoginActivity.class));
            }
        }
    }
}
