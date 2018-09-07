package com.example.liuwen.end_reader.DateLoad;

import android.graphics.Bitmap;
import android.util.Log;

import com.example.liuwen.end_reader.Base.Config;
import com.example.liuwen.end_reader.Bean.Dish;
import com.example.liuwen.end_reader.CallBack.HttpCallback;
import com.example.liuwen.end_reader.CallBack.ResultCallback;
import com.example.liuwen.end_reader.Utils.HttpUtil;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * author : liuwen
 * e-mail : liuwen370494581@163.com
 * time   : 2018/08/08 15:15
 * desc   :
 */
public class HttpDataSource {

    public static void HttpGet_html(String url, final String charsetName, final ResultCallback callback) {
        Log.e(Config.TAG, "httpGet_html=====" + url);
        HttpUtil.sendGetRequest_okHttp(url, new HttpCallback() {
            @Override
            public void onFinish(String response) {

            }

            @Override
            public void onFinish(InputStream in) {
                try {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in, charsetName));
                    StringBuilder response = new StringBuilder();
                    String line = reader.readLine();
                    while (line != null) {
                        response.append(line);
                        line = reader.readLine();
                    }
                    if (callback != null) {
                        Log.d("Http", "read finish：" + response.toString());
                        callback.onFinish(response.toString(), 0);
                    }
                } catch (Exception e) {
                    callback.onError(e);
                }
            }

            @Override
            public void onFinish(Bitmap bm) {

            }

            @Override
            public void onError(Exception e) {
                if (callback != null) {
                    callback.onError(e);
                }
            }
        });
    }




}
