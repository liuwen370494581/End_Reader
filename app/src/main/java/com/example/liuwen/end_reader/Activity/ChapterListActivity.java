package com.example.liuwen.end_reader.Activity;

import android.content.Context;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.liuwen.end_reader.Action.MyReadHandler;
import com.example.liuwen.end_reader.Adapter.ChapterListAdapter;
import com.example.liuwen.end_reader.Base.BaseActivity;
import com.example.liuwen.end_reader.Bean.Book;
import com.example.liuwen.end_reader.Bean.Chapter;
import com.example.liuwen.end_reader.CallBack.ResultCallback;
import com.example.liuwen.end_reader.DateLoad.CommonApi;
import com.example.liuwen.end_reader.Listener.OnHandlerListener;
import com.example.liuwen.end_reader.R;
import com.example.liuwen.end_reader.Utils.ToastUtils;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * author : liuwen
 * e-mail : liuwen370494581@163.com
 * time   : 2018/09/29 15:53
 * desc   : 章节目录
 */
public class ChapterListActivity extends BaseActivity implements View.OnClickListener {

    private RecyclerView mRecyclerView;
    private Book mCurrentBook;
    private List<Chapter> mCurrentChapterList = new ArrayList<>();
    private TextView mTvChapterList, mTvBook;
    private int flag = 1;
    private ChapterListAdapter mAdapter;


    private MyReadHandler myReadHandler = new MyReadHandler(getActivityContext(), new OnHandlerListener() {
        @Override
        public void handlerMessage(Message message, WeakReference<Context> reference) {
            ChapterListActivity activity = (ChapterListActivity) reference.get();
            if (activity != null) {
                switch (message.what) {
                    case 0:
                        activity.mAdapter.setData(mCurrentChapterList);
                        break;
                    case 1:
                        break;
                    default:
                        break;
                }
            }
        }
    });

    @Override
    protected int setLayoutRes() {
        return R.layout.activity_chapter_list;
    }

    @Override
    protected void initView() {
        showLeftView();
        mTvChapterList = getView(R.id.id_chapter_tab);
        mTvBook = getView(R.id.id_book_tab);
        mRecyclerView = getView(R.id.id_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivityContext()));
        mRecyclerView.addItemDecoration(new com.example.liuwen.end_reader.View.DividerItemDecoration(getActivityContext()));
        mAdapter = new ChapterListAdapter(mRecyclerView, R.layout.item_chapter_list, 1);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        mCurrentBook = (Book) getIntent().getSerializableExtra("Book");
        if (mCurrentBook != null) {
            setCenterText(mCurrentBook.getName());
        }
       getChapterList();
    }

    @Override
    protected void setListener() {
        mTvBook.setOnClickListener(this);
        mTvChapterList.setOnClickListener(this);
    }

    private void getChapterList() {
        showLoadingDialog("", false, null);
        CommonApi.getBookChapters(mCurrentBook.getChapterUrl(), new ResultCallback() {
            @Override
            public void onFinish(Object object, int code) {
                hideLoadingDialog();
                mCurrentChapterList = (List<Chapter>) object;
                mCurrentBook.setChapterTotalNum(mCurrentChapterList.size());
                if (mCurrentChapterList.size() == 0) {
                    ToastUtils.showCenterToast(getActivityContext(), "该书查询不到任何章节");
                    myReadHandler.sendEmptyMessage(2);
                    return;
                }
                myReadHandler.sendEmptyMessage(0);
            }

            @Override
            public void onError(Exception e) {
                hideLoadingDialog();
                myReadHandler.sendEmptyMessage(1);

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        if (v == mTvBook) {
            changePosition(1);
        } else if (v == mTvChapterList) {
            changePosition(0);
        }
    }


    private void changePosition(int index) {
        mTvChapterList.setTextColor(index == 0 ? getResources().getColor(R.color.white) : getResources().getColor(R.color.text_color_66));
        mTvChapterList.setBackgroundResource(index == 0 ? R.drawable.btn_fullblue_left_shape : R.drawable.btn_lineblue_left_shape);
        mTvBook.setBackgroundResource(index == 0 ? R.drawable.btn_lineblue_right_shape : R.drawable.btn_fullblue_right_shape);
        mTvBook.setTextColor(index == 0 ? getResources().getColor(R.color.text_color_33) : getResources().getColor(R.color.white));
    }
}
