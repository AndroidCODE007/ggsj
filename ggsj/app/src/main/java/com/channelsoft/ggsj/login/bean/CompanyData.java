package com.channelsoft.ggsj.login.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.channelsoft.ggsj.base.bean.BaseInfo;

/**
 * Created by dengquan on 16-4-5.
 */
public class CompanyData extends BaseInfo implements Parcelable
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

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeParcelable(this.data, flags);
    }

    protected CompanyData(Parcel in)
    {
        this.data = in.readParcelable(CompanyInfo.class.getClassLoader());
    }

    public static final Parcelable.Creator<CompanyData> CREATOR = new Parcelable.Creator<CompanyData>()
    {
        @Override
        public CompanyData createFromParcel(Parcel source)
        {
            return new CompanyData(source);
        }

        @Override
        public CompanyData[] newArray(int size)
        {
            return new CompanyData[size];
        }
    };
}
