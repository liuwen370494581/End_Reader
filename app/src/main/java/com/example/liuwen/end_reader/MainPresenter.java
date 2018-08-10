package com.example.liuwen.end_reader;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.liuwen.end_reader.Base.BasePresenter;
import com.example.liuwen.end_reader.Fragment.BookCityFragment;
import com.example.liuwen.end_reader.Fragment.BookFragment;

import java.util.ArrayList;

/**
 * author : liuwen
 * e-mail : liuwen370494581@163.com
 * time   : 2018/08/10 11:14
 * desc   :
 */
public class MainPresenter implements BasePresenter {

    private MainActivity mMainActivity;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private String[] tabTitle = {"书架", "书城"};

    public MainPresenter(MainActivity mainActivity) {
        this.mMainActivity = mainActivity;
    }

    @Override
    public void start() {
        initDate();
    }

    private void initDate() {
        mFragments.add(new BookFragment());
        mFragments.add(new BookCityFragment());
        mMainActivity.getVpContent().setAdapter(new FragmentPagerAdapter(mMainActivity.getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return tabTitle[position];
            }
        });

        mMainActivity.getTlTabMenu().setupWithViewPager(mMainActivity.getVpContent());
        mMainActivity.getVpContent().setCurrentItem(0);
    }


}
