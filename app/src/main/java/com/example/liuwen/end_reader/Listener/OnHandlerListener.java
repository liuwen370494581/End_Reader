package com.example.liuwen.end_reader.Listener;

import android.content.Context;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * author : liuwen
 * e-mail : liuwen370494581@163.com
 * time   : 2018/09/27 16:53
 * desc   :
 */
public interface OnHandlerListener {

    void handlerMessage(Message message,WeakReference<Context> reference);
}
