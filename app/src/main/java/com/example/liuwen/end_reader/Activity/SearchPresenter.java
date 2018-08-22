package com.example.liuwen.end_reader.Activity;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.liuwen.end_reader.Base.BasePresenter;
import com.example.liuwen.end_reader.Bean.Dish;
import com.example.liuwen.end_reader.DateLoad.HttpDataSource;
import com.example.liuwen.end_reader.R;

import cn.bingoogolapple.androidcommon.adapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * author : liuwen
 * e-mail : liuwen370494581@163.com
 * time   : 2018/08/10 17:00
 * desc   :
 */
public class SearchPresenter implements BasePresenter {

    private SearchBookActivity mSearchBookActivity;
    private ReFlashAdapter mReFlashAdapter;
    private int reFlash = 2;


    @Override
    public void start() {
        initDate();
    }

    private void initDate() {
        //书籍刷新模块
        reFlashModel();
        //书籍历史记录查询模块
        searchBookHistory();
        //书籍查询结果模块
        searchBookDetail();
    }

    private void reFlashModel() {
        GridLayoutManager manager = new GridLayoutManager(mSearchBookActivity, 3, LinearLayoutManager.HORIZONTAL, false);
        mSearchBookActivity.getmRecyclerReFlash().setLayoutManager(manager);
        mReFlashAdapter = new ReFlashAdapter(mSearchBookActivity.getmRecyclerReFlash());
        mReFlashAdapter.setData(HttpDataSource.getReflashData());
        mSearchBookActivity.getmRecyclerReFlash().setAdapter(mReFlashAdapter);
        mSearchBookActivity.getTvReFlash().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (reFlash) {
                    case 1:
                        mReFlashAdapter.setData(HttpDataSource.getReflashData());
                        reFlash = 2;
                        break;
                    case 2:
                        mReFlashAdapter.setData(HttpDataSource.getReflashData_2());
                        reFlash = 3;
                        break;
                    case 3:
                        mReFlashAdapter.setData(HttpDataSource.getReflashData_3());
                        reFlash = 1;
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void searchBookHistory() {

    }

    private void searchBookDetail() {

    }

    public SearchPresenter(SearchBookActivity mSearchBookActivity) {
        this.mSearchBookActivity = mSearchBookActivity;
    }


    private class ReFlashAdapter extends BGARecyclerViewAdapter<Dish> {

        private ReFlashAdapter(RecyclerView recyclerView) {
            super(recyclerView, R.layout.item_search_book_reflash);
        }

        @Override
        protected void fillData(BGAViewHolderHelper helper, int position, Dish model) {
            helper.setText(R.id.item_name, model.getTitle());
            GradientDrawable p = (GradientDrawable) helper.getView(R.id.item_name).getBackground();
            p.setColor(Color.parseColor(HttpDataSource.getRandomColor()));

        }
    }


}
