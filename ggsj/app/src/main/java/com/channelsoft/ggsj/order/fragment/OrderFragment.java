package com.channelsoft.ggsj.order.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.channelsoft.ggsj.R;
import com.channelsoft.ggsj.base.fragment.BaseFragment;
import com.channelsoft.ggsj.databinding.FragmentOrderBinding;
import com.channelsoft.ggsj.order.adapter.OrderAdapter;
import com.channelsoft.ggsj.view.LoadMoreRecycleView;
import com.channelsoft.ggsj.view.MeasureLayoutManager;

/**
 * 订单列表
 * Created by dengquan on 16-4-18.
 */
public class OrderFragment extends BaseFragment implements LoadMoreRecycleView.OnScrollChangedListener,MeasureLayoutManager.OnMeasureCompleteListener
{
    private FragmentOrderBinding binding;
    private OrderAdapter adapter;
    public MeasureLayoutManager manager;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_order,container,false);
        binding.swipeOrder.setColorSchemeColors(R.color.colorAccent,R.color.colorPrimary,R.color.colorPrimaryDark);
        binding.swipeOrder.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {
                adapter.addHeaderData();
                binding.swipeOrder.setRefreshing(false);
            }
        });
        manager = new MeasureLayoutManager(getActivity());
        manager.setOnMeasureCompleteListener(this);
        adapter = new OrderAdapter(getActivity());
        binding.recycleView.setHasFixedSize(true);
        binding.recycleView.setAdapter(adapter);
        binding.recycleView.setLayoutManager(manager);
        binding.recycleView.setOnScrollChangedListener(this);
        return binding.getRoot();
    }

    @Override
    public void onLoading()
    {
        adapter.addFooterData();
    }

    @Override
    public void onComplete()
    {

    }

    @Override
    public void onMeasureComplete()
    {
        binding.recycleView.setIsFullScreen(manager);
    }
}
