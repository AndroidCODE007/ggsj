package com.channelsoft.android.ggsj.order.model;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import com.channelsoft.android.ggsj.http.Http;
import com.channelsoft.android.ggsj.http.Url;
import com.channelsoft.android.ggsj.order.bean.OrderListResult;
import com.channelsoft.android.ggsj.order.listener.OnGetOrderListListener;
import com.channelsoft.android.ggsj.utils.LogUtils;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenyg on 2016/4/21.
 */
public class GetOrderListModelImpl implements IGetOrderListModel{

    private static final String TAG = GetOrderListModelImpl.class.getSimpleName();
    private OnGetOrderListListener listener;
    private OrderListResult result;

    public GetOrderListModelImpl(OnGetOrderListListener listener) {
        this.listener = listener;
    }

    @Override
    public void getOrderList(final String groupStatus, final int page) {
        StringRequest request = new StringRequest(
                Request.Method.POST,
                Url.HOST + Url.Order.GET_ORDERLIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        LogUtils.i(TAG, s);
                        Gson gson = new Gson();
                        try {
                            result = gson.fromJson(s.toString(),OrderListResult.class);
                            if(listener != null){
                                listener.onGetOrderListSuccess(result);
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
                            listener.onGetOrderListError(volleyError.getMessage());
                        }
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<String,String>();
                map.put("groupStatus",groupStatus);
                map.put("page",String.valueOf(page));
                return map;
            }
        };
        Http.addRequest(request, TAG);
    }
}
