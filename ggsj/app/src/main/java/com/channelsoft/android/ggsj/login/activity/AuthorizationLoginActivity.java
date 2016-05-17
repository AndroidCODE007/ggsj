package com.channelsoft.android.ggsj.login.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.channelsoft.android.ggsj.R;
import com.channelsoft.android.ggsj.base.activity.BaseActivity;
import com.channelsoft.android.ggsj.databinding.ActivityAuthorizationLoginBinding;
import com.channelsoft.android.ggsj.login.viewmodel.AuthLoginViewModelImpl;
import com.channelsoft.android.ggsj.login.viewmodel.IAuthLoginViewModel;
import com.channelsoft.android.ggsj.utils.LogUtils;
import com.channelsoft.android.ggsj.utils.LoginManager;

/**
 * Created by chenyg on 2016/5/17.
 */
public class AuthorizationLoginActivity extends BaseActivity implements AuthLoginViewModelImpl.OnAuthLoginView{

    private static final String TAG = AuthorizationLoginActivity.class.getSimpleName();
    private ActivityAuthorizationLoginBinding binding;
    private IAuthLoginViewModel model;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_authorization_login);

        LogUtils.i(TAG,"regId = " + LoginManager.getRegId() + "     authId = ");
    }

    @Override
    public void onAuthLoginSuccess(String deviceName, String deviceModel) {

    }

    @Override
    public void onAuthLoginError() {

    }
}
