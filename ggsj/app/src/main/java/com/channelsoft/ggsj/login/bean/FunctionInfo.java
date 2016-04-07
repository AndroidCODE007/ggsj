package com.channelsoft.ggsj.login.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dengquan on 16-4-5.
 */
public class FunctionInfo implements Parcelable
{
    private String funType;
    private int level;
    private String funName;
    private List<SubFunctionInfo> subFunction;

    public String getFunType()
    {
        return funType;
    }

    public void setFunType(String funType)
    {
        this.funType = funType;
    }

    public List<SubFunctionInfo> getSubFunction()
    {
        return subFunction;
    }

    public void setSubFunction(List<SubFunctionInfo> subFunction)
    {
        this.subFunction = subFunction;
    }

    public String getFunName()
    {
        return funName;
    }

    public void setFunName(String funName)
    {
        this.funName = funName;
    }

    public int getLevel()
    {
        return level;
    }

    public void setLevel(int level)
    {
        this.level = level;
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
        dest.writeList(this.subFunction);
    }

    public FunctionInfo()
    {
    }

    protected FunctionInfo(Parcel in)
    {
        this.funType = in.readString();
        this.level = in.readInt();
        this.funName = in.readString();
        this.subFunction = new ArrayList<SubFunctionInfo>();
        in.readList(this.subFunction, SubFunctionInfo.class.getClassLoader());
    }

    public static final Parcelable.Creator<FunctionInfo> CREATOR = new Parcelable.Creator<FunctionInfo>()
    {
        @Override
        public FunctionInfo createFromParcel(Parcel source)
        {
            return new FunctionInfo(source);
        }

        @Override
        public FunctionInfo[] newArray(int size)
        {
            return new FunctionInfo[size];
        }
    };
}
