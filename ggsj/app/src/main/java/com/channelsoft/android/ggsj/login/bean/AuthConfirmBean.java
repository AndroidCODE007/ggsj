package com.channelsoft.android.ggsj.login.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 老板确认授权登陆的数据
 * Created by dengquan on 16-5-18.
 */
public class AuthConfirmBean implements Parcelable
{
    private String authId;
    private String regId;
    private String deviceName;
    private String returnCouponRight;
    private String verifyCouponRight;
    private String orderRight;

    public String getAuthId()
    {
        return authId;
    }

    public void setAuthId(String authId)
    {
        this.authId = authId;
    }

    public String getRegId()
    {
        return regId;
    }

    public void setRegId(String regId)
    {
        this.regId = regId;
    }

    public String getDeviceName()
    {
        return deviceName;
    }

    public void setDeviceName(String deviceName)
    {
        this.deviceName = deviceName;
    }

    public String getReturnCouponRight()
    {
        return returnCouponRight;
    }

    public void setReturnCouponRight(String returnCouponRight)
    {
        this.returnCouponRight = returnCouponRight;
    }

    public String getVerifyCouponRight()
    {
        return verifyCouponRight;
    }

    public void setVerifyCouponRight(String verifyCouponRight)
    {
        this.verifyCouponRight = verifyCouponRight;
    }

    public String getOrderRight()
    {
        return orderRight;
    }

    public void setOrderRight(String orderRight)
    {
        this.orderRight = orderRight;
    }


    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(this.authId);
        dest.writeString(this.regId);
        dest.writeString(this.deviceName);
        dest.writeString(this.returnCouponRight);
        dest.writeString(this.verifyCouponRight);
        dest.writeString(this.orderRight);
    }

    public AuthConfirmBean()
    {
    }

    protected AuthConfirmBean(Parcel in)
    {
        this.authId = in.readString();
        this.regId = in.readString();
        this.deviceName = in.readString();
        this.returnCouponRight = in.readString();
        this.verifyCouponRight = in.readString();
        this.orderRight = in.readString();
    }

    public static final Parcelable.Creator<AuthConfirmBean> CREATOR = new Parcelable.Creator<AuthConfirmBean>()
    {
        @Override
        public AuthConfirmBean createFromParcel(Parcel source)
        {
            return new AuthConfirmBean(source);
        }

        @Override
        public AuthConfirmBean[] newArray(int size)
        {
            return new AuthConfirmBean[size];
        }
    };
}
