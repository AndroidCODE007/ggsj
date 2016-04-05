package com.channelsoft.ggsj.login.bean;

import com.channelsoft.ggsj.base.bean.BaseInfo;

import java.util.List;

/**
 * Created by dengquan on 16-3-28.
 */
public class CompanyInfo
{
    private String tokenId;
    private List<CompanyDetailInfo> companyInfo;

    public List<CompanyDetailInfo> getCompanyInfo()
    {
        return companyInfo;
    }

    public void setCompanyInfo(List<CompanyDetailInfo> companyInfo)
    {
        this.companyInfo = companyInfo;
    }

    public String getTokenId()
    {
        return tokenId;
    }

    public void setTokenId(String tokenId)
    {
        this.tokenId = tokenId;
    }
}
