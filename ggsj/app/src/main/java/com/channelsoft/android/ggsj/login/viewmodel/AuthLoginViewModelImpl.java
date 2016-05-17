package com.channelsoft.android.ggsj.login.viewmodel;

import com.channelsoft.android.ggsj.base.bean.BaseInfo;
import com.channelsoft.android.ggsj.login.listener.AuthorizationLoginListener;
import com.channelsoft.android.ggsj.login.model.AuthLoginModelImpl;
import com.channelsoft.android.ggsj.login.model.IAuthLoginModel;

/**
 * Created by lenovo on 2016/5/17.
 */
public class AuthLoginViewModelImpl implements IAuthLoginViewModel,AuthorizationLoginListener {

    private OnAuthLoginView listener;
    private IAuthLoginModel model;

    public AuthLoginViewModelImpl(OnAuthLoginView listener) {
        this.listener = listener;
    }

    @Override
    public void authLogin(String regId, String authId) {
        model = new AuthLoginModelImpl(this);
        model.authLogin(regId,authId);
    }

    @Override
    public void AuthorizationLoginSuccess(String deviceName, String deviceModel) {
        if(listener != null){
            listener.onAuthLoginSuccess(deviceName,deviceModel);
        }
    }

    @Override
    public void AuthorizationLoginError() {
        if(listener != null){
            listener.onAuthLoginError();
        }
    }

    public interface OnAuthLoginView{
        void onAuthLoginSuccess(String deviceName,String deviceModel);

        void onAuthLoginError();
    }
}
