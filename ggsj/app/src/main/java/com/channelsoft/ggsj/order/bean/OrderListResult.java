package com.channelsoft.ggsj.order.bean;

import com.channelsoft.ggsj.base.bean.BaseInfo;

import java.util.List;

/**
 * Created by chenyg on 2016/4/21.
 */
public class OrderListResult extends BaseInfo
{
    private List<BlockInfo> block;
    private List<OrderListInfo> orderList;
    private int page;
    private int totalPage;

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

    public int getPage()
    {
        return page;
    }

    public void setPage(int page)
    {
        this.page = page;
    }

    public int getTotalPage()
    {
        return totalPage;
    }

    public void setTotalPage(int totalPage)
    {
        this.totalPage = totalPage;
    }
}
