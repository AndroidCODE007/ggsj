package com.channelsoft.android.ggsj.login.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.channelsoft.android.ggsj.R;
import com.channelsoft.android.ggsj.base.activity.BaseActivity;
import com.channelsoft.android.ggsj.databinding.ActivityAuthorizationLoginBinding;
import com.channelsoft.android.ggsj.login.bean.AuthConfirmBean;
import com.channelsoft.android.ggsj.login.listener.OnAuthConfirmListener;
import com.channelsoft.android.ggsj.login.model.AuthConfirmModel;
import com.channelsoft.android.ggsj.login.viewmodel.AuthLoginViewModelImpl;
import com.channelsoft.android.ggsj.login.viewmodel.IAuthLoginViewModel;
import com.channelsoft.android.ggsj.utils.LogUtils;
import com.channelsoft.android.ggsj.utils.LoginManager;

/**
 * Created by chenyg on 2016/5/17.
 */
public class AuthorizationLoginActivity extends BaseActivity
        implements AuthLoginViewModelImpl.OnAuthLoginView,View.OnClickListener,
        OnAuthConfirmListener{

    private static final String TAG = AuthorizationLoginActivity.class.getSimpleName();
    private ActivityAuthorizationLoginBinding binding;
    private IAuthLoginViewModel model;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_authorization_login);
        model = new AuthLoginViewModelImpl(this);
        model.authLogin(getIntent().getStringExtra("regId"),getIntent().getStringExtra("authId"));
        binding.btnAuthorization.setOnClickListener(this);
    }

    @Override
    public void onAuthLoginSuccess(String deviceName, String deviceModel) {
        binding.deviceModel.setText(deviceModel);
        binding.deviceName.setText(deviceName);
    }

    @Override
    public void onAuthLoginError() {

    }

    @Override
    public void onAuthConfirmSuccess()
    {
        finish();
    }

    @Override
    public void onAuthCOnfirmError(String returnCode)
    {
        showToast("授权失败，再次扫码授权");
    }

    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.btnAuthorization:
                authConfirm();
                break;
        }
    }

    private void authConfirm()
    {
        AuthConfirmBean bean = new AuthConfirmBean();
        bean.setAuthId(getIntent().getStringExtra("authId"));
        bean.setRegId(getIntent().getStringExtra("regId"));
        bean.setDeviceName(binding.deviceName.getText().toString().trim());
        if(binding.cbCheckTicket.isChecked())
        {
            bean.setVerifyCouponRight("1");
        }
        else
        {
            bean.setVerifyCouponRight("0");
        }

        if(binding.cbReturnTicket.isChecked())
        {
            bean.setReturnCouponRight("1");
        }
        else
        {
            bean.setReturnCouponRight("0");
        }


        if(binding.cbOrder.isChecked())
        {
            bean.setOrderRight("1");
        }
        else
        {
            bean.setOrderRight("0");
        }
        AuthConfirmModel model = new AuthConfirmModel(this);
        model.onAuthConfirm(bean);
    }
}
