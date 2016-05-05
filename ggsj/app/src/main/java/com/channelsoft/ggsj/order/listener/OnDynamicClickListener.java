package com.channelsoft.ggsj.order.listener;

/**
 * Created by dengquan on 16-4-25.
 */
public interface OnDynamicClickListener
{
    void onDynamicClick(TypeClick type);

    /**
     * 事件的类型。
     */
    enum TypeClick
    {
        socketExceptionClick,netExceptionClick,emptyClick;
    }
}
