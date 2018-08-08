package com.example.liuwen.end_reader.CallBack;

/**
 * author : liuwen
 * e-mail : liuwen370494581@163.com
 * time   : 2018/08/07 16:45
 * desc   :
 */
public interface ResultCallback {
    void onFinish(Object o, int code);

    void onError(Exception e);

}
