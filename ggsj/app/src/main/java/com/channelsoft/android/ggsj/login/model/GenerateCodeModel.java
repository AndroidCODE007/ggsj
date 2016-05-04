package com.channelsoft.android.ggsj.login.model;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.channelsoft.android.ggsj.http.Http;
import com.channelsoft.android.ggsj.http.Url;
import com.channelsoft.android.ggsj.login.listener.GetGenerateCodeListener;
import com.channelsoft.android.ggsj.utils.LogUtils;


import java.util.HashMap;
import java.util.Map;

/**
 * 获取验证码
 * Created by dengquan on 16-3-24.
 */
public class GenerateCodeModel implements IGenerateCodeModel
{
    private static final String TAG = GenerateCodeModel.class.getSimpleName();
    private GetGenerateCodeListener generateCodeListener;

    public GenerateCodeModel(GetGenerateCodeListener listener)
    {
        this.generateCodeListener = listener;
    }

    @Override
    public void generateCode(final String phoneNumber)
    {
        StringRequest request = new StringRequest(Request.Method.POST, Url.HOST + Url.Login.GENERATE_CODE,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String s)
                    {
                        LogUtils.i(TAG, s.toString());
                        if(generateCodeListener != null)
                        {
                            generateCodeListener.onSuccess(null);
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError volleyError)
                    {
                        LogUtils.i(TAG,"exception cause " + volleyError.getCause());
                        if(generateCodeListener != null)
                        {
                            generateCodeListener.onError(null);
                        }
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                Map<String, String> map = new HashMap<>();
                map.put("phone", phoneNumber);
                map.put("action", Url.ACTION);
                return map;
            }
        };
        Http.addRequest(request, GenerateCodeModel.class.getSimpleName());
    }
}
