package com.example.liuwen.end_reader.Activity;

import android.content.Context;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.example.liuwen.end_reader.Action.MyReadHandler;
import com.example.liuwen.end_reader.Adapter.ReadAdapter;
import com.example.liuwen.end_reader.Base.BaseActivity;
import com.example.liuwen.end_reader.Base.Config;
import com.example.liuwen.end_reader.Bean.Book;
import com.example.liuwen.end_reader.Bean.Chapter;
import com.example.liuwen.end_reader.CallBack.ResultCallback;
import com.example.liuwen.end_reader.DateLoad.CommonApi;
import com.example.liuwen.end_reader.Listener.OnHandlerListener;
import com.example.liuwen.end_reader.R;
import com.example.liuwen.end_reader.Utils.StringHelper;
import com.example.liuwen.end_reader.Utils.ToastUtils;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * author : liuwen
 * e-mail : liuwen370494581@163.com
 * time   : 2018/09/26 15:40
 * desc   :
 */
public class ReadBookActivity extends BaseActivity {


    private Book mCurrentBook;
    private String mChapterUrl = "";
    private List<Chapter> mChapterList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private ReadAdapter mAdapter;

    private MyReadHandler myReadHandler = new MyReadHandler(this, new OnHandlerListener() {
        @Override
        public void handlerMessage(Message message, WeakReference<Context> reference) {
            ReadBookActivity activity = (ReadBookActivity) reference.get();
            if (activity != null) {
                switch (message.what) {
                    case 0:
                        activity.mAdapter.setData(activity.mChapterList);
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
        requestWindowFeature(Window.FEATURE_NO_TITLE); // 隐藏应用程序的标题栏，即当前activity的label
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); // 隐藏android系统的状态栏
        return R.layout.activity_read_book;
    }


    @Override
    protected void initView() {
        mRecyclerView = getView(R.id.id_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivityContext()));
        mAdapter = new ReadAdapter(mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        mCurrentBook = (Book) getIntent().getSerializableExtra("Book");
        if (mCurrentBook != null) {
            mChapterUrl = mCurrentBook.getChapterUrl();
        }
        getDate();

    }


    @Override
    protected void setListener() {

    }

    private void getDate() {
        showLoadingDialog("", false, null);
        CommonApi.getBookChapters(mChapterUrl, new ResultCallback() {
            @Override
            public void onFinish(Object object, int code) {
                mChapterList = (List<Chapter>) object;
                mCurrentBook.setChapterTotalNum(mChapterList.size());
                if (mChapterList.size() == 0) {
                    ToastUtils.showCenterToast(getActivityContext(), "该书查询不到任何章节");
                } else {
                    if (mCurrentBook.getHisttoryChapterNum() < 0) {
                        mCurrentBook.setHisttoryChapterNum(0);
                    } else if (mCurrentBook.getHisttoryChapterNum() >= mChapterList.size()) {
                        mCurrentBook.setHisttoryChapterNum(mChapterList.size() - 1);
                    }
                    getChapterContent(mChapterList.get(mCurrentBook.getHisttoryChapterNum()), new ResultCallback() {
                        @Override
                        public void onFinish(Object object, int code) {
                            hideLoadingDialog();
                            mChapterList.get(mCurrentBook.getHisttoryChapterNum()).setContent((String) object);
                            Log.e(Config.MyTAG, mChapterList.get(mCurrentBook.getHisttoryChapterNum()).getContent());
                            myReadHandler.sendEmptyMessage(0);
                        }

                        @Override
                        public void onError(Exception e) {
                            myReadHandler.sendEmptyMessage(1);
                        }
                    });
                }


            }

            @Override
            public void onError(Exception e) {

            }
        });
    }

    private void getChapterContent(final Chapter chapter, ResultCallback resultCallback) {
        if (StringHelper.isEmpty(chapter.getBookId())) chapter.setId(mCurrentBook.getId());
        if (!StringHelper.isEmpty(chapter.getContent())) {
            if (resultCallback != null) {
                resultCallback.onFinish(chapter.getContent(), 0);
            }
        } else {
            if (resultCallback != null) {
                CommonApi.getChapterContent(chapter.getUrl(), resultCallback);
            } else {
                CommonApi.getChapterContent(chapter.getUrl(), new ResultCallback() {
                    @Override
                    public void onFinish(final Object o, int code) {
                        chapter.setContent((String) o);
                        Log.e("MainActivity", chapter.getContent());
                        // mChapterService.saveOrUpdateChapter(chapter);
                    }

                    @Override
                    public void onError(Exception e) {

                    }

                });
            }
        }
    }

}
