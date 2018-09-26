package com.example.liuwen.end_reader.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.liuwen.end_reader.Base.BaseFragment;
import com.example.liuwen.end_reader.R;
import com.example.liuwen.end_reader.View.SearchBar;

/**
 * author : liuwen
 * e-mail : liuwen370494581@163.com
 * time   : 2018/09/25 13:31
 * desc   : 搜索页面
 */
public class SearchFragment extends BaseFragment {

    private SearchBar mSearchBar;


    @Override
    public void initData() {
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mSearchBar = (SearchBar) view.findViewById(R.id.id_search_bar);
    }
}
