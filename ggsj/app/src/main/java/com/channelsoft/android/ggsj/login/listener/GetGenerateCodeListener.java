package com.channelsoft.android.ggsj.login.listener;


import com.channelsoft.android.ggsj.base.bean.BaseInfo;

/**
 * Created by dengquan on 16-3-24.
 */
public interface GetGenerateCodeListener
{
    void onSuccess(BaseInfo info);

    void onError(BaseInfo info);
}
