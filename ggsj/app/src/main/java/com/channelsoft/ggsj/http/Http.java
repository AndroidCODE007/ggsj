package com.channelsoft.ggsj.http;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.channelsoft.ggsj.base.GlobalApplication;
import com.channelsoft.ggsj.base.bean.BaseInfo;
import com.channelsoft.ggsj.utils.LogUtils;
import com.channelsoft.ggsj.utils.LoginManager;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dengquan on 16-3-23.
 */
public class Http
{
    public static final String CHANGE_TIME_OUT = "111";
    public static final String RETURNCODE_OK = "00";
    private static final String TAG = Http.class.getSimpleName();
    private static final int CONNECTION_TIMEOUT = 5 * 1000;
    public static final int TIMES_OF_RETRY = 1;
    public static RequestQueue requestQueue = Volley.newRequestQueue((Context) GlobalApplication.getInstance(), new OkHttpStack());

    private Http()
    {

    }

    public static void addRequest(Request<?> request, Object tag)
    {
        if (tag != null)
        {
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

    public static void cancelAll(Object tag)
    {
        requestQueue.cancelAll(tag);
    }


    /**
     * session过期处理
     *
     * @param requestUrl
     * @param params
     */
    public static void changeTimeOut(final String requestUrl,
                                     final Map<String, String> params, final StringRequest request,
                                     final Response.Listener listener, final Response.ErrorListener errorListener)
    {
        String URL = LoginManager.getHelpDesjUrl() + Url.Login.RE_LOGIN;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String s)
                    {
                        LogUtils.i(TAG,"change time out :"+s);
                        Gson gson = new Gson();
                        BaseInfo info = gson.fromJson(s, BaseInfo.class);
                        if(Http.RETURNCODE_OK.equals(info.getReturnCode()))
                        {
                            Http.addRequest(request,"");
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError volleyError)
                    {
                        LogUtils.i(TAG,"change time out error:"+volleyError.getMessage());
                        if(errorListener != null)
                        {
                            errorListener.onErrorResponse(volleyError);
                        }
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                //老板的sessionId过期
                Map<String,String> map =  new HashMap<>();
                map.put("phone", "18812345678");
                map.put("entId", LoginManager.getEntId());
                map.put("tokenId",LoginManager.getToken());
                return map;
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response)
            {

                try
                {
                    Map<String, String> responseHeaders = response.headers;
                    String rawCookies = responseHeaders.get("Set-Cookie");
                    String dataString = new String(response.data, "GBK");
                    LogUtils.i(TAG, "cookie :" + rawCookies + "   " + responseHeaders.toString());
                    saveSession(rawCookies);
                    return Response.success(dataString, HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException ex)
                {
                    return Response.error(new ParseError(ex));
                }
            }
        };
        Http.addRequest(stringRequest,TAG);

    }

    private static void saveSession(String cookie)
    {

        String[] s = cookie.split(";");
        for(String string :s)
        {
            if (string.contains("JSESSIONID"))
            {
                LogUtils.e("授权登录获取sessionid=", string.substring(11));
                LoginManager.saveSessionId(string.substring(11));
            }
        }

    }

}
