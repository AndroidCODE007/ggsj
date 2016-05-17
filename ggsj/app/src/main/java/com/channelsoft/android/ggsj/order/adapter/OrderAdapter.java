package com.channelsoft.android.ggsj.order.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.channelsoft.android.ggsj.BR;
import com.channelsoft.android.ggsj.R;
import com.channelsoft.android.ggsj.databinding.ItemOrderBinding;
import com.channelsoft.android.ggsj.order.bean.OrderListInfo;
import com.channelsoft.android.ggsj.view.LoadFooterView;
import com.channelsoft.android.ggsj.view.loading.SpinKitView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dengquan on 16-4-18.
 */
public class OrderAdapter extends RecyclerView.Adapter
{
    private static final int ORDER_DETAIL = 1;
    private static final int LOAD_MORE = 2;
    private Context context;
    private List<OrderListInfo> list = new ArrayList<>();
    private ItemOrderBinding binding;
    private OrderViewHolder holder;
    private OrderListInfo info;
    private LoadFooterView footerView;
    public OrderAdapter(Context context,List<OrderListInfo> list)
    {
        super();
        this.context = context;
        this.list = list;
    }

    @Override
    public int getItemCount()
    {
        return list.size() + 1;
    }

    @Override
    public int getItemViewType(int position)
    {
        if(getItemCount() == position + 1)
        {
            return LOAD_MORE;
        }
        else
        {
            return ORDER_DETAIL;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {
        if(holder instanceof OrderViewHolder)
        {
            info = list.get(position);
            ((OrderViewHolder) holder).getBinding().setVariable(BR.dishInfo,info);
            ((OrderViewHolder) holder).getBinding().executePendingBindings();
        }
        else if(holder instanceof FooterViewHolder)
        {
            FooterViewHolder holder1 = (FooterViewHolder) holder;
//            holder1.textView.setText(context.getResources().getString(R.string.load_more));
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        if(viewType == ORDER_DETAIL)
        {
            binding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.getContext()),R.layout.item_order,parent,false);
            holder = new OrderViewHolder(binding.getRoot());
            holder.setBinding(binding);
            return holder;
        }
        else
        {
            footerView =
                    (LoadFooterView) (LayoutInflater.from(context).inflate(R.layout.footer_view,parent,false));
            return new FooterViewHolder(footerView);
        }
    }

    public void addHeaderData(List<OrderListInfo> orderList)
    {
        Toast.makeText(context,"header data  ",Toast.LENGTH_LONG).show();
        list = orderList;
        this.notifyDataSetChanged();
    }

    public void addFooterData(List<OrderListInfo> orderList)
    {
        Toast.makeText(context,"footer data  ",Toast.LENGTH_LONG).show();
        checkFooterViewStatus(orderList);
        list.addAll(orderList);
        this.notifyDataSetChanged();
    }

    public LoadFooterView getFooterView()
    {
        return getFooterView();
    }

    class OrderViewHolder extends RecyclerView.ViewHolder
    {
        private ItemOrderBinding binding;
        public OrderViewHolder(View itemView)
        {
            super(itemView);
        }

        public ItemOrderBinding getBinding()
        {
            return binding;
        }

        public void setBinding(ItemOrderBinding binding)
        {
            this.binding = binding;
        }
    }

    class FooterViewHolder extends RecyclerView.ViewHolder
    {
        public FooterViewHolder(View itemView)
        {
            super(itemView);
        }
    }

    /**
     * 设置不同状态下面的footerView的状态
     * @param listInfos
     */
    private void checkFooterViewStatus(List<OrderListInfo> listInfos )
    {
        if(listInfos == null || listInfos.size() <= 0)
        {
            footerView.setViewStatus(LoadFooterView.StatusFooterView.nomore);
        }
        else
        {

        }
    }
}
