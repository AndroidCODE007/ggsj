package com.channelsoft.android.ggsj.order.bean;


import com.channelsoft.android.ggsj.base.bean.BaseInfo;

import java.util.List;

/**
 * Created by chenyg on 2016/4/21.
 */
public class OrderListResult extends BaseInfo {
    private List<BlockInfo> block;
    private List<OrderListInfo> orderList;

    public List<BlockInfo> getBlock()
    {
        return block;
    }

    public void setBlock(List<BlockInfo> block)
    {
        this.block = block;
    }

    public List<OrderListInfo> getOrderList()
    {
        return orderList;
    }

    public void setOrderList(List<OrderListInfo> orderList)
    {
        this.orderList = orderList;
    }
}
