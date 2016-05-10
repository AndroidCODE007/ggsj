package com.channelsoft.android.ggsj.order.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.channelsoft.android.ggsj.R;
import com.channelsoft.android.ggsj.base.fragment.BaseFragment;
import com.channelsoft.android.ggsj.databinding.FragmentOrderBinding;
import com.channelsoft.android.ggsj.order.adapter.OrderAdapter;
import com.channelsoft.android.ggsj.order.bean.OrderListInfo;
import com.channelsoft.android.ggsj.order.bean.OrderListResult;
import com.channelsoft.android.ggsj.order.viewmodel.GetOrderListViewModelImpl;
import com.channelsoft.android.ggsj.order.viewmodel.IGetOrderListViewModel;
import com.channelsoft.android.ggsj.view.DynamicViewGroup;
import com.channelsoft.android.ggsj.view.LoadFooterView;
import com.channelsoft.android.ggsj.view.LoadMoreRecycleView;
import com.channelsoft.android.ggsj.view.MeasureLayoutManager;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 订单列表
 * Created by dengquan on 16-4-18.
 */
public class OrderFragment extends BaseFragment implements
        LoadMoreRecycleView.OnScrollChangedListener,
        MeasureLayoutManager.OnMeasureCompleteListener,
        GetOrderListViewModelImpl.OnGetOrderListView, DynamicViewGroup.OnBtnClickListener,
        LoadFooterView.onFooterViewClickListener
{
    private LoadMoreRecycleView recyclerView;
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
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_order, container, false);
        initView();
        bindViewModelAndInitData();
        binding.dynamicView.setOnBtnClickListener(this);
        return rootView = binding.getRoot();
    }

    private void bindViewModelAndInitData()
    {
        getOrderListViewModel = new GetOrderListViewModelImpl(this);
        getOrderListViewModel.getOrderList("0", page);
    }

    private void initView()
    {
        binding.dynamicView.setDataView(LayoutInflater.from(getActivity()).inflate(R.layout.layout_order_data, binding.dynamicView,false));
        binding.dynamicView.showLoadingLayout();
        orderList = new ArrayList<>();

        binding.swipeOrder.setColorSchemeColors(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark);
        binding.swipeOrder.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {
                binding.swipeOrder.setRefreshing(false);
                isLoadMoreOrRefresh = true;
                getOrderListViewModel.getOrderList("0", page);
            }
        });
    }

    @Override
    public void onLoading()
    {
        isLoadMoreOrRefresh = false;
        page += 1;
        getOrderListViewModel.getOrderList("0", page);
    }

    @Override
    public void onComplete()
    {

    }

    @Override
    public void onMeasureComplete()
    {
        recyclerView.setIsFullScreen(manager);
    }

    @Override
    public void onGetting()
    {

    }

    @Override
    public void onGetSuccess(OrderListResult result)
    {
        initAdapter();
        page =  result.getPage();
        if (isLoadMoreOrRefresh)
        {
            orderList.clear();
            orderList = result.getOrderList();
            adapter.addHeaderData(orderList);
        }
        else
        {
            orderList.addAll(result.getOrderList());
            adapter.addFooterData(orderList);
        }
    }

    private void initAdapter()
    {
        recyclerView = ((LoadMoreRecycleView) binding.dynamicView.getDataView());
        binding.dynamicView.showCustomView();
        adapter = new OrderAdapter(getActivity(), orderList);
        manager = new MeasureLayoutManager(getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);
        //使RecyclerView保持固定的大小,这样会提高RecyclerView的性能。
        recyclerView.setHasFixedSize(true);
        manager.setOnMeasureCompleteListener(this);


        recyclerView.setHasFixedSize(true);

        recyclerView.setOnScrollChangedListener(this);
        recyclerView.setIsLoading();

    }

    @Override
    public void onGetError(String errorMsg)
    {
        Toast.makeText(getActivity(), errorMsg, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onCheckNetWork()
    {

    }

    @Override
    public void onEmptyRefresh()
    {

    }

    @Override
    public void onRefresh()
    {

    }

    /**
     * 底部加载更多的布局的按钮事件，不是自动触发而是手动触发，比如网路异常的点击事件，或者没有更多数据的点击事件
     */
    @Override
    public void onFooterLoadMore()
    {
        adapter.getFooterView().setOnFooterViewClickListener(this);
    }
}
