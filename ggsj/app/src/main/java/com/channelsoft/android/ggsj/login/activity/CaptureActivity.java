package com.channelsoft.android.ggsj.login.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.channelsoft.android.ggsj.R;
import com.channelsoft.android.ggsj.base.activity.BaseActivity;
import com.channelsoft.android.ggsj.home.listener.OnScanQrResultListener;
import com.channelsoft.android.ggsj.utils.LogUtils;
import com.channelsoft.android.ggsj.zxing.AmbientLightManager;
import com.channelsoft.android.ggsj.zxing.BeepManager;
import com.channelsoft.android.ggsj.zxing.CameraManager;
import com.channelsoft.android.ggsj.zxing.CaptureActivityHandler;
import com.channelsoft.android.ggsj.zxing.FinishListener;
import com.channelsoft.android.ggsj.zxing.InactivityTimer;
import com.channelsoft.android.ggsj.zxing.ViewfinderView;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.Result;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Collection;
import java.util.Map;

/**
 *扫描二维码
 */
public final class CaptureActivity extends BaseActivity implements
        SurfaceHolder.Callback
{
    private static final String TAG = CaptureActivity.class.getSimpleName();
    private CameraManager cameraManager;
    private CaptureActivityHandler handler;
    private Result savedResultToShow;
    private ViewfinderView viewfinderView;
    private boolean hasSurface;
    private Collection<BarcodeFormat> decodeFormats;
    private Map<DecodeHintType, ?> decodeHints;
    private String characterSet;
    private InactivityTimer inactivityTimer;
    private BeepManager beepManager;
    private AmbientLightManager ambientLightManager;
    private static OnScanQrResultListener mScanQrResultListener;
    public static Intent newIntent(Context context,OnScanQrResultListener onScanQrResultListener)
    {
        Intent intent = new Intent(context,CaptureActivity.class);
        mScanQrResultListener = onScanQrResultListener;
        return intent;
    }
    public ViewfinderView getViewfinderView()
    {
        return viewfinderView;
    }

    public Handler getHandler()
    {
        return handler;
    }

    public CameraManager getCameraManager()
    {
        return cameraManager;
    }

    @Override
    public void onCreate(Bundle icicle)
    {
        super.onCreate(icicle);

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_capture);
        setScanWidthHeight();

        hasSurface = false;
        inactivityTimer = new InactivityTimer(this);
        beepManager = new BeepManager(this);
        ambientLightManager = new AmbientLightManager(this);
    }

    private void setScanWidthHeight()
    {
        //设置扫描的大小
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int widthPixels = metrics.widthPixels;
        int heightPixels = metrics.heightPixels;
        int width = widthPixels < heightPixels ? widthPixels : heightPixels;
        if (width <= 0)
            width = 320;
        CameraManager.MIN_FRAME_WIDTH = (int) (width * 3 / 5);
        CameraManager.MIN_FRAME_HEIGHT = (int) (width * 3 / 5);
        CameraManager.MAX_FRAME_WIDTH = (int) (width * 3 / 5);//(int)(width*2/3);
        CameraManager.MAX_FRAME_HEIGHT = (int) (width * 3 / 5);
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void onResume()
    {
        super.onResume();

        cameraManager = new CameraManager(getApplication());

        viewfinderView = (ViewfinderView) findViewById(R.id.viewfinder_view);
        viewfinderView.setCameraManager(cameraManager);

        handler = null;
        resetStatusView();

        SurfaceView surfaceView = (SurfaceView) findViewById(R.id.preview_view);
        SurfaceHolder surfaceHolder = surfaceView.getHolder();
        if (hasSurface)
        {
            initCamera(surfaceHolder);
        }
        else
        {
            surfaceHolder.addCallback(this);
            surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        }

        SurfaceHolder.Callback callback = new SurfaceHolder.Callback()
        {
            @Override
            public void surfaceCreated(SurfaceHolder holder)
            {

            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
            {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder)
            {

            }
        };

        beepManager.updatePrefs();
        ambientLightManager.start(cameraManager);

        inactivityTimer.onResume();

        decodeFormats = null;
        characterSet = null;
    }

    @Override
    protected void onPause()
    {
        if (handler != null)
        {
            handler.quitSynchronously();
            handler = null;
        }
        inactivityTimer.onPause();
        ambientLightManager.stop();
        cameraManager.closeDriver();
        if (!hasSurface)
        {
            SurfaceView surfaceView = (SurfaceView) findViewById(R.id.preview_view);
            SurfaceHolder surfaceHolder = surfaceView.getHolder();
            surfaceHolder.removeCallback(this);
        }
        super.onPause();
    }

    @Override
    protected void onDestroy()
    {
        inactivityTimer.shutdown();
        viewfinderView.recycleLineDrawable();
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        switch (keyCode)
        {
            case KeyEvent.KEYCODE_CAMERA:// 拦截相机键
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void decodeOrStoreSavedBitmap(Bitmap bitmap, Result result)
    {
        if (handler == null)
        {
            savedResultToShow = result;
        } else
        {
            if (result != null)
            {
                savedResultToShow = result;
            }
            if (savedResultToShow != null)
            {
                Message message = Message.obtain(handler,
                        R.id.decode_succeeded, savedResultToShow);
                handler.sendMessage(message);
            }
            savedResultToShow = null;
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder)
    {
        if (!hasSurface)
        {
            hasSurface = true;
            initCamera(holder);
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder)
    {
        hasSurface = false;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height)
    {

    }

    /**
     * 结果处理
     */
    public void handleDecode(Result rawResult, Bitmap barcode, float scaleFactor)
    {
        inactivityTimer.onActivity();
        beepManager.playBeepSoundAndVibrate();

        String resultString = rawResult.getText();
        resultString = URLDecoder.decode(resultString);
        LogUtils.i(TAG,"scan result :"+resultString);
        if(mScanQrResultListener != null)
        {
            mScanQrResultListener.onScanResult(resultString);
        }

    }

    private void initCamera(SurfaceHolder surfaceHolder)
    {
        if (surfaceHolder == null)
        {
            return;
        }
        if (cameraManager.isOpen())
        {
            return;
        }
        try
        {
            cameraManager.openDriver(surfaceHolder);
            if (handler == null)
            {
                handler = new CaptureActivityHandler(this, decodeFormats,
                        decodeHints, characterSet, cameraManager);
            }
            decodeOrStoreSavedBitmap(null, null);
        } catch (IOException ioe)
        {
            displayFrameworkBugMessageAndExit();
        } catch (RuntimeException e)
        {
            displayFrameworkBugMessageAndExit();
        }
    }

    private void displayFrameworkBugMessageAndExit()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("警告");
        builder.setMessage("抱歉，相机出现问题，您可能需要设置允许使用相机");
        builder.setPositiveButton("确定", new FinishListener(this));
        builder.setOnCancelListener(new FinishListener(this));
        builder.show();
    }

    public void restartPreviewAfterDelay(long delayMS)
    {
        if (handler != null)
        {
            handler.sendEmptyMessageDelayed(R.id.restart_preview, delayMS);
        }
        resetStatusView();
    }

    private void resetStatusView()
    {
        viewfinderView.setVisibility(View.VISIBLE);
    }

    public void drawViewfinder()
    {
        viewfinderView.drawViewfinder();
    }
}
