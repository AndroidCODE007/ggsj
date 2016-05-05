package com.channelsoft.ggsj.login.listener;

import com.channelsoft.ggsj.login.bean.CompanyData;

/**
 * Created by dengquan on 16-3-28.
 */
public interface OnLoginListener
{
    void onLoginSuccess(CompanyData info);

    void onLoginError(String errorMsg);
}
