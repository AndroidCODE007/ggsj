package com.channelsoft.android.ggsj.push.pushmodel;

/**
 * Created by chenyg on 2016/5/3.
 */
public interface IRegistMsgCentrePushModel {
    void registMsgCentre(String regId, String deviceOsVersion, String deviceModel, String appVersion, String phoneNumber);
}
