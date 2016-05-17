package com.channelsoft.android.ggsj.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.channelsoft.android.ggsj.R;
import com.channelsoft.android.ggsj.utils.LogUtils;
import com.channelsoft.android.ggsj.utils.ScreenUtils;


/**
 * 可以测量View是否大于一个屏幕，以及显示加载更多的View和底部布局的各种操作
 * Created by dengquan on 16-4-18.
 */
public class LoadMoreRecycleView extends RecyclerView
{
    private static final String TAG = LoadMoreRecycleView.class.getSimpleName();
    private int lastVisibleItem;
    private OnScrollChangedListener listener;
    private boolean isFullScreen = false;   //初始化数据是否充满屏幕。  true：可以加载更多   false :没有更多数据，不需要加载更多事件
    private boolean isLoading = false;   //是否正在加载更多。
    private Context context;
    private RecyclerView.Adapter mAdapter;
    private View mFooterView;
    private RecyclerView.Adapter mWrapperAdapter;
    private static final int ORDER_DETAIL = 1;
    private static final int LOAD_MORE = 2;

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
        initView(context);
    }


    private void initView(Context context)
    {
        mFooterView = LayoutInflater.from(context).inflate(R.layout.footer_view,null);
    }


    @Override
    public void setAdapter(Adapter adapter)
    {
        mAdapter = adapter;
        super.setAdapter(adapter);
        mWrapperAdapter = new WrapperAdapter(mAdapter,mFooterView);
        mAdapter.registerAdapterDataObserver(observer);
    }


    public class WrapperAdapter extends RecyclerView.Adapter
    {
        private RecyclerView.Adapter adapter;
        private View footerView;
        public WrapperAdapter(RecyclerView.Adapter adapter,View footerView)
        {
            this.adapter = adapter;
            this.footerView = footerView;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            if(viewType == ORDER_DETAIL)
            {
                return adapter.onCreateViewHolder(parent,viewType);
            }
            else
            {
                return new FooterViewHolder(footerView);
            }
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position)
        {
            if(holder instanceof FooterViewHolder)
            {
                FooterViewHolder holder1 = (FooterViewHolder) holder;
            }
            else
            {
                if(adapter != null)
                {
                    adapter.onBindViewHolder(holder,position);
                }
            }
        }

        @Override
        public int getItemCount()
        {
            if(adapter != null)
            {
                return  adapter.getItemCount() + 1;
            }
            else
            {
                return  0;
            }
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
        public long getItemId(int position)
        {
            return super.getItemId(position);
        }

        class FooterViewHolder extends RecyclerView.ViewHolder
        {
            public FooterViewHolder(View itemView)
            {
                super(itemView);
            }
        }
    }


    public RecyclerView.AdapterDataObserver observer = new RecyclerView.AdapterDataObserver()
    {
        @Override
        public void onChanged()
        {
            mWrapperAdapter.notifyDataSetChanged();
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount, Object payload)
        {
            mWrapperAdapter.notifyItemRangeChanged(positionStart,itemCount,payload);
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount)
        {
            mWrapperAdapter.notifyItemRangeChanged(positionStart,itemCount);
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount)
        {
            mWrapperAdapter.notifyItemRangeInserted(positionStart,itemCount);
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount)
        {
            mWrapperAdapter.notifyItemRangeRemoved(positionStart,itemCount);
        }

        @Override
        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount)
        {
            mWrapperAdapter.notifyItemRemoved(fromPosition);
        }
    };

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
    }

    private void initListener()
    {
        this.setOnScrollListener(new OnScrollListener()
        {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState)
            {
                super.onScrollStateChanged(recyclerView, newState);
                LogUtils.i(TAG, "onScrollStateChanged   :" + newState +
                        "    lastVisibleItemPosition  :" + lastVisibleItem);
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && LoadMoreRecycleView.this.getAdapter().getItemCount()
                        == lastVisibleItem + 1)
                {
                    if (isFullScreen)
                    {
                        //加载数据
                        if (listener != null && !isLoading )
                        {
                            isLoading = true;
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
                LogUtils.i(TAG,"is full screen :"+isFullScreen);
                if (!isFullScreen)
                {
                    //不需要加载数据。j使空间不可见
                    LogUtils.i(TAG, "child  position ：" + (recyclerView.getAdapter().getItemCount() - 1));
                    if((recyclerView.getAdapter().getItemCount()) ==
                            ((LinearLayoutManager) LoadMoreRecycleView.this.getLayoutManager()).findLastVisibleItemPosition() + 1)
                    {
                        View view = recyclerView.getChildAt(recyclerView.getAdapter().getItemCount() - 1);
                        if(view != null)
                        {
                            view.setVisibility(View.GONE);
                        }
                    }
                }
            }
        });
    }

    /**
     * 加载完成以后，设置isLoading为false
     */
    public void setIsLoading()
    {
        isLoading = false;
    }

}
