package com.channelsoft.android.ggsj.push.service;

import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.WindowManager;

import com.channelsoft.android.ggsj.R;
import com.channelsoft.android.ggsj.base.GlobalApplication;
import com.channelsoft.android.ggsj.push.dialog.CommonDialog;
import com.channelsoft.android.ggsj.utils.LogUtils;
import com.channelsoft.android.ggsj.utils.ScreenUtils;

import java.util.Random;

/**
 * Created by chenyg on 2016/5/6.
 */
public class PushNotifyService extends Service {

    private Context context;
    private static final String TAG = PushNotifyService.class.getSimpleName();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        context = this;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(intent == null){
            return super.onStartCommand(intent, flags, startId);
        }
        String action = intent.getStringExtra("action");

        if(!TextUtils.isEmpty(action) && action.equals("dialog")){
            dialog(intent);
        }else if(!TextUtils.isEmpty(action) && action.equals("notification")){
            String title = intent.getStringExtra("title");
            int notifyType = intent.getIntExtra("notifyType", 0);
            String orderId = intent.getStringExtra("orderId");
            String startActivityAction = intent.getStringExtra("startActivityAction");

            LogUtils.i(TAG, "OnStartCommand  action = " + action + "   title = " + title + "   notifyType = " + notifyType + "orderId = "
                    + orderId + "startActivityAction = " + startActivityAction);

            notification(context,title,orderId,notifyType,startActivityAction);
        }
        return super.onStartCommand(intent, flags, startId);
    }

    private void notification(Context context, String tickerText, String orderId, int notifyType, String startActivityAction){
        if(!ScreenUtils.isScreenOn()){
            ScreenUtils.openScreenTime(5000);
        }
        NotificationManager manager = (NotificationManager) GlobalApplication.getInstance().getSystemService(Context.NOTIFICATION_SERVICE);

        Intent intent;

        //在前台就启动目标页面，在后台就启动MainActivity
        if(ScreenUtils.isForeground(context,"com.channelsoft.android.ggsj")){
            LogUtils.i(TAG,"foreground");
            intent = new Intent();
            intent.setAction(startActivityAction);
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }else {
            LogUtils.i(TAG,"background");
            PackageManager packageManager = context.getPackageManager();
            intent = packageManager.getLaunchIntentForPackage("com.channelsoft.android.ggsj");
        }

        PendingIntent pendingIntent = PendingIntent.getActivity(context, new Random().nextInt(Integer.MAX_VALUE), intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification notification = new Notification.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                .setTicker(tickerText)
                .setContentTitle(tickerText)
                .setContentText("点击查看详情")
                .setContentIntent(pendingIntent)
                .setWhen(System.currentTimeMillis())
                .getNotification();

        notification.flags |= Notification.FLAG_AUTO_CANCEL;

        try {

            LogUtils.i(TAG,"try id = " + Integer.valueOf(orderId.substring(orderId.length() - 8)));
            manager.notify(Integer.valueOf(orderId.substring(orderId.length() - 8)), notification);

        }catch(Exception e) {

            LogUtils.i(TAG,"catch id = " + new Random().nextInt(Integer.MAX_VALUE));
            manager.notify(new Random().nextInt(Integer.MAX_VALUE), notification);

        }

    }

    private void dialog(Intent intent){
        String type = intent.getStringExtra("type");
        String orderId = intent.getExtras().getString("data");
        makeOrderDialog(intent, orderId);
    }

    private void makeOrderDialog(Intent intent, String orderId) {
        final String title = intent.getExtras().getString("title");
        String content = intent.getExtras().getString("content");
        String leftbtn = intent.getExtras().getString("leftbtn");
        String rightbtn = intent.getExtras().getString("rightbtn");
        Dialog dialog = new CommonDialog(context, new CommonDialog.clickListener()
        {
            @Override
            public void clickSure(String result) {
                if(result.equals("1")) {//点确定的操作

                }
            }
        }, title, content, leftbtn, rightbtn);
        dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        dialog.show();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
