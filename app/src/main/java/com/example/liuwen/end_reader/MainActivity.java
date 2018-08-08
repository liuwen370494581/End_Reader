package com.example.liuwen.end_reader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.liuwen.end_reader.Base.Config;
import com.example.liuwen.end_reader.CallBack.ResultCallback;
import com.example.liuwen.end_reader.DateLoad.CommonApi;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        CommonApi.searchBook("寒门状元", new ResultCallback() {
            @Override
            public void onFinish(Object o, int code) {
                Log.e(Config.TAG, "book====" + o);
            }

            @Override
            public void onError(Exception e) {
                Log.e(Config.TAG, "bookError====" + e);
            }
        });
    }
}
