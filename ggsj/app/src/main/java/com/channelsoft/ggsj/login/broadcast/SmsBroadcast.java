package com.channelsoft.ggsj.login.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

import com.channelsoft.ggsj.utils.LogUtils;

/**
 * 监听短信的广播
 * Created by dengquan on 16-3-28.
 */
public class SmsBroadcast extends BroadcastReceiver
{
    private static final String TAG = SmsBroadcast.class.getSimpleName();
    @Override
    public void onReceive(Context context, Intent intent)
    {
        LogUtils.i(TAG,"sms broad cast on receive");
        Bundle bundle = intent.getExtras();
        SmsMessage message = null;
        if(bundle != null)
        {
            Object[] objects = (Object[])bundle.get("pdus");
            for(Object object : objects)
            {
                message = SmsMessage.createFromPdu((byte[]) object);
            }
            if(message.getMessageBody().startsWith("测试"))
            {
                Toast.makeText(context, message.getMessageBody(), Toast.LENGTH_SHORT).show();
                LogUtils.i(TAG,"sms content :  "+message.getMessageBody());
            }
        }
    }
}
