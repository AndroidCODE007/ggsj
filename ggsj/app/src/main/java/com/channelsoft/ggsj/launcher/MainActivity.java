package com.channelsoft.ggsj.launcher;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.channelsoft.ggsj.R;
import com.channelsoft.ggsj.databinding.ActivityMainBinding;
import com.channelsoft.ggsj.login.activity.BossLoginActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        binding.textView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {

        switch (v.getId())
        {
            case R.id.textView:
                startActivity(new Intent(MainActivity.this, BossLoginActivity.class));
                break;
        }
    }
}
