package com.channelsoft.ggsj.login.model;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.channelsoft.ggsj.http.Http;
import com.channelsoft.ggsj.http.Url;
import com.channelsoft.ggsj.login.bean.CompanyData;
import com.channelsoft.ggsj.login.listener.OnLoginListener;
import com.channelsoft.ggsj.utils.LogUtils;
import com.channelsoft.ggsj.utils.LoginManager;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dengquan on 16-3-28.
 */
public class VerifyCodeModel implements IVerifyCodeModel
{
    private static final String TAG = VerifyCodeModel.class.getSimpleName();
    private OnLoginListener listener;
    StringRequest request = null;

    public VerifyCodeModel(OnLoginListener listener)
    {
        this.listener = listener;
    }

    @Override
    public void login(final String phoneNumber, final String code)
    {
        request = new StringRequest(Request.Method.POST, Url.HOST + Url.Login.VERIFY_CODE,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String s)
                    {
                        LogUtils.i(TAG, s);
                        Gson gson = new Gson();
                        CompanyData info;
                        try
                        {
                            info = gson.fromJson(s.toString(), CompanyData.class);
                            LoginManager.saveToken(info.getData().getTokenId());
                            if (listener != null)
                            {
                                listener.onLoginSuccess(info);
                            }
                        }
                        catch (Exception e)
                        {
                            LogUtils.i(TAG, "json exception :" + e.getMessage());
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError volleyError)
                    {
                        if (listener != null)
                        {
                            listener.onLoginError(volleyError.getMessage());
                        }
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                Map<String, String> map = new HashMap<>();
                map.put("phone", phoneNumber);
                map.put("code", code);
                map.put("action", Url.ACTION);
                return map;
            }

        };
        Http.addRequest(request, "Login");
    }

}
