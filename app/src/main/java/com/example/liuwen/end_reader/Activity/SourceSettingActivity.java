package com.example.liuwen.end_reader.Activity;

import com.example.liuwen.end_reader.Base.BaseActivity;
import com.example.liuwen.end_reader.R;

/**
 * author : liuwen
 * e-mail : liuwen370494581@163.com
 * time   : 2018/09/25 15:02
 * desc   : 搜索源
 */
public class SourceSettingActivity extends BaseActivity {
    @Override
    protected int setLayoutRes() {
        return R.layout.activity_source_setting;
    }

    @Override
    protected void initView() {
        showLeftView();
        setCenterText("搜索源");
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {

    }
}
