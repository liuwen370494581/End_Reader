package com.example.liuwen.end_reader.Action;

/**
 * author : liuwen
 * e-mail : liuwen370494581@163.com
 * time   : 2018/09/07 14:52
 * desc   :
 */
public interface ActionCallBack<T> {

    void ok(T obj);

    void failed(Object obj);
}
