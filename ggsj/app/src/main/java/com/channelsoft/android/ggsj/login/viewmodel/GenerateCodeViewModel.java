package com.channelsoft.android.ggsj.login.viewmodel;

import android.text.TextUtils;


import com.channelsoft.android.ggsj.base.bean.BaseInfo;
import com.channelsoft.android.ggsj.login.listener.GetGenerateCodeListener;
import com.channelsoft.android.ggsj.login.model.GenerateCodeModel;

import java.util.regex.Matcher;

/**
 * 获取验证码
 * Created by dengquan on 16-3-24.
 */
public class GenerateCodeViewModel implements IGenerateCodeViewModel,GetGenerateCodeListener
{
    @Override
    public void getGenerateCode(String phoneNumber)
    {
        GenerateCodeModel model = new GenerateCodeModel(this);
        model.generateCode(phoneNumber);
    }

    /**
     * 校验 手机号码是否合法
     * @param phone
     * @return
     */
    public boolean checkPhoneNumber(String phone)
    {
        if(TextUtils.isEmpty(phone) || phone.length() < 11)
        {
            return false;
        }

        return true;
    }

    @Override
    public void onError(BaseInfo info)
    {

    }

    @Override
    public void onSuccess(BaseInfo info)
    {

    }

    public interface IGenerateCodeView
    {
        void onLoading();

        void onGenerateCodeSuccess();

        void onGenerateCodeError();
    }

}
