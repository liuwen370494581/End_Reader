package com.example.liuwen.end_reader.CallBack;

import com.example.liuwen.end_reader.Bean.JsonModel;

/**
 * author : liuwen
 * e-mail : liuwen370494581@163.com
 * time   : 2018/08/07 16:47
 * desc   :
 */
public interface JsonCallback {

    void onFinish(JsonModel jsonModel);

    void onError(Exception e);
}
