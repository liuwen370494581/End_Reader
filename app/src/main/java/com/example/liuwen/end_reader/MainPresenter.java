package com.example.liuwen.end_reader;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.liuwen.end_reader.Adapter.MainAdapter;
import com.example.liuwen.end_reader.Base.BasePresenter;
import com.example.liuwen.end_reader.Fragment.BookCityFragment;
import com.example.liuwen.end_reader.Fragment.BookFragment;
import com.example.liuwen.end_reader.Fragment.SearchFragment;
import com.example.liuwen.end_reader.Fragment.UserInfoFragment;
import com.example.liuwen.end_reader.Utils.StatusBarUtil;

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
    private String[] mTabTextArr;
    private MainAdapter mMainAdapter;
    private final int[] mImgNormalResArr = {R.mipmap.ic_tab_home_normal, R.mipmap.ic_tab_search_normal, R.mipmap.ic_tab_discover_normal, R.mipmap.ic_tab_mine_normal};
    private final int[] mImgSelectedResArr = {R.mipmap.ic_tab_home_pressed, R.mipmap.ic_tab_search_pressed, R.mipmap.ic_tab_discover_pressed, R.mipmap.ic_tab_mine_pressed};

    public MainPresenter(MainActivity mainActivity) {
        this.mMainActivity = mainActivity;
    }

    @Override
    public void start() {
        initDate();
    }

    private void initDate() {
        mTabTextArr = mMainActivity.getResources().getStringArray(R.array.tabs);
        mFragments.add(new BookFragment());
        mFragments.add(new SearchFragment());
        mFragments.add(new BookCityFragment());
        mFragments.add(new UserInfoFragment());
        mMainAdapter = new MainAdapter(mFragments, mMainActivity.getSupportFragmentManager());
        mMainActivity.getMyViewPager().setAdapter(mMainAdapter);
        mMainActivity.getMyViewPager().setOffscreenPageLimit(0);
        mMainActivity.getMyViewPager().setSlipping(false);
        mMainActivity.getmTableLayout().setTabMode(TabLayout.MODE_FIXED);
        mMainActivity.getmTableLayout().addTab(mMainActivity.getmTableLayout().newTab().setCustomView(getTabView(0)));
        mMainActivity.getmTableLayout().addTab(mMainActivity.getmTableLayout().newTab().setCustomView(getTabView(1)));
        mMainActivity.getmTableLayout().addTab(mMainActivity.getmTableLayout().newTab().setCustomView(getTabView(2)));
        mMainActivity.getmTableLayout().addTab(mMainActivity.getmTableLayout().newTab().setCustomView(getTabView(3)));
        mMainActivity.getMyViewPager().addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setSelectedTabStyle(mMainActivity.getmTableLayout(), position);
                if (position == 3) {
                    StatusBarUtil.setColor(mMainActivity, ContextCompat.getColor(mMainActivity, R.color.main_text_color_focus), 0);
                } else {
                    StatusBarUtil.setColor(mMainActivity, ContextCompat.getColor(mMainActivity, R.color.transparent), 0);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mMainActivity.getmTableLayout().addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                setSelectedTabStyle(mMainActivity.getmTableLayout(), mMainActivity.getmTableLayout().getSelectedTabPosition());
                mMainActivity.getMyViewPager().setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        setSelectedTabStyle(mMainActivity.getmTableLayout(), mMainActivity.getmTableLayout().getSelectedTabPosition());
    }


    private void setSelectedTabStyle(TabLayout tabLayout, int position) {
        try {
            TextView tv = null;
            ImageView img = null;
            for (int i = 0; i < tabLayout.getTabCount(); i++) {
                TabLayout.Tab tab = tabLayout.getTabAt(i);
                tv = (TextView) tab.getCustomView().findViewById(R.id.id_tab_tv);
                tv.setTextColor(ContextCompat.getColor(mMainActivity, R.color.black));
                img = (ImageView) tab.getCustomView().findViewById(R.id.id_tab_img);
                img.setImageResource(mImgNormalResArr[i]);
            }
            TabLayout.Tab selectedTab = tabLayout.getTabAt(position);
            tv = (TextView) selectedTab.getCustomView().findViewById(R.id.id_tab_tv);
            tv.setTextColor(ContextCompat.getColor(mMainActivity, R.color.main_text_color_focus));
            img = (ImageView) selectedTab.getCustomView().findViewById(R.id.id_tab_img);
            img.setImageResource(mImgSelectedResArr[position]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private View getTabView(int position) {
        View view = View.inflate(mMainActivity, R.layout.main_tab_layout, null);
        TextView tv = (TextView) view.findViewById(R.id.id_tab_tv);
        ImageView img = (ImageView) view.findViewById(R.id.id_tab_img);
        tv.setText(mTabTextArr[position]);
        img.setImageResource(mImgNormalResArr[position]);
        return view;
    }


}
