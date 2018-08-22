package com.example.liuwen.end_reader.Activity;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.liuwen.end_reader.Base.BaseActivity;
import com.example.liuwen.end_reader.EventBus.C;
import com.example.liuwen.end_reader.EventBus.Event;
import com.example.liuwen.end_reader.EventBus.EventBusUtil;
import com.example.liuwen.end_reader.R;
import com.liaoinstan.springview.widget.SpringView;

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

    private SearchPresenter mSearchPresenter;

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
        mSearchPresenter = new SearchPresenter(this);
        mSearchPresenter.start();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {

    }


    public EditText getmEditName() {
        return mEditName;
    }

    public TextView getTvCancel() {
        return tvCancel;
    }

    public TextView getTvReFlash() {
        return tvReFlash;
    }

    public TextView getTvClean() {
        return tvClean;
    }

    public RelativeLayout getReReFlash() {
        return reReFlash;
    }

    public RecyclerView getmRecyclerReFlash() {
        return mRecyclerReFlash;
    }

    public RecyclerView getmRecyclerClean() {
        return mRecyclerClean;
    }

    public RecyclerView getmRecyclerHistory() {
        return mRecyclerHistory;
    }

    public SpringView getmSpringView() {
        return mSpringView;
    }
}
