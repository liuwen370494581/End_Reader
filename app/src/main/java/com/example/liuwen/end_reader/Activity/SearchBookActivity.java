package com.example.liuwen.end_reader.Activity;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.liuwen.end_reader.Action.SearchBookAction;
import com.example.liuwen.end_reader.Base.BaseActivity;
import com.example.liuwen.end_reader.Bean.Dish;
import com.example.liuwen.end_reader.DateLoad.HttpDataSource;
import com.example.liuwen.end_reader.EventBus.C;
import com.example.liuwen.end_reader.EventBus.Event;
import com.example.liuwen.end_reader.EventBus.EventBusUtil;
import com.example.liuwen.end_reader.R;
import com.example.liuwen.end_reader.Utils.KeyboardUtil;
import com.liaoinstan.springview.widget.SpringView;

import cn.bingoogolapple.androidcommon.adapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * author : liuwen
 * e-mail : liuwen370494581@163.com
 * time   : 2018/08/09 17:29
 * desc   :
 */
public class SearchBookActivity extends BaseActivity {

    private EditText mEditName;
    private TextView tvCancel, tvReFlash, tvClean;
    private RelativeLayout reReFlash;
    private RecyclerView mRecyclerReFlash, mRecyclerClean, mRecyclerHistory;
    private SpringView mSpringView;
    private ReFlashAdapter mReFlashAdapter;
    private int reFlash = 2;

    @Override
    protected int setLayoutRes() {
        return R.layout.activity_search_book;
    }

    @Override
    protected void initView() {
        mEditName = getView(R.id.activity_search_book_name);
        tvCancel = getView(R.id.activity_search_book_cancel);
        reReFlash = getView(R.id.activity_search_book_re_reflash);
        tvReFlash = getView(R.id.activity_search_book_reflash);
        tvClean = getView(R.id.activity_search_book_clean);
        mRecyclerReFlash = getView(R.id.activity_search_book_reflash_recycler_view);
        mRecyclerClean = getView(R.id.activity_search_book_history_recycler_view);
        mRecyclerHistory = getView(R.id.history_recycler_view);
        mSpringView = getView(R.id.history_spring_view);
        KeyboardUtil.hideInputMethodWindow(this, mEditName);
    }

    @Override
    protected void initData() {
        //书籍刷新模块
        reFlashModel();
        //书籍历史记录查询模块
        searchBookHistory();
        //书籍查询结果模块
        searchBookDetail();

    }

    @Override
    protected void setListener() {

    }


    private void reFlashModel() {
        GridLayoutManager manager = new GridLayoutManager(getActivityContext(), 3, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerReFlash.setLayoutManager(manager);
        mReFlashAdapter = new ReFlashAdapter(mRecyclerReFlash);
        mReFlashAdapter.setData(SearchBookAction.getReflashData());
        mRecyclerReFlash.setAdapter(mReFlashAdapter);
        mRecyclerReFlash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (reFlash) {
                    case 1:
                        mReFlashAdapter.setData(SearchBookAction.getReflashData());
                        reFlash = 2;
                        break;
                    case 2:
                        mReFlashAdapter.setData(SearchBookAction.getReflashData_2());
                        reFlash = 3;
                        break;
                    case 3:
                        mReFlashAdapter.setData(SearchBookAction.getReflashData_3());
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


    private class ReFlashAdapter extends BGARecyclerViewAdapter<Dish> {

        private ReFlashAdapter(RecyclerView recyclerView) {
            super(recyclerView, R.layout.item_search_book_reflash);
        }

        @Override
        protected void fillData(BGAViewHolderHelper helper, int position, Dish model) {
            helper.setText(R.id.item_name, model.getTitle());
            GradientDrawable p = (GradientDrawable) helper.getView(R.id.item_name).getBackground();
            p.setColor(Color.parseColor(SearchBookAction.getRandomColor()));
        }
    }


}
