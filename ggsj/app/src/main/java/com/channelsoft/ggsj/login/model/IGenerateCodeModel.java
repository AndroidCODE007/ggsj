package com.channelsoft.ggsj.login.model;

import com.channelsoft.ggsj.login.listener.GetGenerateCodeListener;
import com.channelsoft.ggsj.login.viewmodel.IGenerateCodeViewModel;

/**
 * 获取验证码
 * Created by dengquan on 16-3-24.
 */
public interface IGenerateCodeModel
{
    void generateCode(String phoneNumber);
}
