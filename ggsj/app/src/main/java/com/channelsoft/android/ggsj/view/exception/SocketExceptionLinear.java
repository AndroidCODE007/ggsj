package com.channelsoft.ggsj.view.exception;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.channelsoft.android.ggsj.R;
import com.channelsoft.android.ggsj.utils.ScreenUtils;


/**
 * socket异常
 * Created by dengquan on 2015/10/29.
 */
public class SocketExceptionLinear extends LinearLayout{
    private ImageView bg;
    private TextView textView;

    private LayoutParams params;
    private LayoutParams bgParams;
    private LayoutParams textParams;
    private float density = ScreenUtils.getDensity();
    public SocketExceptionLinear(Context context)
    {
        this(context,null);
    }

    public SocketExceptionLinear(Context context, AttributeSet attrs)
    {
        this(context, attrs,0);
    }

    public SocketExceptionLinear(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        initView(context);
        setContentSize(context);
        initView(context,attrs);
    }

    /**
     * 初始化对象
     * @param context
     */
    private void initView(Context context)
    {
        params = (LinearLayout.LayoutParams)this.getLayoutParams();
        if(params == null)
        {
            params = new LinearLayout.LayoutParams(context,null);
        }


        bg = new ImageView(context);
        bgParams = new LayoutParams(context,null);

        textView = new TextView(context);
        textParams = new LayoutParams(context,null);

        this.addView(bg);
        this.addView(textView);
        this.setGravity(Gravity.CENTER);
        this.setOrientation(VERTICAL);

    }

    /**
     * 设置内容区域的大小
     * @param context
     */
    private void setContentSize(Context context)
    {
        if(bgParams != null)
        {
            bgParams.width = (int)(90 * density);
            bgParams.height = (int)(90 * density);
        }

        if(textParams != null)
        {
            textParams.height = (int)(45 * density);
            textParams.width = (int)(180 * density);
        }

        bg.setLayoutParams(bgParams);
        textView.setLayoutParams(textParams);
    }

    private void initView(Context context,AttributeSet attrs)
    {
        TypedArray array = context.obtainStyledAttributes(R.styleable.SocketException);
        if(array != null)
        {
            int bgId = array.getResourceId(R.styleable.SocketException_SocketExceptionBg,0);
            if(bgId == 0)
            {
                bgId = R.mipmap.ic_launcher;
            }
            bg.setImageResource(bgId);

            String content = array.getString(R.styleable.SocketException_SocketExceptionDescription);
            if(TextUtils.isEmpty(content))
            {
                content = "哎呀，暂时没有任何数据。。。。。。";
            }
            textView.setText(content);
        }
    }

    /**
     *
     * @param bgId  @not null
     * @param content
     */
    public void setContent(int bgId ,int content)
    {
        bg.setImageResource(bgId);
        textView.setText(content);
    }

}
