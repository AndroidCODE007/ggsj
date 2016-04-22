package com.channelsoft.ggsj.order.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.channelsoft.ggsj.R;
import com.channelsoft.ggsj.base.fragment.BaseFragment;
import com.channelsoft.ggsj.databinding.FragmentOrderBinding;
import com.channelsoft.ggsj.order.adapter.OrderAdapter;
import com.channelsoft.ggsj.order.bean.OrderListInfo;
import com.channelsoft.ggsj.order.bean.OrderListResult;
import com.channelsoft.ggsj.order.viewmodel.GetOrderListViewModelImpl;
import com.channelsoft.ggsj.order.viewmodel.IGetOrderListViewModel;
import com.channelsoft.ggsj.utils.LogUtils;
import com.channelsoft.ggsj.view.LoadMoreRecycleView;
import com.channelsoft.ggsj.view.MeasureLayoutManager;

import java.util.ArrayList;
import java.util.List;

/**
 * 订单列表
 * Created by dengquan on 16-4-18.
 */
public class OrderFragment extends BaseFragment implements LoadMoreRecycleView.OnScrollChangedListener,MeasureLayoutManager.OnMeasureCompleteListener,GetOrderListViewModelImpl.OnGetOrderListView {
    private FragmentOrderBinding binding;
    private OrderAdapter adapter;
    public MeasureLayoutManager manager;

    private IGetOrderListViewModel getOrderListViewModel;

    private int page = 1;

    private List<OrderListInfo> orderList;

    private boolean isLoadMoreOrRefresh = true;//下拉刷新为true，加载更多为false

    private View rootView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_order,container,false);

        bindViewModelAndInitData();

        initView();

        return rootView = binding.getRoot();
    }

    private void bindViewModelAndInitData() {
        getOrderListViewModel = new GetOrderListViewModelImpl(this);
        getOrderListViewModel.getOrderList("0",page);
    }

    private void initView() {
        orderList = new ArrayList<OrderListInfo>();

        binding.swipeOrder.setColorSchemeColors(R.color.colorAccent,R.color.colorPrimary,R.color.colorPrimaryDark);
        binding.swipeOrder.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;

                binding.swipeOrder.setRefreshing(false);
                isLoadMoreOrRefresh = true;

                getOrderListViewModel.getOrderList("0",page);
            }
        });
        manager = new MeasureLayoutManager(getActivity());
        manager.setOnMeasureCompleteListener(this);
        adapter = new OrderAdapter(getActivity(),orderList);
        binding.recycleView.setHasFixedSize(true);
        binding.recycleView.setAdapter(adapter);
        binding.recycleView.setLayoutManager(manager);
        binding.recycleView.setOnScrollChangedListener(this);
    }
    @Override
    public void onLoading() {
        isLoadMoreOrRefresh = false;
        page += 1;

        getOrderListViewModel.getOrderList("0",page);
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onMeasureComplete()
    {
        binding.recycleView.setIsFullScreen(manager);
    }

    @Override
    public void onGetting() {

    }

    @Override
    public void onGetSuccess(OrderListResult result) {
        binding.recycleView.setIsLoading();
        if(isLoadMoreOrRefresh){
            orderList.clear();
            orderList = result.getOrderList();
            adapter.addHeaderData(orderList);
        }else {
            orderList.addAll(result.getOrderList());
            adapter.addFooterData(orderList);
        }
    }

    @Override
    public void onGetError(String errorMsg) {
        Toast.makeText(getActivity(),errorMsg,Toast.LENGTH_SHORT).show();
    }
}
