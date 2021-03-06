package com.channelsoft.android.ggsj.login.model;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;

import com.channelsoft.android.ggsj.http.Http;
import com.channelsoft.android.ggsj.http.Url;
import com.channelsoft.android.ggsj.login.bean.CompanyData;
import com.channelsoft.android.ggsj.login.listener.OnLoginListener;
import com.channelsoft.android.ggsj.login.viewmodel.ILoginViewModel;
import com.channelsoft.android.ggsj.utils.LogUtils;
import com.channelsoft.android.ggsj.utils.LoginManager;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dengquan on 16-5-3.
 */
public class LoginModel implements ILoginModel
{
    private static final String TAG = LoginModel.class.getSimpleName();
    private ILoginViewModel viewModel;

    public LoginModel(ILoginViewModel viewModel)
    {
        this.viewModel = viewModel;
    }

    @Override
    public void login(final String entId)
    {
        StringRequest request = new StringRequest(Request.Method.POST,
                LoginManager.getHelpDeskUrl() + Url.Login.BOSS_LOGIN_HELPDESK,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String s)
                    {
                        LogUtils.i(TAG, s.toString());
                        Gson gson = new Gson();
                        CompanyData info ;
                        try
                        {
                            info = gson.fromJson(s.toString(),CompanyData.class);
                            LoginManager.saveToken(info.getData().getTokenId());

                            if (viewModel != null)
                            {
                                viewModel.onError();
                            }
                        }
                        catch (Exception e)
                        {
                            viewModel.onSuccess();
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError volleyError)
                    {
                        LogUtils.i(TAG, "exception cause " + volleyError.getCause());

                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                Map<String, String> map = new HashMap<>();
                map.put("phone", "18812345678");
                map.put("entId", entId);
                LogUtils.i(TAG, "entId  :" + entId);
                map.put("tokenId", LoginManager.getToken());
                return map;
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response)
            {
                try
                {
                    Map<String, String> responseHeaders = response.headers;
                    String rawCookies = responseHeaders.get("Set-Cookie");
                    String dataString = new String(response.data, "GBK");
                    LogUtils.i(TAG, "cookie :" + rawCookies + "   " + responseHeaders.toString());
                    saveSession(rawCookies);
                    return Response.success(dataString, HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException ex)
                {
                    return Response.error(new ParseError(ex));
                }

            }
        };
        Http.addRequest(request, TAG);
    }

    private void saveSession(String cookie)
    {

        String[] s = cookie.split(";");
        for(String string :s)
        {
            if (string.contains("JSESSIONID"))
            {
                LogUtils.e("授权登录获取sessionid=", string.substring(11));
                LoginManager.saveSessionId(string.substring(11));
            }
        }
    }

}
