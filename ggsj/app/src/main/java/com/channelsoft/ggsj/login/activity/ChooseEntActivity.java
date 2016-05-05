package com.channelsoft.ggsj.login.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.channelsoft.ggsj.R;
import com.channelsoft.ggsj.base.activity.BaseActivity;
import com.channelsoft.ggsj.databinding.ActivtyChooseEntBinding;
import com.channelsoft.ggsj.home.activity.MainActivity;
import com.channelsoft.ggsj.login.adapter.ChooseEntAdapter;
import com.channelsoft.ggsj.login.bean.CompanyData;
import com.channelsoft.ggsj.login.viewmodel.ILoginViewModel;
import com.channelsoft.ggsj.login.viewmodel.LoginViewModel;
import com.channelsoft.ggsj.utils.LogUtils;
import com.channelsoft.ggsj.utils.LoginManager;

/**
 * 选择商户
 * Created by dengquan on 16-4-5.
 */
public class ChooseEntActivity extends BaseActivity implements ChooseEntAdapter.OnItemClickListener,LoginViewModel.ChooseEntActivityView
{
    private static final String TAG = ChooseEntActivity.class.getSimpleName();
    public static final String ENT_INFO = "ENT_INFO";
    private ActivtyChooseEntBinding binding;
    private ChooseEntAdapter adapter;
    private CompanyData data;

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
        LogUtils.i(TAG,((CompanyData) getIntent().getParcelableExtra(ENT_INFO)).getData().getTokenId());
    }

    private  void initAdapter()
    {
        data = (CompanyData)getIntent().getParcelableExtra(ENT_INFO);
        adapter = new ChooseEntAdapter(this,data);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        binding.recycleView.setLayoutManager(manager);
        binding.recycleView.setHasFixedSize(true);
        binding.recycleView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }

    @Override
    public void onClick(int position)
    {
        LoginManager.saveEntId(data.getData().getCompanyInfo().get(position).getEntId());
        LoginManager.saveHelpDeskUrl(data.getData().getCompanyInfo().get(position).getHelpdeskUrl());
        ILoginViewModel model = new LoginViewModel(this);
        model.login(data.getData().getCompanyInfo().get(position).getEntId());
    }

    @Override
    public void onLogining()
    {

    }

    @Override
    public void onSuccess()
    {
        startActivity(new Intent(ChooseEntActivity.this, MainActivity.class));
    }

    @Override
    public void onError(String returnCode)
    {

    }
}
