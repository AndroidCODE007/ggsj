package com.channelsoft.android.ggsj.login.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;


import com.channelsoft.android.ggsj.R;
import com.channelsoft.android.ggsj.base.GlobalApplication;
import com.channelsoft.android.ggsj.base.activity.BaseActivity;
import com.channelsoft.android.ggsj.login.bean.CompanyData;
import com.channelsoft.android.ggsj.login.broadcast.SmsBroadcast;
import com.channelsoft.android.ggsj.login.viewmodel.GenerateCodeViewModel;
import com.channelsoft.android.ggsj.login.viewmodel.LoginViewModel;
import com.channelsoft.android.ggsj.login.viewmodel.VerifyCodeViewModel;
import com.channelsoft.android.ggsj.utils.LogUtils;
import com.channelsoft.android.ggsj.utils.LoginManager;
import com.channelsoft.android.ggsj.utils.PermissionManager;
import com.channelsoft.android.ggsj.view.CountDownBtn;
import com.channelsoft.android.ggsj.databinding.ActivityBossLoginBinding;

import java.util.List;

/**
 * 老板登录
 * 需要读取手机的短信,使用广播机制.
 * 在android6.0系统中需要进行权限的判断
 * Created by dengquan on 16-3-23.
 */
public class BossLoginActivity extends BaseActivity implements
        View.OnClickListener,CountDownBtn.GetGenerateCodeListener,GenerateCodeViewModel.IGenerateCodeView,
        com.channelsoft.android.ggsj.login.viewmodel.VerifyCodeViewModel.OnLoginView
{
    private static final String TAG = BossLoginActivity.class.getSimpleName();
    private ActivityBossLoginBinding binding ;
    private GenerateCodeViewModel generateCodeViewModel;
    private VerifyCodeViewModel loginViewModel;
    private static final int REQUEST_CODE_SOME_FEATURES_PERMISSIONS = 101;

    private String phoneNumber;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_boss_login);
        bindViewModel();
        List<String> list =
                PermissionManager.PermissionV4.isThatPermissionGranted(this,
                        Manifest.permission.RECEIVE_SMS,Manifest.permission.READ_SMS);
        LogUtils.i(TAG, "list size " + list.size());
        if(list != null && list.size() > 0)
        {
            final List<String> list1 =
                    PermissionManager.PermissionV4.shouldShowDialog(this,
                            Manifest.permission.RECEIVE_SMS, Manifest.permission.READ_SMS);
            if(list1 != null && list1.size() > 0)
            {
                    showMessageOKCancel("You need to allow access to Contacts",
                            new DialogInterface.OnClickListener()
                            {
                                @Override
                                public void onClick(DialogInterface dialog, int which)
                                {
                                    ActivityCompat.requestPermissions(BossLoginActivity.this,
                                            list1.toArray(new String[list1.size()]),
                                            REQUEST_CODE_SOME_FEATURES_PERMISSIONS);
                                }
                            });
                    return;

            }
            ActivityCompat.requestPermissions(BossLoginActivity.this, list.toArray(new String[list.size()]),
                    REQUEST_CODE_SOME_FEATURES_PERMISSIONS);
        }
        else
        {
            LogUtils.i(TAG, " register broad cast ");
            SmsBroadcast smsBroadcast = new SmsBroadcast();
            IntentFilter filter = new IntentFilter();
            filter.addAction("android.provider.Telephony.SMS_RECEIVED");
            registerReceiver(smsBroadcast, filter);
        }
    }


    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.bntLogin:
                loginViewModel.login(binding.tvPhone.getText().toString().trim(),
                        binding.tvPassword.getText().toString().trim());

                phoneNumber = binding.tvPhone.getText().toString().trim();

                break;
        }
    }

    private void bindViewModel()
    {
        generateCodeViewModel = new GenerateCodeViewModel();
        loginViewModel = new VerifyCodeViewModel(this);
        binding.bntLogin.setOnClickListener(this);
        binding.btnGenerateCode.setActivity(this);
        binding.btnGenerateCode.setGenerateCodeListener(this);
    }

    @Override
    public void getGenerateCode()
    {
        if(generateCodeViewModel.checkPhoneNumber(binding.tvPhone.getText().toString().trim()))
        {
            generateCodeViewModel.getGenerateCode(binding.tvPhone.getText().toString().trim());
        }
        else
        {
            binding.btnGenerateCode.reset();
        }
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        binding.btnGenerateCode.cance();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
    {
        switch (requestCode)
        {
            case REQUEST_CODE_SOME_FEATURES_PERMISSIONS:
                for(int i=0;i<permissions.length;i++)
                {
                    if(grantResults[i] == PackageManager.PERMISSION_GRANTED)
                    {
                        LogUtils.i(TAG,"permisssion granted "+permissions[i]);
                        SmsBroadcast smsBroadcast = new SmsBroadcast();
                        IntentFilter filter = new IntentFilter();
                        filter.addAction("android.provider.Telephony.SMS_RECEIVED");
                        registerReceiver(smsBroadcast, filter);
                    }
                    else
                    {
                        LogUtils.i(TAG,"permisssion deny "+permissions[i]);
                    }
                }
                break;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onGenerateCodeError()
    {

    }

    @Override
    public void onLoading()
    {

    }

    @Override
    public void onGenerateCodeSuccess()
    {

    }

    @Override
    public void onLogining()
    {

    }

    @Override
    public void onLoginSuccess(CompanyData info) {
        LoginManager.savePhoneNumber(phoneNumber);
        GlobalApplication.instance.registToMiPush();
        startActivity(ChooseEntActivity.newIntent(BossLoginActivity.this,info));
    }


    @Override
    public void onLoginError(String errorMsg)
    {

    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(BossLoginActivity.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

}
