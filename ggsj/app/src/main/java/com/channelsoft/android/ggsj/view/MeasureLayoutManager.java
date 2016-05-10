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

    /**
     * 注解：getChildCount的值是0   ；     getItemCount得到的值是4
     * @param recycler
     * @param state
     * @param widthSpec
     * @param heightSpec
     */
    @Override
    public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int widthSpec, int heightSpec)
    {
        super.onMeasure(recycler, state, widthSpec, heightSpec);
        LogUtils.i("LoadMoreRecycleView", "onMeasure   xcc:"+this.getChildCount()+"  "+this.getItemCount());


        LogUtils.i("LoadMoreRecycleView", "sumHeight :" + sumHeight);
        if(listener != null)
        {
            listener.onMeasureComplete();
        }
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state)
    {
        super.onLayoutChildren(recycler, state);
        for (int i = 0; i < this.getItemCount(); i++)
        {
            View view = recycler.getViewForPosition(i);
            sumHeight += recycler.getViewForPosition(i).getMeasuredHeight();
            LogUtils.i("LoadMoreRecycleView","item height :"+recycler.getViewForPosition(i).getHeight());
        }
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
