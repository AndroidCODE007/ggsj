package com.channelsoft.android.ggsj.login.bean;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dengquan on 16-3-28.
 */
public class CompanyInfo implements Parcelable
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


    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(this.tokenId);
        dest.writeList(this.companyInfo);
    }

    public CompanyInfo()
    {
    }

    protected CompanyInfo(Parcel in)
    {
        this.tokenId = in.readString();
        this.companyInfo = new ArrayList<CompanyDetailInfo>();
        in.readList(this.companyInfo, CompanyDetailInfo.class.getClassLoader());
    }

    public static final Creator<CompanyInfo> CREATOR = new Creator<CompanyInfo>()
    {
        @Override
        public CompanyInfo createFromParcel(Parcel source)
        {
            return new CompanyInfo(source);
        }

        @Override
        public CompanyInfo[] newArray(int size)
        {
            return new CompanyInfo[size];
        }
    };
}
