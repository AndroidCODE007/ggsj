package com.channelsoft.android.ggsj.login.activity;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.channelsoft.android.ggsj.R;
import com.channelsoft.android.ggsj.base.activity.BaseActivity;
import com.channelsoft.android.ggsj.databinding.ActivityStaffLoginBinding;
import com.channelsoft.android.ggsj.login.model.GetWxAuthUrlModel;

/**
 * 员工登录
 * Created by dengquan on 16-3-23.
 */
public class StafLoginActivity extends BaseActivity implements View.OnClickListener
{
    private ActivityStaffLoginBinding binding;
    private GetWxAuthUrlModel model;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_staff_login);
        binding.bossLoginBtn.setOnClickListener(this);
        initWxUrl();
    }

    private void initWxUrl()
    {
        model = new GetWxAuthUrlModel();
        model.getWxAuthUrl();
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.bossLoginBtn:
                startActivity(new Intent(StafLoginActivity.this,BossLoginActivity.class));
                break;
        }
    }
}
