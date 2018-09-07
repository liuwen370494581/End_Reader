package com.example.liuwen.end_reader.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.liuwen.end_reader.Base.BaseFragment;
import com.example.liuwen.end_reader.R;

/**
 * author : liuwen
 * e-mail : liuwen370494581@163.com
 * time   : 2018/08/22 14:27
 * desc   :
 */
public class UserInfoFragment extends BaseFragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_info, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
    }

    @Override
    public void initData() {

    }
}
