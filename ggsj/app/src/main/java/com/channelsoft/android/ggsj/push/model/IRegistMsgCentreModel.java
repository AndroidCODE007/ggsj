package com.channelsoft.android.ggsj.push.model;

/**
 * Created by chenyg on 2016/5/3.
 */
public interface IRegistMsgCentreModel {
    void registMsgCentre(String regId, String deviceOsVersion, String deviceModel, String appVersion, String alias, String userAccount, String osType);
}
