package com.channelsoft.android.ggsj.login.bean;


import com.channelsoft.android.ggsj.base.bean.BaseInfo;

/**
 * Created by dengquan on 16-3-28.
 */
public class LoginInfo extends BaseInfo
{
    private CompanyInfo data;

    public CompanyInfo getData()
    {
        return data;
    }

    public void setData(CompanyInfo data)
    {
        this.data = data;
    }
}
