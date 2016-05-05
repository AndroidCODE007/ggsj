package com.channelsoft.ggsj.login.viewmodel;

import com.channelsoft.ggsj.login.bean.CompanyData;
import com.channelsoft.ggsj.login.listener.OnLoginListener;
import com.channelsoft.ggsj.login.model.VerifyCodeModel;

/**
 * 验证验证码
 * Created by dengquan on 16-3-28.
 */
public class VerifyCodeViewModel implements IVerifyCodeViewModel, OnLoginListener
{
    private OnLoginView listener;
    private VerifyCodeModel loginModel;
    public VerifyCodeViewModel(OnLoginView listener)
    {
        this.listener = listener;
    }
    @Override
    public void login(String phoneNumber,String code)
    {
        loginModel = new VerifyCodeModel(this);
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

