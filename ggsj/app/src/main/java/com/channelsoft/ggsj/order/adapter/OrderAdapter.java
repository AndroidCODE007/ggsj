package com.channelsoft.ggsj.order.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.channelsoft.ggsj.R;
import com.channelsoft.ggsj.order.bean.OrderListInfo;
import com.channelsoft.ggsj.view.MeasureLayoutManager;

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
            OrderViewHolder holder1 = (OrderViewHolder)holder;
            holder1.textView.setText(list.get(position).getPayPrice()+"元");
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
            return new OrderViewHolder(LayoutInflater.from(context).inflate(R.layout.item_order,parent,false));
        }
        else
        {
            return new FooterViewHolder(LayoutInflater.from(context).inflate(R.layout.footer_view,parent,false));
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
        list = orderList;
        this.notifyDataSetChanged();
    }

    class OrderViewHolder extends RecyclerView.ViewHolder
    {
        private TextView textView;
        public OrderViewHolder(View itemView)
        {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.orderName);
        }
    }

    class FooterViewHolder extends RecyclerView.ViewHolder
    {

        public FooterViewHolder(View itemView)
        {
            super(itemView);
        }
    }
}
