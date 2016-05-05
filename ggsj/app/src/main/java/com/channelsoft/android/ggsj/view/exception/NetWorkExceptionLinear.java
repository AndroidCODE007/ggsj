package com.channelsoft.android.ggsj.view.exception;
import com.channelsoft.android.ggsj.R;
import com.channelsoft.android.ggsj.order.listener.OnDynamicClickListener;
import com.channelsoft.android.ggsj.utils.ScreenUtils;
import com.channelsoft.android.ggsj.view.exception.BaseLinear;



import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;



/**
 * 网络异常
 * Created by dengquan on 2015/10/29.
 */
public class NetWorkExceptionLinear extends LinearLayout implements BaseLinear
{
    private ImageView bg;
    private TextView textView;

    private RelativeLayout.LayoutParams params;
    private LayoutParams bgParams;
    private LayoutParams textParams;
    public OnDynamicClickListener listener = null;
    private float density = ScreenUtils.getDensity();

    public NetWorkExceptionLinear(Context context)
    {
        this(context, null);
    }

    public NetWorkExceptionLinear(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public NetWorkExceptionLinear(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        initView(context);
        setContentSize(context);
        initView(context, attrs);
    }

    /**
     * 初始化对象
     *
     * @param context
     */
    private void initView(Context context)
    {
        params = (RelativeLayout.LayoutParams) this.getLayoutParams();
        if (params == null)
        {
            params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.MATCH_PARENT);
            params.addRule(RelativeLayout.CENTER_IN_PARENT);
        }

        bg = new ImageView(context);
        this.addView(bg);
        bgParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        textView = new TextView(context);
        this.addView(textView);
        textParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        this.setGravity(Gravity.CENTER);
        this.setOrientation(VERTICAL);
        this.setLayoutParams(params);
    }

    /**
     * 设置内容区域的大小
     *
     * @param context
     */
    private void setContentSize(Context context)
    {
        if (bgParams != null)
        {
            bgParams.width = (int) (90 * density);
            bgParams.height = (int) (90 * density);
            bgParams.gravity = Gravity.CENTER;
        }

        if (textParams != null)
        {
            textParams.height = (int) (45 * density);
            textParams.width = (int) (280 * density);
            bgParams.gravity = Gravity.CENTER;
        }

        bg.setLayoutParams(bgParams);
        textView.setLayoutParams(textParams);
    }

    private void initView(Context context, AttributeSet attrs)
    {
        TypedArray array = context.obtainStyledAttributes(R.styleable.NetWorkException);
        if (array != null)
        {
            int bgId = array.getResourceId(R.styleable.NetWorkException_NetWorkExceptionBg, 0);
            if (bgId == 0)
            {
                bgId = R.mipmap.ic_launcher;
            }
            bg.setImageResource(bgId);

            String content = array.getString(R.styleable.NetWorkException_NetWorkDescription);
            if (TextUtils.isEmpty(content))
            {
                content = "哎呀，请检查网络设置。。。。。。";
            }
            textView.setText(content);
        }
        array.recycle();
        textView.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(listener != null)
                {
                    listener.onDynamicClick(OnDynamicClickListener.TypeClick.netExceptionClick);
                }
            }
        });
    }

    /**
     * @param bgId    @not null
     * @param content
     */
    public void setContent(int bgId, int content)
    {
        bg.setImageResource(bgId);
        textView.setText(content);
    }


    @Override
    public void setOnDynamicClickListener(OnDynamicClickListener listener)
    {
        this.listener = listener;
    }
}
