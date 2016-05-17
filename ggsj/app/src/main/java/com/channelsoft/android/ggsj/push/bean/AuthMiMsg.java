package com.channelsoft.android.ggsj.push.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by chenyg on 2016/5/5.
 */
public class AuthMiMsg implements Parcelable
{
    private String entId;

    private String appNodeUrl;

    private String bizType;

    private String deviceName;//员工设备名

    private String returnCouponRight;//员工是否有返券权限

    private String verifyCouponRight;//员工是否有验券权限

    private String orderRight;//员工是否有订单权限

    private String deskSwitchStatus;//桌位开关

    private String orderId;//订单ID

    private String notifyTitle;//消息中需要通知栏通知的标题

    private String dialogContent;//消息中需要弹窗显示的内容

    private String fontSize;//消息中心弹出字体的内容

    private String couplet;//小票打印联数

    private String autoPrintKitchenOrder;//顾客下单自动打印厨房单

    private String autoPrintReceipt;//顾客下单自动打印小票

    private String printStatus;//云端打印机连接状态

    public String getEntId() {
        return entId;
    }

    public void setEntId(String entId) {
        this.entId = entId;
    }

    public String getAppNodeUrl() {
        return appNodeUrl;
    }

    public void setAppNodeUrl(String appNodeUrl) {
        this.appNodeUrl = appNodeUrl;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getReturnCouponRight() {
        return returnCouponRight;
    }

    public void setReturnCouponRight(String returnCouponRight) {
        this.returnCouponRight = returnCouponRight;
    }

    public String getVerifyCouponRight() {
        return verifyCouponRight;
    }

    public void setVerifyCouponRight(String verifyCouponRight) {
        this.verifyCouponRight = verifyCouponRight;
    }

    public String getOrderRight() {
        return orderRight;
    }

    public void setOrderRight(String orderRight) {
        this.orderRight = orderRight;
    }

    public String getDeskSwitchStatus() {
        return deskSwitchStatus;
    }

    public void setDeskSwitchStatus(String deskSwitchStatus) {
        this.deskSwitchStatus = deskSwitchStatus;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getNotifyTitle() {
        return notifyTitle;
    }

    public void setNotifyTitle(String notifyTitle) {
        this.notifyTitle = notifyTitle;
    }

    public String getDialogContent() {
        return dialogContent;
    }

    public void setDialogContent(String dialogContent) {
        this.dialogContent = dialogContent;
    }

    public String getFontSize() {
        return fontSize;
    }

    public void setFontSize(String fontSize) {
        this.fontSize = fontSize;
    }

    public String getCouplet() {
        return couplet;
    }

    public void setCouplet(String couplet) {
        this.couplet = couplet;
    }

    public String getAutoPrintKitchenOrder() {
        return autoPrintKitchenOrder;
    }

    public void setAutoPrintKitchenOrder(String autoPrintKitchenOrder) {
        this.autoPrintKitchenOrder = autoPrintKitchenOrder;
    }

    public String getAutoPrintReceipt() {
        return autoPrintReceipt;
    }

    public void setAutoPrintReceipt(String autoPrintReceipt) {
        this.autoPrintReceipt = autoPrintReceipt;
    }

    public String getPrintStatus() {
        return printStatus;
    }

    public void setPrintStatus(String printStatus) {
        this.printStatus = printStatus;
    }

    @Override
    public String toString()
    {
        return this.getAppNodeUrl()+"   "+this.getAutoPrintKitchenOrder()+"   "+
                this.getAutoPrintReceipt()+"   "+this.getBizType()+"  "+this.getCouplet()+"   "
                +this.getDeviceName()+"   "+this.getEntId()+"  "+this.getFontSize();
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
        dest.writeString(this.appNodeUrl);
        dest.writeString(this.bizType);
        dest.writeString(this.deviceName);
        dest.writeString(this.returnCouponRight);
        dest.writeString(this.verifyCouponRight);
        dest.writeString(this.orderRight);
        dest.writeString(this.deskSwitchStatus);
        dest.writeString(this.orderId);
        dest.writeString(this.notifyTitle);
        dest.writeString(this.dialogContent);
        dest.writeString(this.fontSize);
        dest.writeString(this.couplet);
        dest.writeString(this.autoPrintKitchenOrder);
        dest.writeString(this.autoPrintReceipt);
        dest.writeString(this.printStatus);
    }

    protected AuthMiMsg(Parcel in)
    {
        this.entId = in.readString();
        this.appNodeUrl = in.readString();
        this.bizType = in.readString();
        this.deviceName = in.readString();
        this.returnCouponRight = in.readString();
        this.verifyCouponRight = in.readString();
        this.orderRight = in.readString();
        this.deskSwitchStatus = in.readString();
        this.orderId = in.readString();
        this.notifyTitle = in.readString();
        this.dialogContent = in.readString();
        this.fontSize = in.readString();
        this.couplet = in.readString();
        this.autoPrintKitchenOrder = in.readString();
        this.autoPrintReceipt = in.readString();
        this.printStatus = in.readString();
    }

    public static final Parcelable.Creator<AuthMiMsg> CREATOR = new Parcelable.Creator<AuthMiMsg>()
    {
        @Override
        public AuthMiMsg createFromParcel(Parcel source)
        {
            return new AuthMiMsg(source);
        }

        @Override
        public AuthMiMsg[] newArray(int size)
        {
            return new AuthMiMsg[size];
        }
    };
}
