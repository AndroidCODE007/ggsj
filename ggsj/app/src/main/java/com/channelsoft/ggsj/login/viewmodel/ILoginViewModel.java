package com.channelsoft.ggsj.login.viewmodel;

/**
 * 登陆，选择商户登陆
 * Created by dengquan on 16-5-3.
 */
public interface ILoginViewModel
{
    void  login(String entId);

    void onSuccess();


    void onError();
}
