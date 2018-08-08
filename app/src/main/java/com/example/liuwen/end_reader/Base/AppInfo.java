package com.example.liuwen.end_reader.Base;

import android.app.Application;
import android.content.Context;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * author : liuwen
 * e-mail : liuwen370494581@163.com
 * time   : 2018/08/08 14:42
 * desc   :
 */
public class AppInfo extends Application {

    private static android.os.Handler handler = new android.os.Handler();
    private static AppInfo mAppInfo;
    private ExecutorService mFixedThreadPool;//线程池


    @Override
    public void onCreate() {
        super.onCreate();
        mAppInfo = this;
        mFixedThreadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());//初始化线程池
    }

    public static Context getAppContext() {
        return mAppInfo;
    }

    public void newThread(Runnable runnable) {
        try {
            mFixedThreadPool.execute(runnable);
        } catch (Exception e) {
            e.printStackTrace();
            mFixedThreadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());//初始化线程池
            mFixedThreadPool.execute(runnable);
        }

    }

    public void closeThreadPool() {
        mFixedThreadPool.shutdown();
    }

    public static void runOnUiThread(Runnable runnable) {
        handler.post(runnable);
    }

    public static AppInfo getAppInfo() {
        return mAppInfo;
    }


}



