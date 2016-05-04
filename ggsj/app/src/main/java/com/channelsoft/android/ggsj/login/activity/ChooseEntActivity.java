package com.channelsoft.android.ggsj.login.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.LinearLayout;

import com.channelsoft.android.ggsj.R;
import com.channelsoft.android.ggsj.base.activity.BaseActivity;
import com.channelsoft.android.ggsj.databinding.ActivtyChooseEntBinding;
import com.channelsoft.android.ggsj.home.activity.MainActivity;
import com.channelsoft.android.ggsj.login.adapter.ChooseEntAdapter;
import com.channelsoft.android.ggsj.login.bean.CompanyData;
import com.channelsoft.android.ggsj.utils.LogUtils;


/**
 * 选择商户
 * Created by dengquan on 16-4-5.
 */
public class ChooseEntActivity extends BaseActivity implements ChooseEntAdapter.OnItemClickListener
{
    private static final String TAG = ChooseEntActivity.class.getSimpleName();
    public static final String ENT_INFO = "ENT_INFO";
    private ActivtyChooseEntBinding binding;
    private ChooseEntAdapter adapter;

    public static Intent newIntent(Context context,CompanyData info)
    {
        Intent intent = new Intent(context,ChooseEntActivity.class);
        intent.putExtra(ENT_INFO, info);
        return intent;
    }
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activty_choose_ent);
        initAdapter();
        LogUtils.i(TAG, ((CompanyData) getIntent().getParcelableExtra(ENT_INFO)).getData().getTokenId());
    }

    private  void initAdapter()
    {
        adapter = new ChooseEntAdapter(this,(CompanyData)getIntent().getParcelableExtra(ENT_INFO));
        LinearLayoutManager manager = new LinearLayoutManager(this);
        binding.recycleView.setLayoutManager(manager);
        binding.recycleView.setHasFixedSize(true);
        binding.recycleView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }

    @Override
    public void onClick()
    {
        startActivity(new Intent(ChooseEntActivity.this, MainActivity.class));
    }
}
