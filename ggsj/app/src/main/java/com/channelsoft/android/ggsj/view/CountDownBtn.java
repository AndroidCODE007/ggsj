package com.channelsoft.android.ggsj.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;

import com.channelsoft.android.ggsj.R;
import com.channelsoft.android.ggsj.utils.LogUtils;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 倒计时的View
 * Created by dengquan on 16-3-25.
 */
public class CountDownBtn extends Button implements View.OnClickListener
{
    private static final String TAG = CountDownBtn.class.getSimpleName();
    private String TIP;
    private Integer TOTALTIME;
    private int tempTotalTime;
    private Integer normalBg;
    private Integer countTimeBg;
    private Activity activity;
    private final static String SECOND = "秒";
    private Timer timer;
    private GetGenerateCodeListener listener;
    private CountDownBtn instance;

    public CountDownBtn(Context context)
    {
        this(context, null);
    }

    public CountDownBtn(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public CountDownBtn(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr)
    {
        instance = this;
        if(attrs != null)
        {
            TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CountDown);
            if(array.length() > 0)
            {
                TIP = array.getString(R.styleable.CountDown_defTip);
                tempTotalTime = TOTALTIME = array.getInteger(R.styleable.CountDown_totalTime, 0);
                normalBg = array.getResourceId(R.styleable.CountDown_normalBg, 0);
                countTimeBg = array.getResourceId(R.styleable.CountDown_countTimeBg, 0);
                LogUtils.i(TAG, "data " + TIP + "  " + tempTotalTime + "  " + normalBg + "  " + countTimeBg);
                checkMessage(tempTotalTime, TIP, normalBg, countTimeBg);
                array.recycle();
            }
        }
    }

    public void setActivity(Activity activity)
    {
        if (activity == null)
        {
            throw new NullPointerException("activity non null ");
        }
        this.activity = activity;
    }

    private void start()
    {
        timer = new Timer();
        timer.schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                activity.runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        if (TOTALTIME > 0) {
                            CountDownBtn.this.instance.setText((TOTALTIME) + SECOND);
                            TOTALTIME = TOTALTIME - 1;
                        } else {
                            reset();
                        }
                    }
                });
            }
        }, 0, 1000);
    }

    /**
     *该法是在layout中不设置相关属性,则需要在code里面动态的设置属性
     * @param totalTime    倒计时的时间范围
     * @param tipId        默认的提示语
     * @param normalBgId   刚开始的默认背景.传入对应的Id
     * @param countTimeBgId   倒计时时的背景颜色.传入对应的ID
     */
    public void setMessage(int totalTime,int tipId,int normalBgId,int countTimeBgId)
    {
        tempTotalTime = this.TOTALTIME = totalTime;
        this.TIP = this.getResources().getString(tipId);
        normalBg = normalBgId;
        countTimeBg = countTimeBgId;
        checkMessage(TOTALTIME, TIP, normalBg, countTimeBg);
    }

    /**
     * 校验数据合法性.(如果数据不合法,则使用默认值).
     * @param totalTime
     * @param tip
     * @param normalBgId
     * @param countTimeBgId
     */
    private void checkMessage(int totalTime,String tip,int normalBgId,int countTimeBgId)
    {
        LogUtils.i(TAG,"checkMessage user count");
        if(totalTime <= 0)
        {
            tempTotalTime = totalTime = 60;
        }
        if(totalTime > 120)
        {
            tempTotalTime = totalTime = 120;
        }
        if(TextUtils.isEmpty(tip))
        {
            TIP = this.getResources().getString(R.string.btn_generatecode);
        }
        if(normalBgId == 0)
        {
            normalBg = R.color.colorAccent;    //默认背景,可以自己定义
        }
        if(countTimeBgId == 0)
        {
            countTimeBg = R.color.colorPrimary;   //倒计时背景,可以自己定义
        }
        this.setBackground(getResources().getDrawable(normalBg));
        this.setText(TIP);
        this.setGravity(Gravity.CENTER);
        this.setOnClickListener(this);
    }

    public void cance()
    {
        if(timer != null)
        {
            timer.cancel();
        }
    }

    @Override
    public void onClick(View v)
    {
        if(this.getText().equals(TIP))
        {
            this.setClickable(false);
            this.setBackground(this.getResources().getDrawable(countTimeBg));
            start();
            if(listener != null)
            {
                listener.getGenerateCode();
            }
        }
    }

    /**
     * 重新设置View状态为初始化状态.
     */
    public void reset()
    {
        CountDownBtn.this.instance.setBackground(CountDownBtn.this.getResources().getDrawable(normalBg));
        CountDownBtn.this.instance.setText(TIP);
        timer.cancel();
        TOTALTIME = tempTotalTime;
        CountDownBtn.this.instance.setClickable(true);
    }

    /**
     * 单击获取验证码以后的回调,在里面进行获取验证码的各种操作(networker)
     */
    public interface GetGenerateCodeListener
    {
        void getGenerateCode();
    }

    public void setGenerateCodeListener(GetGenerateCodeListener listener)
    {
        this.listener = listener;
    }
}
