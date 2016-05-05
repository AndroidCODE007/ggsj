package com.channelsoft.android.ggsj.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.channelsoft.android.ggsj.R;
import com.channelsoft.android.ggsj.order.listener.OnDynamicClickListener;
import com.channelsoft.android.ggsj.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dengquan on 16-4-22.
 */
public class DynamicViewGroup extends RelativeLayout implements OnDynamicClickListener
{
    private static final String TAG = DynamicViewGroup.class.getSimpleName();
    private View fullDataView;   //特指有数据的View
    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<View> mViews;   ///所有的View的集合
    private OnBtnClickListener listener;

    // Default Tags
    private final String NET_WORK_EXCEPTION = "NET_WORK_EXCEPTION";
    private final String LOADING_CONTENT = "LOADING_CONTENT";
    private final String SOCKET_EXCEPTION = "SOCKET_EXCEPTION";
    private final String EMPTY_CONTENT = "EMPTY_CONTENT";
    private final String FULL_CONTENT = "FULL_CONTENT";


    public DynamicViewGroup(Context context)
    {
        this(context,null);
    }

    public DynamicViewGroup(Context context, AttributeSet attrs)
    {
        this(context, attrs,0);
    }

    public DynamicViewGroup(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    /**
     * 初始化参数
     * @param context
     */
    private void init(Context context)
    {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mViews = new ArrayList<>();

    }

    /**
     * 初始化View
     */
    private void initView()
    {
        setDefaultViews();
    }

    /**
     * Return a view based on layout id
     *
     * @param layout Layout Id
     * @param tag    Layout Tag
     * @return View
     */
    private View initView(int layout, String tag)
    {
        View view = mInflater.inflate(layout, null);

        view.setTag(tag);
        view.setVisibility(View.GONE);
        return view;
    }

    /**
     * 初始化其他的View对象
     */
    private void setDefaultViews()
    {
        View mLoadingLayout = initView(R.layout.loading_view, LOADING_CONTENT);
        addCustomView(mLoadingLayout,LOADING_CONTENT);
        View netWorkExceptionLayout = initView(R.layout.network_exception, NET_WORK_EXCEPTION);
        addCustomView(netWorkExceptionLayout,NET_WORK_EXCEPTION);
        View mSocketExceptionLayout = initView(R.layout.socket_exception, SOCKET_EXCEPTION);
        addCustomView(mSocketExceptionLayout,SOCKET_EXCEPTION);
        View mEmptyLayout = initView(R.layout.empty_linear,EMPTY_CONTENT);
        addCustomView(mEmptyLayout,EMPTY_CONTENT);

        mViews.add(1, mLoadingLayout);
        mViews.add(2, netWorkExceptionLayout);
        mViews.add(3, mSocketExceptionLayout);
        mViews.add(4, mEmptyLayout);


        // init Layout params
        RelativeLayout.LayoutParams containerParams =
                new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        containerParams.addRule(RelativeLayout.CENTER_VERTICAL);

        // init new RelativeLayout Wrapper
        this.setLayoutParams(containerParams);

        // Add default views
        this.addView(mLoadingLayout);
        this.addView(netWorkExceptionLayout);
        this.addView(mSocketExceptionLayout);
        this.addView(mEmptyLayout);
        setViewLayoutParams(mViews);
    }

    /**
     *显示加载中布局
     */
    public void showLoadingLayout()
    {
        show(LOADING_CONTENT);
    }

    /**
     *显示网络异常
     */
    public void showNetWorkExceptionLayout()
    {
        show(NET_WORK_EXCEPTION);
    }

    /**
     *显示通信异常布局
     */
    public void showExceptionLayout()
    {
        show(SOCKET_EXCEPTION);
    }

    /**
     *显示数据（正常）
     */
    public void showCustomView()
    {
        show(FULL_CONTENT);
    }

    /**
     * 显示空的数据布局
     */
    public void showEmptyView()
    {
        show(EMPTY_CONTENT);
    }

    /**
     * 隐藏所有布局
     */
    public void hideAll()
    {
        ArrayList<View> views = new ArrayList<>(mViews);
        for (View view : views)
        {
            view.setVisibility(View.GONE);
        }
    }

    private void show(String tag)
    {
        ArrayList<View> views = new ArrayList<>(mViews);
        for (View view : views)
        {
            if (view.getTag() != null && view.getTag().toString().equals(tag))
            {
                view.setVisibility(View.VISIBLE);
                LogUtils.i(TAG,"view visible +"+view.toString() + view.getTag()
                        +view.getWidth() +"   "+view.getHeight());
            }
            else
            {
                view.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onDynamicClick(com.channelsoft.android.ggsj.order.listener.OnDynamicClickListener.TypeClick type)
    {
        switch (type)
        {
            case emptyClick:
                if(listener != null)
                {
                    listener.onEmptyRefresh();
                }
                break;
            case socketExceptionClick:
                if(listener != null)
                {
                    listener.onRefresh();
                }
                break;

            case netExceptionClick:
                if(listener != null)
                {
                    listener.onCheckNetWork();
                }
                break;
        }
    }
    /**
     * 为布局添加标记
     * @param customView
     * @param tag
     */
    private void addCustomView(View customView, String tag)
    {
        customView.setTag(tag);
        customView.setVisibility(View.GONE);
    }

    public View getDataView()
    {
        return fullDataView;
    }


    public void setDataView(View view)
    {
        fullDataView = view;
        addCustomView(fullDataView,FULL_CONTENT);
        this.addView(fullDataView);
        mViews.add(0,fullDataView);
        initView();
        //show(LOADING_CONTENT);
    }


    private void setViewLayoutParams(List<View> list)
    {
        ArrayList<View> views = (ArrayList<View>) list;
        for (View view : views)
        {
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)view.getLayoutParams();
            params.width = LayoutParams.MATCH_PARENT;
            params.height = LayoutParams.MATCH_PARENT;
            params.addRule(RelativeLayout.CENTER_IN_PARENT);
            view.setLayoutParams(params);
        }
    }


    public void setOnBtnClickListener(OnBtnClickListener listener)
    {
        this.listener = listener;
    }


    /**
     * 按钮事件的监听
     */
    public interface OnBtnClickListener
    {
        /**
         * 检查网络
         */
        void onCheckNetWork();

        /**
         * 当数据为null时，需要一些操作
         */
        void onEmptyRefresh();


        /**
         * 当Socket异常时刷新数据
         */
        void onRefresh();
    }

}
