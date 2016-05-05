package com.channelsoft.android.ggsj.order.listener;


import com.channelsoft.android.ggsj.order.bean.OrderListResult;

/**
 * Created by chenyg on 2016/4/21.
 */
public interface OnGetOrderListListener
{
    void onGetOrderListSuccess(OrderListResult result);
    void onGetOrderListError(String errorMsg);
}
