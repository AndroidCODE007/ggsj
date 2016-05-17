package com.channelsoft.android.ggsj.home.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.channelsoft.android.ggsj.R;
import com.channelsoft.android.ggsj.base.GlobalApplication;
import com.channelsoft.android.ggsj.base.activity.BaseActivity;
import com.channelsoft.android.ggsj.databinding.ActivityMainBinding;
import com.channelsoft.android.ggsj.home.listener.OnScanQrResultListener;
import com.channelsoft.android.ggsj.login.activity.CaptureActivity;
import com.channelsoft.android.ggsj.push.pushmodel.IRegistMsgCentrePushModel;
import com.channelsoft.android.ggsj.push.pushmodel.RegistMsgCentrePushModelImpl;
import com.channelsoft.android.ggsj.utils.LogUtils;
import com.channelsoft.android.ggsj.utils.LoginManager;
import com.channelsoft.android.ggsj.utils.VersionCodeUtil;
import com.xiaomi.mipush.sdk.MiPushClient;

/**
 * 首页
 * Created by dengquan on 16-4-7.
 */
public class MainActivity extends BaseActivity implements
        View.OnClickListener, RegistMsgCentrePushModelImpl.OnRegistPush,OnScanQrResultListener
{
    private ActivityMainBinding binding;
    private ActionBarDrawerToggle mToggle;
    private static final String TAG = MainActivity.class.getSimpleName();

    private IRegistMsgCentrePushModel model;
    private String deviceOsVersion;
    private String appVersion;
    private String deviceModel;
    private String alias;
    private String userAccount;
    private String osType;
    private String regId;

    public static Intent newIntent(Context context)
    {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        if (!GlobalApplication.instance.isRegistToMiPush)
        {
            GlobalApplication.instance.registToMiPush();
        }

        registToMsgCentre();
        initToolBarAndDrawer();
    }

    private void registToMsgCentre()
    {
        LoginManager.setUserAccount(LoginManager.getEntId());

        deviceModel = Build.MODEL;
        deviceOsVersion = Build.VERSION.RELEASE;
        appVersion = VersionCodeUtil.getCurrentName();
        alias = LoginManager.getDeviceId();
        userAccount = LoginManager.getEntId();
        osType = "1";
        regId = LoginManager.getRegId();

        LogUtils.i(TAG, "    deviceOsVersion = " + deviceOsVersion
                + "    deviceModel = " + deviceModel + "     appVersion = "
                + appVersion + "    alias = " + alias + "    userAccount = " + userAccount
                + "    osType = " + osType);

        model = new RegistMsgCentrePushModelImpl(this);
        model.registMsgCentre(regId, deviceOsVersion, deviceModel, appVersion, alias, userAccount, osType);
    }

    private void initToolBarAndDrawer()
    {
        binding.mainToolbar.setTitle("咕咕商家");//设置标题
        binding.mainToolbar.setTitleTextColor(Color.parseColor("#ffffff"));//设置标题字体颜色
        setSupportActionBar(binding.mainToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);//决定左上角的图标是否可以点击。没有向左的小图标。 true 图标可以点击  false 不可以点击
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//给左上角图标的左边加上一个返回的图标

        mToggle = new ActionBarDrawerToggle(this, binding.mainDrawer, binding.mainToolbar, R.string.open_drawer, R.string.close_drawer)
        {
            @Override
            public void onDrawerOpened(View drawerView)
            {
                super.onDrawerOpened(drawerView);
                Toast.makeText(MainActivity.this, "打开侧滑栏", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDrawerClosed(View drawerView)
            {
                super.onDrawerClosed(drawerView);
                Toast.makeText(MainActivity.this, "关闭侧滑栏", Toast.LENGTH_SHORT).show();
            }
        };
        mToggle.syncState();//该方法会自动和actionBar关联, 将开关的图片显示在了action上，如果不设置，也可以有抽屉的效果，不过是默认的图标
        binding.mainDrawer.setDrawerListener(mToggle);

        binding.drawerItem1.setOnClickListener(this);
        binding.drawerItem2.setOnClickListener(this);
        binding.drawerItem3.setOnClickListener(this);
        binding.drawerItem4.setOnClickListener(this);
        binding.drawerItem5.setOnClickListener(this);

    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.drawer_item1:
                Toast.makeText(MainActivity.this, "扫码授权", Toast.LENGTH_SHORT).show();
                startActivity(CaptureActivity.newIntent(MainActivity.this,this));
                break;
            case R.id.drawer_item2:
                Toast.makeText(MainActivity.this, "点击侧滑栏标题2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.drawer_item3:
                Toast.makeText(MainActivity.this, "点击侧滑栏标题3", Toast.LENGTH_SHORT).show();
                break;
            case R.id.drawer_item4:
                Toast.makeText(MainActivity.this, "点击侧滑栏标题4", Toast.LENGTH_SHORT).show();
                break;
            case R.id.drawer_item5:
                Toast.makeText(MainActivity.this, "点击侧滑栏标题5", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onScanResult(String result)
    {
        try
        {
            {
                result = result.substring(result.indexOf("?") + 1, result.length());
                result = result.substring(result.indexOf("?") + 1, result.length());
                String[] strs = result.split("&");
                String authId = null;
                if (strs[0].startsWith("authId="))
                {
                    authId = strs[0].substring(7);
                }
                String regId = null;
                if (strs[1].startsWith("regId="))
                    regId = strs[1].substring(6);
                Log.v("", "解析结果--->url:" + result + "   authId:" + authId + "  regId:" + regId);
                if (result.equals("") || TextUtils.isEmpty(authId) || TextUtils.isEmpty(regId))
                {
                    Log.v("", "不认识的二维码--->url:" + result + "   authId:" + authId + "  regId:" + regId);
//                    resultIntent.putExtra("title", "扫码授权");
//                    //resultIntent.setClass(this, ScanCodeFailActivity.class);
//                    startActivity(resultIntent);
                    return;
                }
//                resultIntent.setClass(GlobalApplication.getInstance(), AuthorizationLoginActivity.class);
//                resultIntent.putExtra("authId", authId);
//                resultIntent.putExtra("regId", regId);
//                startActivity(resultIntent);
            }
        }
        catch (Exception e)
        {
            LogUtils.v("", "url 扫描失败  " + e.getMessage() + e.getLocalizedMessage());
//            resultIntent.setClass(this, ScanCodeFailActivity.class);
//            startActivity(resultIntent);
        }
    }

    @Override
    public void onRegistPushSuccess()
    {
        LogUtils.i(TAG, "消息中心注册成功");
    }

    @Override
    public void onRegistPushError()
    {
        LogUtils.i(TAG, "消息中心注册失败");
    }
}
