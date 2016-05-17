package com.channelsoft.android.ggsj.http;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.channelsoft.android.ggsj.utils.LogUtils;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 获取Cookie的类
 * Created by dengquan on 16-5-3.
 */
public class CookieStringRequest extends StringRequest
{
    private String url = "";
    private static final String TAG = CookieStringRequest.class.getSimpleName();
    public CookieStringRequest(int method, String url, Response.Listener<String> listener,
                               Response.ErrorListener errorListener)
    {
        super(method, url, listener, errorListener);
    }

    @Override
    protected void deliverResponse(String response)
    {
        LogUtils.i(TAG,"deliver response   ");
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError
    {
        LogUtils.i(TAG,"get params    ");
        return super.getParams();
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response)
    {

        return super.parseNetworkResponse(response);
    }
}
