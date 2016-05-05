package com.channelsoft.ggsj.login.model;

/**
 * 获取验证码
 * Created by dengquan on 16-3-24.
 */
public interface IGenerateCodeModel
{
    void generateCode(String phoneNumber);
}
