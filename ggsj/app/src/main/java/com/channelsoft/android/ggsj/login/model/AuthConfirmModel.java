package com.channelsoft.android.ggsj.login.model;

import android.os.BaseBundle;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.channelsoft.android.ggsj.base.bean.BaseInfo;
import com.channelsoft.android.ggsj.http.Http;
import com.channelsoft.android.ggsj.http.Url;
import com.channelsoft.android.ggsj.login.bean.AuthConfirmBean;
import com.channelsoft.android.ggsj.login.listener.OnAuthConfirmListener;
import com.channelsoft.android.ggsj.utils.LogUtils;
import com.channelsoft.android.ggsj.utils.LoginManager;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dengquan on 16-5-18.
 */
public class AuthConfirmModel implements IAuthConfirmModel
{
    private static final String TAG = AuthConfirmModel.class.getSimpleName();
    private OnAuthConfirmListener listener;
    private StringRequest request;
    public AuthConfirmModel(OnAuthConfirmListener listener)
    {
        this.listener = listener;
    }
    @Override
    public void onAuthConfirm(final AuthConfirmBean info)
    {
        final String url = LoginManager.getHelpDeskUrl() + Url.Login.AUTH_CONFIRM + ";jsessionid="
                + LoginManager.getSessionId();
        request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String s)
            {
                LogUtils.i(TAG,"auth confirm :"+s);
                BaseInfo baseInfo ;
                Gson gson = new Gson();
                try
                {
                    baseInfo = gson.fromJson(s,BaseInfo.class);
                    if(Http.RETURNCODE_OK.equals(baseInfo.getReturnCode()))
                    {
                        if(listener != null)
                        {
                            listener.onAuthConfirmSuccess();
                        }
                    }
                    else if(Http.CHANGE_TIME_OUT.equals(baseInfo.getReturnCode()))
                    {
                        Http.changeTimeOut(url,null,request,null,null);
                    }
                    else
                    {
                        if(listener != null)
                        {
                            listener.onAuthCOnfirmError(baseInfo.getReturnCode());
                        }
                    }
                }
                catch (Exception e)
                {
                    if(listener != null)
                    {
                        listener.onAuthCOnfirmError("");
                    }
                }
            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError volleyError)
            {
                LogUtils.i(TAG,"on auth confirm error :"+volleyError.getMessage());
                if(listener != null)
                {
                    listener.onAuthCOnfirmError(volleyError.getLocalizedMessage());
                }
            }
        }
        )
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                Map<String,String> map = new HashMap<>();
                map.put("authId",info.getAuthId());
                map.put("regId",info.getRegId());
                map.put("deviceName",info.getDeviceName());
                map.put("returnCouponRight",info.getReturnCouponRight());
                map.put("verifyCouponRight",info.getVerifyCouponRight());
                map.put("orderRight",info.getOrderRight());
                return map;
            }
        };
        Http.addRequest(request,TAG);
    }
}
