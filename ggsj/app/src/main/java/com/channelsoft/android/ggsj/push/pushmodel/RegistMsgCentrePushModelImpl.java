package com.channelsoft.android.ggsj.push.pushmodel;


import com.channelsoft.android.ggsj.push.listener.OnRegistMsgCentreListener;
import com.channelsoft.android.ggsj.push.model.IRegistMsgCentreModel;
import com.channelsoft.android.ggsj.push.model.RegistMsgCentreModelImpl;

/**
 * Created by chenyg on 2016/5/3.
 */
public class RegistMsgCentrePushModelImpl implements IRegistMsgCentrePushModel,OnRegistMsgCentreListener {

    private OnRegistPush listener;

    public RegistMsgCentrePushModelImpl(OnRegistPush listener) {
        this.listener = listener;
    }

    IRegistMsgCentreModel model;

    @Override
    public void registMsgCentre(String regId, String deviceOsVersion, String deviceModel, String appVersion, String alias, String userAccount, String osType) {
        model =  new RegistMsgCentreModelImpl(this);
        model.registMsgCentre(regId,deviceOsVersion,deviceModel,appVersion,alias, userAccount,osType);
    }

    @Override
    public void onRegistSuccess() {
        listener.onRegistPushSuccess();
    }

    @Override
    public void onRegistError() {
        listener.onRegistPushError();
    }

    public interface OnRegistPush{
        void onRegistPushSuccess();
        void onRegistPushError();
    }
}
