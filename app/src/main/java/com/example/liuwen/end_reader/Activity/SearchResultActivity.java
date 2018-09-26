package com.example.liuwen.end_reader.Activity;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.liuwen.end_reader.Adapter.SearchResultAdapter;
import com.example.liuwen.end_reader.Base.BaseActivity;
import com.example.liuwen.end_reader.Bean.Book;
import com.example.liuwen.end_reader.CallBack.ResultCallback;
import com.example.liuwen.end_reader.DateLoad.CommonApi;
import com.example.liuwen.end_reader.R;
import com.example.liuwen.end_reader.Utils.ToastUtils;
import com.example.liuwen.end_reader.View.TipDialog;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * author : liuwen
 * e-mail : liuwen370494581@163.com
 * time   : 2018/09/25 15:12
 * desc   : 搜索结果
 */
public class SearchResultActivity extends BaseActivity {

    private String bookName;
    private List<Book> mSearchBooks = new ArrayList<>();
    private MyHandler mHandler = new MyHandler(this);
    private RecyclerView mRecyclerView;
    private SpringView mSpringView;
    private SearchResultAdapter mAdapter;

    private static class MyHandler extends Handler {
        private WeakReference<Context> reference;

        public MyHandler(Context context) {
            reference = new WeakReference<>(context);
        }

        @Override
        public void handleMessage(Message msg) {
            final SearchResultActivity activity = (SearchResultActivity) reference.get();
            if (activity != null) {
                switch (msg.what) {
                    case 0:
                        //获取成功
                        activity.mAdapter.setData(activity.mSearchBooks);
                        break;
                    case 1:
                        activity.showTipDialog(new TipDialog.ITipDialogListener() {
                            @Override
                            public void clickLeft() {

                            }

                            @Override
                            public void clickRight() {
                                activity.searchBookForName();
                            }
                        });//获取失败
                        break;
                    default:
                }
            }
        }
    }


    @Override
    protected int setLayoutRes() {
        return R.layout.activity_search_result;
    }

    @Override
    protected void initView() {
        showLeftView();
        setCenterText("搜索结果");
        bookName = getIntent().getStringExtra("text");
        mRecyclerView = findViewById(R.id.recycler_tv);
        mSpringView = findViewById(R.id.id_spring_view);
    }

    @Override
    protected void initData() {
        mAdapter = new SearchResultAdapter(mRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivityContext()));
        mRecyclerView.addItemDecoration(new com.example.liuwen.end_reader.View.DividerItemDecoration(getActivityContext()));
        mRecyclerView.setAdapter(mAdapter);
        searchBookForName();
    }


    @Override
    protected void setListener() {
        mSpringView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                mAdapter.clear();
                searchBookForName();
                mSpringView.onFinishFreshAndLoad();
            }

            @Override
            public void onLoadmore() {
                ToastUtils.showCenterToast(getActivityContext(), "已经没有多余的数据了");
                mSpringView.onFinishFreshAndLoad();

            }
        });
        mSpringView.setHeader(new DefaultHeader(getActivityContext()));
        mSpringView.setFooter(new DefaultFooter(getActivityContext()));
    }


    private void searchBookForName() {
        showLoadingDialog(getString(R.string.on_loading), true, null);
        mAdapter.setTitle(bookName);
        CommonApi.searchBook(bookName, new ResultCallback() {
            @Override
            public void onFinish(Object o, int code) {
                hideLoadingDialog();
                mSearchBooks = (List<Book>) o;
                mHandler.sendEmptyMessage(0);

            }

            @Override
            public void onError(Exception e) {
                hideLoadingDialog();
                mHandler.sendEmptyMessage(1);
            }
        });
    }
}