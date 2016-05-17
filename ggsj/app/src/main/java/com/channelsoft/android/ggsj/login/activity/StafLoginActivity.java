package com.channelsoft.android.ggsj.login.activity;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;

import com.channelsoft.android.ggsj.R;
import com.channelsoft.android.ggsj.base.activity.BaseActivity;
import com.channelsoft.android.ggsj.databinding.ActivityStaffLoginBinding;
import com.channelsoft.android.ggsj.login.model.GetWxAuthUrlModel;
import com.channelsoft.android.ggsj.login.model.StaffLoginModel;
import com.channelsoft.android.ggsj.push.bean.AuthMiMsg;
import com.channelsoft.android.ggsj.push.receiver.PushMsgReceiver;
import com.channelsoft.android.ggsj.utils.LogUtils;

/**
 * 员工登录
 * Created by dengquan on 16-3-23.
 */
public class StafLoginActivity extends BaseActivity implements
        View.OnClickListener,GetWxAuthUrlModel.IStaffLoginView,StaffLoginModel.OnStaffLoginListener
{
    private static final String TAG = StafLoginActivity.class.getSimpleName();
    public static final String STAFF_LOGIN_ACTION = "com.channelsoft.com.staff.login.action";
    private ActivityStaffLoginBinding binding;
    private GetWxAuthUrlModel model;
    private StaffLoginReceiver registerReceiver;
    private StaffLoginModel staffLoginModel;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_staff_login);
        binding.bossLoginBtn.setOnClickListener(this);
        initWxUrl();
        registerStaffReceiver();
    }

    private void initWxUrl()
    {
        model = new GetWxAuthUrlModel(this);
        model.getWxAuthUrl();
    }

    private void registerStaffReceiver()
    {
        registerReceiver = new StaffLoginReceiver();
        IntentFilter filter = new IntentFilter(STAFF_LOGIN_ACTION);
        registerReceiver(registerReceiver,filter);
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

    @Override
    public void setBitmap(Bitmap bitmap)
    {
        binding.urlImg.setImageBitmap(bitmap);
    }

    public class StaffLoginReceiver extends BroadcastReceiver
    {
        @Override
        public void onReceive(Context context, Intent intent)
        {
            LogUtils.i(TAG,"receiver message  "+((AuthMiMsg) intent.getParcelableExtra(PushMsgReceiver.AUTHMIMSG)).toString());
            staffLoginModel = new StaffLoginModel(StafLoginActivity.this);
            staffLoginModel.onStaffLogin((AuthMiMsg) intent.getParcelableExtra(PushMsgReceiver.AUTHMIMSG));
        }
    }


    @Override
    public void onStaffLoginSuccess()
    {

    }

    @Override
    public void onStaffLoginError(String returnCode)
    {

    }

    @Override
    protected void onDestroy()
    {
        this.unregisterReceiver(registerReceiver);
        super.onDestroy();
    }
}
