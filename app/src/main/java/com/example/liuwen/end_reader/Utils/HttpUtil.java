package com.example.liuwen.end_reader.Utils;

import com.example.liuwen.end_reader.Base.AppInfo;
import com.example.liuwen.end_reader.CallBack.HttpCallback;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * author : liuwen
 * e-mail : liuwen370494581@163.com
 * time   : 2018/08/07 17:19
 * desc   :
 */
public class HttpUtil {


    private static String sessionid;
    private static OkHttpClient mClient;

    private static synchronized OkHttpClient getOkHttpClient() {
        if (mClient == null) {
            mClient = new OkHttpClient.Builder()
                    .readTimeout(5000, TimeUnit.SECONDS)
                    .writeTimeout(5000, TimeUnit.SECONDS)
                    .connectTimeout(5000, TimeUnit.SECONDS)//设置连接超时时间
                    .build();
        }
        return mClient;
    }

    public static void sendBitmapGetRequest(final String address, final HttpCallback httpCallback) {
        new Thread(new Runnable() {
            HttpURLConnection connection = null;

            @Override
            public void run() {
                try {
                    URL url = new URL(address);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    public static void sendGetRequest_okHttp(final String address, final HttpCallback httpCallback) {
        AppInfo.getAppInfo().newThread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = getOkHttpClient();
                    Request request = new Request.Builder().url(address).build();
                    Response response = client.newCall(request).execute();
                    httpCallback.onFinish(response.body().byteStream());
                } catch (IOException e) {
                    e.printStackTrace();
                    httpCallback.onError(e);
                }

            }
        });
    }

    public static void sendPostRequest_okHttp(final String address, final String output, final HttpCallback httpCallback) {
        AppInfo.getAppInfo().newThread(new Runnable() {
            @Override
            public void run() {
                try {
                    MediaType contentType = MediaType.parse("charset=utf-8");
                    OkHttpClient client = getOkHttpClient();
                    RequestBody body = RequestBody.create(contentType, output);
                    Request request = new Request.Builder().url(address).post(body).build();
                    Response response = client.newCall(request).execute();
                    httpCallback.onFinish(response.body().byteStream());
                } catch (Exception e) {
                    e.printStackTrace();
                    httpCallback.onError(e);
                }

            }
        });
    }
}
