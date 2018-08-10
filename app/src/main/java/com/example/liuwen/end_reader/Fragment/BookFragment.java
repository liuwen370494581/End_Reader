package com.example.liuwen.end_reader.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.liuwen.end_reader.Base.BaseFragment;
import com.example.liuwen.end_reader.EventBus.Event;
import com.example.liuwen.end_reader.R;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * author : liuwen
 * e-mail : liuwen370494581@163.com
 * time   : 2018/08/09 17:24
 * desc   :
 */
public class BookFragment extends BaseFragment {
    @Override
    public void initData() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book, container, false);
        initView(view);
        return view;
    }
    private void initView(View view){

    }

}
