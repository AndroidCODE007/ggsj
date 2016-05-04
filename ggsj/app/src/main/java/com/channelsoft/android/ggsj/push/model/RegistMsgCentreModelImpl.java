package com.channelsoft.android.ggsj.push.model;


import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import com.channelsoft.android.ggsj.base.bean.BaseInfo;
import com.channelsoft.android.ggsj.http.Http;
import com.channelsoft.android.ggsj.http.Url;
import com.channelsoft.android.ggsj.push.listener.OnRegistMsgCentreListener;
import com.channelsoft.android.ggsj.utils.LogUtils;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenyg on 2016/5/3.
 */
public class RegistMsgCentreModelImpl implements IRegistMsgCentreModel {

    private static final String TAG = RegistMsgCentreModelImpl.class.getSimpleName();
    private OnRegistMsgCentreListener listener;

    public RegistMsgCentreModelImpl(OnRegistMsgCentreListener listener) {
        this.listener = listener;
        LogUtils.e(TAG,"RegistMsgCentreModelImpl调用");
    }

    @Override
    public void registMsgCentre(final String regId, final String deviceOsVersion, final String deviceModel, final String appVersion, final String phoneNumber) {
        StringRequest request = new StringRequest(
                StringRequest.Method.POST,
                Url.RegistMsgCentre.REGIST_MSG_CENTRE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        LogUtils.i(TAG, s.toString());
                        try {
                            Gson gson = new Gson();
                            BaseInfo info = gson.fromJson(s,BaseInfo.class);
                            if(listener != null){
                                if(info.getReturnCode().equals("0")){
                                    listener.onRegistSuccess();
                                }else {
                                    listener.onRegistError();
                                }
                            }
                        }catch (Exception e){
                            LogUtils.e(TAG,"Json Exception = "+e.getMessage());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        if(listener != null){
                            listener.onRegistError();
                        }
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();

                map.put("regId", regId);
                map.put("deviceOsVersion", deviceOsVersion);
                map.put("deviceModel", deviceModel);
                map.put("appVersion", appVersion);
                map.put("phoneNumber", phoneNumber);

                return map;
            }
        };
        Http.addRequest(request, TAG);
    }
}
