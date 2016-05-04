package com.channelsoft.android.ggsj.login.viewmodel;

import com.channelsoft.android.ggsj.login.bean.CompanyData;
import com.channelsoft.android.ggsj.login.listener.OnLoginListener;
import com.channelsoft.android.ggsj.login.model.LoginModel;

/**
 * 登录
 * Created by dengquan on 16-3-28.
 */
public class LoginViewModel implements ILoginViewModel , OnLoginListener
{
    private OnLoginView listener;
    private LoginModel loginModel;
    public LoginViewModel(OnLoginView listener)
    {
        this.listener = listener;
    }
    @Override
    public void login(String phoneNumber,String code)
    {
        loginModel = new LoginModel(this);
        loginModel.login(phoneNumber,code);
        if(listener != null)
        {
            listener.onLogining();
        }
    }

    @Override
    public void onLoginSuccess(CompanyData info)
    {
        if(listener != null)
        {
            listener.onLoginSuccess(info);
        }
    }

    @Override
    public void onLoginError(String errorMsg)
    {
        if(listener != null)
        {
            listener.onLoginError(errorMsg);
        }
    }

    public interface OnLoginView
    {
        void onLogining();

        void onLoginSuccess(CompanyData info);

        void onLoginError(String errorMsg);
    }
}

