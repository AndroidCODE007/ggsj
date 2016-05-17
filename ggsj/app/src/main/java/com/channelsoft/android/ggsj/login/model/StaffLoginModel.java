package com.channelsoft.android.ggsj.login.model;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.channelsoft.android.ggsj.http.Http;
import com.channelsoft.android.ggsj.http.Url;
import com.channelsoft.android.ggsj.push.bean.AuthMiMsg;
import com.channelsoft.android.ggsj.utils.LogUtils;
import com.channelsoft.android.ggsj.utils.LoginManager;

import java.util.HashMap;
import java.util.Map;

/**
 * 员工登陆
 * Created by dengquan on 16-5-11.
 */
public class StaffLoginModel implements IStaffLoginModel
{
    private static final String TAG = StaffLoginModel.class.getSimpleName();
    private OnStaffLoginListener listener;
    public StaffLoginModel(OnStaffLoginListener listener)
    {
        this.listener = listener;
    }
    @Override
    public void onStaffLogin(final AuthMiMsg msg)
    {
        String url = msg.getAppNodeUrl() + Url.Login.QR_LOGIN_URL;
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String s)
            {
                LogUtils.i(TAG,"on staff login success :"+s);
                if(listener != null)
                {
                    listener.onStaffLoginSuccess();
                }
            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError volleyError)
            {
                LogUtils.i(TAG,"on staff login error :"+volleyError.toString());
                if(listener != null)
                {
                    listener.onStaffLoginError(volleyError.toString());
                }
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                HashMap<String,String> map = new HashMap<>();
                map.put("deviceId", LoginManager.getDeviceId());
                map.put("product", "ggsj");
                map.put("entId", msg.getEntId());//企业id
                return map;
            }
        };
        Http.addRequest(request,TAG);
    }

    public interface OnStaffLoginListener
    {
        void onStaffLoginSuccess();

        void onStaffLoginError(String returnCode);
    }
}
