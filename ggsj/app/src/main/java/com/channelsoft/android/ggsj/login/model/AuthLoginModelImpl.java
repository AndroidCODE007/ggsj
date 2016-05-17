package com.channelsoft.android.ggsj.login.model;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.channelsoft.android.ggsj.http.Http;
import com.channelsoft.android.ggsj.http.Url;
import com.channelsoft.android.ggsj.login.listener.AuthorizationLoginListener;
import com.channelsoft.android.ggsj.utils.LogUtils;
import com.channelsoft.android.ggsj.utils.LoginManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenyg on 2016/5/17.
 */
public class AuthLoginModelImpl implements IAuthLoginModel {

    private static final String TAG = AuthLoginModelImpl.class.getSimpleName();
    private AuthorizationLoginListener listener;

    public AuthLoginModelImpl(AuthorizationLoginListener listener) {
        this.listener = listener;
    }

    @Override
    public void authLogin(final String regId, final String authId) {
        StringRequest request = new StringRequest(
                StringRequest.Method.POST,
                LoginManager.getHelpDeskUrl() + Url.Login.GET_AUTH_DEVICE_INFO,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        LogUtils.i(TAG,s);
                        if(listener != null){
                            try {
                                JSONObject jsonObject = new JSONObject(s);
                                if(("00").equals(jsonObject.getString("returnCode"))){
                                    JSONObject object = jsonObject.getJSONObject("data");
                                    String deviceModel = object.getString("deviceModel");
                                    String deviceName = object.getString("deviceName");
                                    listener.AuthorizationLoginSuccess(deviceName,deviceModel);
                                }else {
                                    listener.AuthorizationLoginError();
                                }
                            } catch (JSONException e) {
                                LogUtils.i(TAG,"authLogin 解析异常");
                                listener.AuthorizationLoginError();
                                e.printStackTrace();
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        if(listener != null){
                            listener.AuthorizationLoginError();
                        }
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> map = new HashMap<>();
                map.put("regId",regId);
                map.put("authId",authId);

                return map;
            }
        };
        Http.addRequest(request,TAG);
    }
}
