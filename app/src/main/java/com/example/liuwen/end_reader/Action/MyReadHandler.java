package com.example.liuwen.end_reader.Action;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.example.liuwen.end_reader.Listener.OnHandlerListener;

import java.lang.ref.WeakReference;

/**
 * author : liuwen
 * e-mail : liuwen370494581@163.com
 * time   : 2018/09/27 16:56
 * desc   :
 */
public class MyReadHandler extends Handler {
    private WeakReference<Context> reference;
    private OnHandlerListener mOnHandlerListener;

    public MyReadHandler(Context context, OnHandlerListener onHandlerListener) {
        reference = new WeakReference<>(context);
        mOnHandlerListener = onHandlerListener;
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        mOnHandlerListener.handlerMessage(msg,reference);
    }
}
