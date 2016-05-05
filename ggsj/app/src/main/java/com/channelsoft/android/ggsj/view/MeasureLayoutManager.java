package com.channelsoft.android.ggsj.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.channelsoft.android.ggsj.utils.LogUtils;


/**
 * 测量高度的LayoutManager
 * Created by dengquan on 16-4-19.
 */
public class MeasureLayoutManager extends LinearLayoutManager
{
    private static final String TAG = MeasureLayoutManager.class.getSimpleName();
    private int sumHeight = 0;
    private OnMeasureCompleteListener listener;

    public MeasureLayoutManager(Context context)
    {
        super(context);
        LogUtils.i("LoadMoreRecycleView", "init MeasureLayoutManager   ");
    }

    @Override
    public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int widthSpec, int heightSpec)
    {
        super.onMeasure(recycler, state, widthSpec, heightSpec);
        LogUtils.i("LoadMoreRecycleView", "onMeasure   xcc:" );

        for (int i = 0; i < this.getChildCount(); i++)
        {
            View view = this.getChildAt(i);
            measureChild(view, widthSpec, heightSpec);
            int width = View.MeasureSpec.getSize(widthSpec);
            int height = view.getMeasuredHeight();
            setMeasuredDimension(width, height);
            sumHeight += this.getChildAt(i).getMeasuredHeight();
            LogUtils.i("LoadMoreRecycleView","item height :"+this.getChildAt(i).getMeasuredHeight());
        }
        if(listener != null)
        {
            listener.onMeasureComplete();
        }
        LogUtils.i("LoadMoreRecycleView", "sumHeight :" + sumHeight + "   childCount :" + this.getChildCount());
    }

    public int getSumHeight()
    {
        LogUtils.i("LoadMoreRecycleView","return  sum height :"+sumHeight);
        return sumHeight;
    }

    public void setOnMeasureCompleteListener(OnMeasureCompleteListener listener )
    {
        this.listener = listener;
    }

    public interface OnMeasureCompleteListener
    {
        void onMeasureComplete();
    }
}
