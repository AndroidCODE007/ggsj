package com.channelsoft.android.ggsj.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.channelsoft.android.ggsj.R;

/**
 * 底部的加载更多的布局，包含各种状态下的操作
 * Created by dengquan on 16-5-6.
 */
public class LoadFooterView extends FrameLayout implements View.OnClickListener
{
    private TextView noMoreView;   //没有更多数据
    private TextView netExceptionView;   //网络异常的View。显示：（tip）：点击加载更多
    private View loadingView;
    private onFooterViewClickListener listener;
    public LoadFooterView(Context context)
    {
        this(context,null);
    }

    public LoadFooterView(Context context, AttributeSet attrs)
    {
        this(context, attrs,0);
    }

    public LoadFooterView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init(context);
        initParams(context);

    }

    private void init(Context context)
    {
        netExceptionView = new TextView(context);
        netExceptionView.setOnClickListener(this);
        netExceptionView.setId(R.id.netExceptionId);
        this.addView(netExceptionView);

        noMoreView = new TextView(context);
        noMoreView.setOnClickListener(this);
        noMoreView.setId(R.id.noMoreId);
        this.addView(noMoreView);

        loadingView = LayoutInflater.from(context).inflate(R.layout.footer_load_view,this,false);
        loadingView.setId(R.id.loadingId);
        this.addView(loadingView);

    }

    private void initParams(Context context)
    {
        FrameLayout.LayoutParams netExceptionParams = getParams(netExceptionView);
        netExceptionParams.gravity = Gravity.CENTER;
        netExceptionView.setLayoutParams(netExceptionParams);

        FrameLayout.LayoutParams noMoreLayoutParams = getParams(noMoreView);
        noMoreLayoutParams.gravity = Gravity.CENTER;
        noMoreView.setLayoutParams(noMoreLayoutParams);

        FrameLayout.LayoutParams loadingParams = getParams(loadingView);
        loadingParams.gravity = Gravity.CENTER;
        loadingView.setLayoutParams(loadingParams);
    }

    private FrameLayout.LayoutParams getParams(View view)
    {
        FrameLayout.LayoutParams params =
                (FrameLayout.LayoutParams)view.getLayoutParams();
        if(params == null)
        {
            params =
                    new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
        }
        params.width = LayoutParams.MATCH_PARENT;
        params.height = LayoutParams.WRAP_CONTENT;
        return params;
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.netExceptionId:
                if(listener != null)
                {
                    listener.onFooterLoadMore();
                }
                break;
            case R.id.noMoreId:
                if(listener != null)
                {
                    listener.onFooterLoadMore();
                }
                break;
        }
    }

    public void setViewStatus(StatusFooterView status)
    {
        switch (status)
        {
            case loading:
                showView(R.id.loadingId);
                break;
            case netexception:
                showView(R.id.netExceptionId);
                break;
            case nomore:
                showView(R.id.noMoreId);
                break;
        }
    }

    private void showView(int id)
    {
        hideAll();
        switch (id)
        {
            case R.id.netExceptionId:
                netExceptionView.setVisibility(View.VISIBLE);
                break;
            case R.id.loadingId:
                loadingView.setVisibility(View.VISIBLE);
                break;
            case R.id.noMoreId:
                noMoreView.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void hideAll()
    {
        for(int i = 0;i<this.getChildCount();i++)
        {
            this.getChildAt(i).setVisibility(View.GONE);
        }
    }

    public void setOnFooterViewClickListener(onFooterViewClickListener listener)
    {
        this.listener = listener;
    }

    public interface onFooterViewClickListener
    {
        void onFooterLoadMore();
    }


    public enum  StatusFooterView
    {
        loading,nomore,netexception;
    }
}
