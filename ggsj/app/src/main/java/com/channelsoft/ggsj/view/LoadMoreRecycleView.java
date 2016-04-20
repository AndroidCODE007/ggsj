package com.channelsoft.ggsj.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.channelsoft.ggsj.utils.LogUtils;
import com.channelsoft.ggsj.utils.ScreenUtils;

/**
 * Created by dengquan on 16-4-18.
 */
public class LoadMoreRecycleView extends RecyclerView
{
    private static final String TAG = LoadMoreRecycleView.class.getSimpleName();
    private int lastVisibleItem;
    private OnScrollChangedListener listener;
    private boolean isFullScreen = false;   //初始化数据是否充满屏幕。  true：可以加载更多   false :没有更多数据，不需要加载更多事件
    private Context context;

    public LoadMoreRecycleView(Context context)
    {
        this(context, null);
        LogUtils.i(TAG, "Load more recycleview with  one argument ");
    }

    public LoadMoreRecycleView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        LogUtils.i(TAG, "Load more recycleview with  two argument");
        this.context = context;
    }

    /**
     * 判断是否充满屏幕
     *
     * @return
     */
    private void checkIsFullScreen(Context context, MeasureLayoutManager manager)
    {
        float screenHeight = context.getResources().getDisplayMetrics().heightPixels;
        float density = context.getResources().getDisplayMetrics().density;
        int childCount = this.getAdapter().getItemCount();
        float sumChildHeight = 0;
        sumChildHeight = manager.getSumHeight();
        LogUtils.i(TAG, "screenHeight :" + screenHeight + "      sumChildHeight  :" + sumChildHeight + "  density :" + density);
        if (screenHeight - ScreenUtils.getStatusBarHeight() >= sumChildHeight)
        {
            isFullScreen = false;
        }
        else
        {
            isFullScreen = true;
        }
        initListener();
    }

    public void setIsFullScreen(MeasureLayoutManager manager)
    {
        checkIsFullScreen(context, manager);
    }

    public void setOnScrollChangedListener(OnScrollChangedListener listener)
    {
        this.listener = listener;
    }

    public interface OnScrollChangedListener
    {
        void onLoading();

        void onComplete();
    }

    private void initListener()
    {
        this.setOnScrollListener(new OnScrollListener()
        {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState)
            {
                super.onScrollStateChanged(recyclerView, newState);
                LogUtils.i(TAG, "onScrollStateChanged   :" + newState + "    lastVisibleItemPosition  :" + lastVisibleItem);
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && LoadMoreRecycleView.this.getAdapter().getItemCount()
                        == lastVisibleItem + 1)
                {
                    if (isFullScreen)
                    {
                        //加载数据
                        if (listener != null)
                        {
                            listener.onLoading();
                        }
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy)
            {
                super.onScrolled(recyclerView, dx, dy);
                //LogUtils.i(TAG,"onScrolled    dx :"+dx +"  dy : "+dy);
                LinearLayoutManager manager = (LinearLayoutManager) LoadMoreRecycleView.this.getLayoutManager();
                lastVisibleItem = manager.findLastVisibleItemPosition();
                if (!isFullScreen)
                {
                    //不需要加载数据。j使空间不可见
                    LogUtils.i(TAG, "child  position ：" + (recyclerView.getAdapter().getItemCount() - 1));
                    if((recyclerView.getAdapter().getItemCount()) == ((LinearLayoutManager) LoadMoreRecycleView.this.getLayoutManager()).findLastVisibleItemPosition() + 1)
                    {
                        View view = recyclerView.getChildAt(recyclerView.getAdapter().getItemCount() - 1);
                        view.setVisibility(View.GONE);
                    }
                }
            }
        });
    }
}
