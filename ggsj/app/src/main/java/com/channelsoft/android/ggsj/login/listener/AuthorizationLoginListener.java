package com.channelsoft.android.ggsj.login.listener;

/**
 * Created by lenovo on 2016/5/17.
 */
public interface AuthorizationLoginListener {
    void AuthorizationLoginSuccess(String deviceName,String deviceModel);
    void AuthorizationLoginError();
}
