package com.channelsoft.ggsj.order.listener;

import com.channelsoft.ggsj.order.bean.OrderListResult;

/**
 * Created by chenyg on 2016/4/21.
 */
public interface OnGetOrderListListener
{
    void onGetOrderListSuccess(OrderListResult result);
    void onGetOrderListError(String errorMsg);
}
