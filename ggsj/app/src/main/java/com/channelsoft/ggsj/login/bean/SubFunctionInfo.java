package com.channelsoft.ggsj.login.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by dengquan on 16-4-5.
 */
public class SubFunctionInfo implements Parcelable
{
    private String funType;
    private int level;
    private String funName;

    public String getFunType()
    {
        return funType;
    }

    public void setFunType(String funType)
    {
        this.funType = funType;
    }

    public int getLevel()
    {
        return level;
    }

    public void setLevel(int level)
    {
        this.level = level;
    }

    public String getFunName()
    {
        return funName;
    }

    public void setFunName(String funName)
    {
        this.funName = funName;
    }


    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(this.funType);
        dest.writeInt(this.level);
        dest.writeString(this.funName);
    }

    public SubFunctionInfo()
    {
    }

    protected SubFunctionInfo(Parcel in)
    {
        this.funType = in.readString();
        this.level = in.readInt();
        this.funName = in.readString();
    }

    public static final Parcelable.Creator<SubFunctionInfo> CREATOR = new Parcelable.Creator<SubFunctionInfo>()
    {
        @Override
        public SubFunctionInfo createFromParcel(Parcel source)
        {
            return new SubFunctionInfo(source);
        }

        @Override
        public SubFunctionInfo[] newArray(int size)
        {
            return new SubFunctionInfo[size];
        }
    };
}
