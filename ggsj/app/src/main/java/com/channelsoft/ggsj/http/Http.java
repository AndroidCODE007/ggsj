package com.channelsoft.ggsj.http;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.channelsoft.ggsj.base.GlobalApplication;
import com.channelsoft.ggsj.utils.LogUtils;

/**
 * Created by dengquan on 16-3-23.
 */
public class Http
{
    private static final String TAG = Http.class.getSimpleName();
    private static final int CONNECTION_TIMEOUT = 5 * 1000;
    public static final int TIMES_OF_RETRY = 1;
    public static RequestQueue requestQueue = Volley.newRequestQueue((Context)GlobalApplication.getInstance(),new OkHttpStack());
    private Http()
    {

    }

    public static void addRequest(Request<?> request, Object tag) {
        if (tag != null) {
            request.setTag(tag);
        }
        //给每个请求重设超时、重试次数
        request.setRetryPolicy(new DefaultRetryPolicy(
                CONNECTION_TIMEOUT,
                TIMES_OF_RETRY,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        requestQueue.add(request);
        LogUtils.d(TAG, request.getUrl());

    }

    public static void cancelAll(Object tag) {
        requestQueue.cancelAll(tag);
    }

}
