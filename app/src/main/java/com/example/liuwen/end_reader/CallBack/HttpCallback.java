package com.example.liuwen.end_reader.CallBack;

import android.graphics.Bitmap;

import java.io.InputStream;

/**
 * author : liuwen
 * e-mail : liuwen370494581@163.com
 * time   : 2018/08/07 16:43
 * desc   :
 */
public interface HttpCallback {

    void onFinish(String response);

    void onFinish(InputStream in);

    void onFinish(Bitmap bm);

    void onError(Exception e);

}
