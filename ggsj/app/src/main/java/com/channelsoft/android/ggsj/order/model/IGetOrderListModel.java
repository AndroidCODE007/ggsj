package com.channelsoft.android.ggsj.order.model;

/**
 * Created by chenyg on 2016/4/21.
 */
public interface IGetOrderListModel {
    //groupStatus 查询状态(0-已到店,1-待确认,2-已确认，3-今日完成)
    //page 页码
    void getOrderList(String groupStatus, int page);
}
