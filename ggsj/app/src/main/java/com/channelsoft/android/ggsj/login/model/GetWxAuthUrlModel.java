package com.channelsoft.android.ggsj.login.model;

import android.graphics.Bitmap;
import android.os.Message;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.channelsoft.android.ggsj.http.Http;
import com.channelsoft.android.ggsj.http.Url;
import com.channelsoft.android.ggsj.login.bean.GetWxUrlInfo;
import com.channelsoft.android.ggsj.utils.LogUtils;
import com.channelsoft.android.ggsj.utils.LoginManager;
import com.channelsoft.android.ggsj.utils.VersionCodeUtil;
import com.google.gson.Gson;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * 获取微信授权Url
 * Created by dengquan on 16-5-10.
 */
public class GetWxAuthUrlModel implements IGetWXAuthUrlModel
{
    private static final String TAG = GetWxAuthUrlModel.class.getSimpleName();
    private static final int MESSAGE = 0x12;
    private android.os.Handler handler;
    private IStaffLoginView view;

    public GetWxAuthUrlModel(final IStaffLoginView view)
    {
        this.view = view;
        handler = new android.os.Handler()
        {
            @Override
            public void handleMessage(Message msg)
            {
                super.handleMessage(msg);
                if(GetWxAuthUrlModel.this.view != null)
                {
                    LogUtils.i(TAG,"bitmap :"+msg.obj.toString());
                    GetWxAuthUrlModel.this.view.setBitmap((Bitmap) (msg.obj));
                }
            }
        };
    }

    public void getWxAuthUrl()
    {
        String url = Url.Login.GET_WX_AUTH_URL;
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String s)
            {
                LogUtils.i(TAG, "wx  url :" + s);
                Gson gson = new Gson();
                GetWxUrlInfo info = gson.fromJson(s.toString(), GetWxUrlInfo.class);
                if (Http.RETURNCODE_OK.equals(info.getReturnCode()))
                {
                    LogUtils.i(TAG,"on success");
                    makeBitmap(info.getQrUrl());
                }
            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError volleyError)
            {
                LogUtils.i(TAG,"on error response :"+volleyError.getMessage());
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                Map<String, String> map = new HashMap<>();
                map.put("deviceId", LoginManager.getDeviceId());
                map.put("deviceModel", android.os.Build.MODEL);//设备型号
                map.put("deviceOsVersion", android.os.Build.VERSION.RELEASE);//系统版本
                map.put("product", "ggsj");
                map.put("regId", LoginManager.getRegId());
                map.put("appVersion", VersionCodeUtil.getCurrentName());//应用版本号
                map.put("deviceOsType", "1");//系统类型 1android 2ios
                LogUtils.i(TAG, "params :" + map.toString());
                return map;
            }
        };
        Http.addRequest(request, TAG);
    }

    private void makeBitmap(final String url)
    {
        Runnable runnable = new Runnable()
        {
            @Override
            public void run()
            {
                Message message = new Message();
                message.what = MESSAGE;
                message.obj = urlToBitmap(url);
                LogUtils.i(TAG,"run :"+message.obj.toString());
                handler.sendMessage(message);
            }
        };
        new Thread(runnable).start();
    }

    private Bitmap urlToBitmap(String url)
    {
        Bitmap bitmap = null;
        try
        {
            // 判断URL合法性
            if (url == null || "".equals(url) || url.length() < 1)
            {
                return null;
            }
            Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
            hints.put(EncodeHintType.MARGIN, 0);// 二维码空白边缘宽度
            // 图像数据转换，使用了矩阵转换
            BitMatrix bitMatrix = new QRCodeWriter().encode(url,
                    BarcodeFormat.QR_CODE, 500, 500, hints);
            int[] pixels = new int[500 * 500];
            // 下面这里按照二维码的算法，逐个生成二维码的图片，
            // 两个for循环是图片横列扫描的结果
            for (int y = 0; y < 500; y++)
            {
                for (int x = 0; x < 500; x++)
                {
                    if (bitMatrix.get(x, y))
                    {
                        pixels[y * 500 + x] = 0xff000000;
                    }
                    else
                    {
                        pixels[y * 500 + x] = 0xffffffff;
                    }
                }
            }
            // 生成二维码图片的格式，使用ARGB_8888
            bitmap = Bitmap.createBitmap(500, 500,
                    Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels, 0, 500, 0, 0, 500, 500);
            return bitmap;
        }
        catch (WriterException e)
        {
            e.printStackTrace();
        }
        return null;
    }


    public interface IStaffLoginView
    {
        void setBitmap(Bitmap bitmap);
    }

}
