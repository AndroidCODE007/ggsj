package com.channelsoft.ggsj.order.model;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.channelsoft.ggsj.http.Http;
import com.channelsoft.ggsj.http.Url;
import com.channelsoft.ggsj.order.bean.OrderListResult;
import com.channelsoft.ggsj.order.listener.OnGetOrderListListener;
import com.channelsoft.ggsj.utils.LogUtils;
import com.channelsoft.ggsj.utils.LoginManager;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenyg on 2016/4/21.
 */
public class GetOrderListModelImpl implements IGetOrderListModel
{
    private static final String TAG = GetOrderListModelImpl.class.getSimpleName();
    private OnGetOrderListListener listener;
    private OrderListResult result;
    Map<String, String> map;
    private StringRequest request;
    private Response.Listener rightlistener;
    private Response.ErrorListener errorListener;

    public GetOrderListModelImpl(OnGetOrderListListener listener)
    {
        this.listener = listener;
    }

    @Override
    public void getOrderList(final String groupStatus, final int page)
    {
        request = new StringRequest(
                Request.Method.POST,
                LoginManager.getHelpDesjUrl() + Url.Order.GET_ORDERLIST+";jsessionid="+ LoginManager.getSessionId(),
                rightlistener = new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String s)
                    {
                        LogUtils.i(TAG, s);
                        Gson gson = new Gson();
                        try
                        {
                            result = gson.fromJson(s.toString(), OrderListResult.class);

                            if (Http.CHANGE_TIME_OUT.equals(result.getReturnCode()))
                            {
                                reLogin();

                            } else if (Http.RETURNCODE_OK.equals(result.getReturnCode()))
                            {
                                if (listener != null)
                                {
                                    listener.onGetOrderListSuccess(result);
                                }
                            }

                        } catch (Exception e)
                        {
                            LogUtils.e(TAG, "Json Exception = " + e.getMessage());
                        }
                    }
                },
                errorListener = new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError volleyError)
                    {
                        LogUtils.i(TAG, "get order error " + volleyError.toString());
                        if (listener != null)
                        {
                            listener.onGetOrderListError(volleyError.getMessage());
                        }
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                map = new HashMap<>();
                //(0-已到店,1-待确认,2-已确认，3-今日完成)
                map.put("groupStatus", groupStatus);
                map.put("page", String.valueOf(page));
                return map;
            }
        };
        Http.addRequest(request, TAG);
    }

    private void reLogin()
    {
        Http.changeTimeOut(Url.HOST + Url.Order.GET_ORDERLIST, map, request,rightlistener,errorListener);
    }
}
