package com.channelsoft.ggsj.login.model;

import android.text.method.HideReturnsTransformationMethod;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.channelsoft.ggsj.http.Http;
import com.channelsoft.ggsj.http.Url;
import com.channelsoft.ggsj.login.listener.OnLoginListener;
import com.channelsoft.ggsj.utils.LogUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dengquan on 16-3-28.
 */
public class LoginModel implements ILoginModel
{
    private static final String TAG = LoginModel.class.getSimpleName();
    private OnLoginListener listener;

    public LoginModel(OnLoginListener listener)
    {
        this.listener = listener;
    }

    @Override
    public void login(final String phoneNumber, final String code)
    {
        StringRequest request = new StringRequest(Request.Method.POST,Url.HOST + Url.Login.VERIFY_CODE,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String s)
                    {
                        LogUtils.i(TAG, s);
                        if(listener != null)
                        {
                            listener.onLoginSuccess();
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError volleyError)
                    {
                        if(listener != null)
                        {
                            listener.onLoginError();
                        }
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                Map<String,String> map = new HashMap<>();
                map.put("phone",phoneNumber);
                map.put("code",code);
                map.put("action",Url.ACTION);
                return map;
            }
        };
        Http.addRequest(request,"Login");
    }
}
