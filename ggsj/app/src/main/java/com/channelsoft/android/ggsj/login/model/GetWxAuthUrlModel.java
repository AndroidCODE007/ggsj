package com.channelsoft.android.ggsj.login.model;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.channelsoft.android.ggsj.http.Http;
import com.channelsoft.android.ggsj.http.Url;
import com.channelsoft.android.ggsj.login.bean.GetWxUrlInfo;
import com.channelsoft.android.ggsj.utils.LoginManager;
import com.channelsoft.android.ggsj.utils.VersionCodeUtil;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Handler;

/**
 * 获取微信授权Url
 * Created by dengquan on 16-5-10.
 */
public class GetWxAuthUrlModel implements IGetWXAuthUrlModel
{
    private static final String TAG = GetWxAuthUrlModel.class.getSimpleName();

    public GetWxAuthUrlModel()
    {

    }

    public void getWxAuthUrl()
    {
        String url = Url.Login.GET_WX_AUTH_URL;
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String s)
            {
                Gson gson = new Gson();
                GetWxUrlInfo info = gson.fromJson(s.toString(),GetWxUrlInfo.class);
                if(Http.RETURNCODE_OK.equals(info.getReturnCode()))
                {
                    makeBitmap(info.getQrUrl());
                }
            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError volleyError)
            {

            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                Map<String,String> map = new HashMap<>();
                map.put("deviceId", LoginManager.getDeviceId());
                map.put("deviceModel", android.os.Build.MODEL);//设备型号
                map.put("deviceOsVersion", android.os.Build.VERSION.RELEASE);//系统版本
                map.put("product", "ggsj");
                map.put("regId", regId);
                map.put("appVersion", VersionCodeUtil.getCurrentName());//应用版本号
                map.put("deviceOsType", "1");//系统类型 1android 2ios
                return super.getParams();
            }
        };
        Http.addRequest(request,TAG);
    }

    private void makeBitmap(String url)
    {
        Handler handler;
        handler
    }

}
