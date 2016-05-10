package com.channelsoft.android.ggsj.push.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.channelsoft.android.ggsj.R;
import com.channelsoft.android.ggsj.base.GlobalApplication;

/**
 * Created by chenyg on 2016/5/9.
 */
public class CommonDialog extends Dialog {

    private Context context;
    private clickListener listener;
    private TextView tvEnsure, tvCancel;

    public CommonDialog(Context context, final clickListener listener, String title, String content, String btnLeft, String btnRight) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_common);
        setCanceledOnTouchOutside(false);
        tvEnsure = (TextView) findViewById(R.id.tv_sure);
        tvEnsure.setText(btnRight);
        tvCancel = (TextView) findViewById(R.id.tv_cancel);
        tvCancel.setText(btnLeft);
        ((TextView) findViewById(R.id.tv_title)).setText(title);
        ((TextView) findViewById(R.id.tv_content)).setText(content);
        tvEnsure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.clickSure("1");
                }
                dismiss();
            }
        });
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.clickSure("0");
                }
                dismiss();
            }
        });
    }

    @Override
    public void show() {
        GlobalApplication.isShow = true;
        super.show();
    }

    @Override
    public void dismiss() {
        GlobalApplication.isShow = false;
        super.dismiss();
    }

    public interface clickListener {
        public void clickSure(String result);
    }
}
