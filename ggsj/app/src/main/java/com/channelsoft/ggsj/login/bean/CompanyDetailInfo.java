package com.channelsoft.ggsj.login.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Parcel;
import android.os.Parcelable;

import com.channelsoft.ggsj.BR;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dengquan on 16-3-28.
 */
public class CompanyDetailInfo extends BaseObservable implements Parcelable
{
    private String entId;
    private String shortName;
    private String helpdeskUrl;
    private List<FunctionInfo> functions;

    @Bindable
    public String getEntId()
    {
        return entId;
    }

    public void setEntId(String entId)
    {
        this.entId = entId;
        notifyPropertyChanged(BR.entId);
    }

    @Bindable
    public String getHelpdeskUrl()
    {
        return helpdeskUrl;
    }

    public void setHelpdeskUrl(String helpdeskUrl)
    {
        this.helpdeskUrl = helpdeskUrl;
        notifyPropertyChanged(BR.helpdeskUrl);
    }

    @Bindable
    public String getShortName()
    {
        return shortName;
    }

    public void setShortName(String shortName)
    {
        this.shortName = shortName;
        notifyPropertyChanged(BR.shortName);
    }

    @Bindable
    public List<FunctionInfo> getFunctions()
    {
        return functions;
    }

    public void setFunctions(List<FunctionInfo> functions)
    {
        this.functions = functions;
        notifyPropertyChanged(BR.functions);
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(this.entId);
        dest.writeString(this.shortName);
        dest.writeString(this.helpdeskUrl);
        dest.writeList(this.functions);
    }
    protected CompanyDetailInfo(Parcel in)
    {
        this.entId = in.readString();
        this.shortName = in.readString();
        this.helpdeskUrl = in.readString();
        this.functions = new ArrayList<FunctionInfo>();
        in.readList(this.functions, FunctionInfo.class.getClassLoader());
    }

    public static final Parcelable.Creator<CompanyDetailInfo> CREATOR = new Parcelable.Creator<CompanyDetailInfo>()
    {
        @Override
        public CompanyDetailInfo createFromParcel(Parcel source)
        {
            return new CompanyDetailInfo(source);
        }

        @Override
        public CompanyDetailInfo[] newArray(int size)
        {
            return new CompanyDetailInfo[size];
        }
    };
}
