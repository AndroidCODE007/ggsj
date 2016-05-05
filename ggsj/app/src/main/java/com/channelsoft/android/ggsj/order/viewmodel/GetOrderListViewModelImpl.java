package com.channelsoft.android.ggsj.order.viewmodel;


import com.channelsoft.android.ggsj.order.bean.OrderListResult;
import com.channelsoft.android.ggsj.order.listener.OnGetOrderListListener;
import com.channelsoft.android.ggsj.order.model.GetOrderListModelImpl;
import com.channelsoft.android.ggsj.order.model.IGetOrderListModel;

/**
 * Created by chenyg on 2016/4/21.
 */
public class GetOrderListViewModelImpl implements IGetOrderListViewModel,OnGetOrderListListener {

    private OnGetOrderListView listener;

    public GetOrderListViewModelImpl(OnGetOrderListView listener) {
        this.listener = listener;
    }

    private IGetOrderListModel getOrderListModel;

    @Override
    public void getOrderList(String groupStatus, int page){
        getOrderListModel = new GetOrderListModelImpl(this);
        getOrderListModel.getOrderList(groupStatus,page);
        if(listener != null){
            listener.onGetting();
        }
    }

    @Override
    public void onGetOrderListSuccess(OrderListResult result) {
        if(listener != null){
            listener.onGetSuccess(result);
        }
    }

    @Override
    public void onGetOrderListError(String errorMsg) {
        if(listener != null){
            listener.onGetError(errorMsg);
        }
    }

    public interface OnGetOrderListView{
        void onGetting();
        void onGetSuccess(OrderListResult result);
        void onGetError(String errorMsg);
    }
}

