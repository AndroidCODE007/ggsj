package com.channelsoft.ggsj.http;

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
    private static final String TAG = CookieStringRequest.class.getSimpleName();
    private String mHeader;
    private String cookie;
    private Response.Listener<String> mListener;
    private Map<String,String> map = new HashMap<>();
    private static final String COOKIE = "Cookie";
    public CookieStringRequest(int method, String url, Response.Listener<String> listener,
                               Response.ErrorListener errorListener)
    {
        super(method, url, listener, errorListener);
        mListener = listener;
    }

    @Override
    protected void deliverResponse(String response)
    {
        LogUtils.i(TAG,"deliver response   ");
        mListener.onResponse(response);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError
    {
        LogUtils.i(TAG,"get headers    ");
        map.put(COOKIE,cookie);
        return map;
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
        try
        {
            String str = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            mHeader = response.headers.toString();
            LogUtils.i(TAG,"response  headers  :"+response.headers.toString());
            Pattern pattern = Pattern.compile("Set-Cookie,*?;");
            Matcher  matcher = pattern.matcher(mHeader);
            LogUtils.i(TAG,"matcher result :"+matcher.find());
            if(matcher.find())
            {
                cookie = matcher.group();
                LogUtils.i(TAG,"cookie from response :"+cookie);
            }
            cookie = cookie.subSequence(11,cookie.length() - 1).toString();
            //将cookie字符串添加到jsonObject中，该jsonObject会被deliverResponse递交，
            // 调用请求时则能在onResponse中得到
            return Response.success(cookie, HttpHeaderParser.parseCacheHeaders(response));
        }
        catch (UnsupportedEncodingException ex)
        {
            LogUtils.i(TAG,ex.getMessage());
        }

        return super.parseNetworkResponse(response);
    }
}
