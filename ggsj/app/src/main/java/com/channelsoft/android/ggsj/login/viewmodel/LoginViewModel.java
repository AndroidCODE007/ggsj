package com.channelsoft.android.ggsj.login.viewmodel;

import com.channelsoft.android.ggsj.login.model.ILoginModel;
import com.channelsoft.android.ggsj.login.model.LoginModel;

/**
 * Created by dengquan on 16-5-3.
 */
public class LoginViewModel implements ILoginViewModel
{
    private ILoginModel model;
    private ChooseEntActivityView view;
    public LoginViewModel(ChooseEntActivityView view)
    {
        this.view = view;
        model = new LoginModel(this);
    }
    @Override
    public void login(String entId)
    {
        if(view != null)
        {
            view.onLogining();
        }
        model.login(entId);
    }

    @Override
    public void onSuccess()
    {
        if(view != null)
        {
            view.onSuccess();
        }
    }

    @Override
    public void onError()
    {
        if(view != null)
        {
            view.onError(null);
        }
    }

    public interface ChooseEntActivityView
    {
        void onLogining();

        void onSuccess();

        void onError(String returnCode);
    }
}
