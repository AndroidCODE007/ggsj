package com.channelsoft.ggsj.launcher;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.channelsoft.ggsj.R;
import com.channelsoft.ggsj.databinding.ActivityWelcomeBinding;
import com.channelsoft.ggsj.home.activity.MainActivity;
import com.channelsoft.ggsj.login.activity.BossLoginActivity;
import com.channelsoft.ggsj.utils.LoginManager;
import com.channelsoft.ggsj.utils.VersionCodeUtil;

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
        binding = DataBindingUtil.setContentView(this,R.layout.activity_welcome);
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
                startActivity(new Intent(WelcomeActivity.this,BossLoginActivity.class));
            }
        }
    }
}
