package com.channelsoft.android.ggsj.login.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.channelsoft.android.ggsj.BR;
import com.channelsoft.android.ggsj.R;
import com.channelsoft.android.ggsj.databinding.ItemChooseEntBinding;
import com.channelsoft.android.ggsj.login.bean.CompanyData;
import com.channelsoft.android.ggsj.login.bean.CompanyDetailInfo;
import com.channelsoft.android.ggsj.utils.LogUtils;


/**
 * 选择店铺
 * Created by dengquan on 16-4-5.
 */
public class ChooseEntAdapter extends RecyclerView.Adapter
{
    private static final String TAG = ChooseEntAdapter.class.getSimpleName();
    private Context context;
    private CompanyData data;
    private LayoutInflater inflate;
    private OnItemClickListener listener;
    public ChooseEntAdapter(Context context, CompanyData data)
    {
        this.context = context;
        this.data = data;
        inflate = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        ItemChooseEntBinding binding = DataBindingUtil.inflate(inflate,
                R.layout.item_choose_ent, parent, false);
        EntViewHolder holder = new EntViewHolder(binding.getRoot());
        holder.setBinding(binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {
        CompanyDetailInfo info = data.getData().getCompanyInfo().get(position);
        ((EntViewHolder) holder).getBinding().setVariable(BR.entInfo, info);
        ((EntViewHolder) holder).getBinding().executePendingBindings();
        ((EntViewHolder) holder).getBinding().tvEntName.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(listener != null)
                {
                    listener.onClick();
                }
            }
        });
        LogUtils.i(TAG, "short name  :" + data.getData().getCompanyInfo().get(position).getShortName());
    }

    @Override
    public int getItemCount()
    {
        return data.getData().getCompanyInfo().size();
    }

    class EntViewHolder extends RecyclerView.ViewHolder
    {
        private ItemChooseEntBinding binding;

        public EntViewHolder(View itemView)
        {
            super(itemView);
        }

        private ItemChooseEntBinding getBinding()
        {
            return binding;
        }

        private void setBinding(ItemChooseEntBinding binding)
        {
            this.binding = binding;
        }


    }

    public void setOnItemClickListener(OnItemClickListener listener)
    {
        this.listener = listener;
    }

    public interface OnItemClickListener
    {
        void onClick();
    }
}
