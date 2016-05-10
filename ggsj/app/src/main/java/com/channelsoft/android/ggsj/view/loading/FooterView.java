package com.channelsoft.android.ggsj.view.loading;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ProgressBar;

import com.channelsoft.android.ggsj.R;
import com.channelsoft.android.ggsj.utils.LogUtils;

/**
 * 底部加载布局的动画
 * Created by dengquan on 16-5-5.
 */
public class FooterView extends ProgressBar
{
    private static final String TAG = SpinKitView.class.getSimpleName();
    private Style mStyle;
    private int mColor;
    private Sprite mSprite;

    public FooterView(Context context)
    {
        this(context, null);
    }

    public FooterView(Context context, AttributeSet attrs)
    {
        this(context, attrs, R.attr.SpinKitViewStyle);
    }

    public FooterView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        this(context, attrs, defStyleAttr, R.style.SpinKitView);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public FooterView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes)
    {
        super(context, attrs, defStyleAttr, defStyleRes);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SpinKitView, defStyleAttr,
                defStyleRes);
        mStyle = Style.values()[a.getInt(R.styleable.SpinKitView_SpinKit_Style, 5)];
        mColor = a.getColor(R.styleable.SpinKitView_SpinKit_Color, Color.RED);
        a.recycle();
        init();
        setIndeterminate(true);
    }

    private void init()
    {
        Sprite sprite = SpriteFactory.create(mStyle);
        setIndeterminateDrawable(sprite);
        LogUtils.i(TAG,"init spink ："+sprite.toString() +"   "+mStyle);
    }

    @Override
    public void setIndeterminateDrawable(Drawable d)
    {
        if (!(d instanceof Sprite))
        {
            throw new IllegalArgumentException("this d must be instanceof Sprite");
        }
        setIndeterminateDrawable((Sprite) d);
    }

    public void setIndeterminateDrawable(Sprite d)
    {
        super.setIndeterminateDrawable(d);
        mSprite = d;
        if (mSprite.getColor() == 0)
        {
            mSprite.setColor(mColor);
        }
        onSizeChanged(getWidth(), getHeight(), getWidth(), getHeight());
        if (getVisibility() == VISIBLE)
        {
            mSprite.start();
        }
    }

    @Override
    public Sprite getIndeterminateDrawable()
    {
        return mSprite;
    }

    @Override
    public void unscheduleDrawable(Drawable who)
    {
        super.unscheduleDrawable(who);
        if (who instanceof Sprite)
        {
            ((Sprite) who).stop();
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus)
    {
        super.onWindowFocusChanged(hasWindowFocus);
        if (hasWindowFocus)
        {
            if (mSprite != null && getVisibility() == VISIBLE)
            {
                mSprite.start();
            }
        }
    }

    @Override
    public void onScreenStateChanged(int screenState)
    {
        super.onScreenStateChanged(screenState);
        if (screenState == View.SCREEN_STATE_OFF)
        {
            if (mSprite != null)
            {
                mSprite.stop();
            }
        }
    }
}
