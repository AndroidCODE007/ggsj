package com.channelsoft.ggsj.login.viewmodel;

import com.channelsoft.ggsj.login.listener.OnLoginListener;
import com.channelsoft.ggsj.login.model.LoginModel;

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
    }

    @Override
    public void onLoginSuccess()
    {

    }

    @Override
    public void onLoginError()
    {

    }

    public interface OnLoginView
    {
        void onLogining();

        void onLoginSuccess();

        void onLoginError();
    }
}
